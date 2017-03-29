package com.sky.demo.web_demo_multi_tenant_separate_schema.es.search;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.EsClient;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto.SearchCondition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 16/11/29.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    //@Resource
    private EsClient esClient;

    private static final int MAX_SEARCH_SIZE = 5;
    private static final int SCROLL_TIME_VALUE_MINUTES = 1;


    @Override
    public SearchResponse search(String index, String type, QueryBuilder queryBuilder, SortBuilder sortBuilder, int from, int size) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkNotNull(queryBuilder, "QueryBuilder is null");


        logger.info("QueryBuilder : {}", queryBuilder.toString());
//        TransportClient transportClient = getTransportWithRetry();
        SearchResponse response = esClient.getTransportClient().prepareSearch(index)
                .setTypes(type)
                .setQuery(queryBuilder)
                .addSort(sortBuilder)
                .setFrom(from)
                .setSize(size)
                .get();

        return response;
    }

    @Override
    public SearchResponse search(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");
        SearchResponse response = getSearchResponse(searchCondition);
        long total = response.getHits().getTotalHits();

        logger.info("search by scroll total size : {}, result:\n", total, response);
        return response;
    }

    @Override
    public long searchCount(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");
        //count do not need  from, size, sort
        SearchResponse response = getSearchResponse(searchCondition);

        long count = 0;
        if (response != null) {
            count = response.getHits().getTotalHits();
        }
        logger.info("search count result : {}", count);
        return count;
    }

    @Override
    public List<SearchHit> searchHits(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");
        List<SearchHit> searchHits = Lists.newArrayList();
        SearchResponse response = getSearchResponse(searchCondition);

        if (response != null) {
            SearchHit[] hits = response.getHits().getHits();
            searchHits.addAll(Lists.newArrayList(hits));
        }
        logger.info("search hits size : {}, result : {}", searchHits.size(), searchHits);

        return searchHits;
    }

    @Override
    public SearchResponse searchByScroll(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        SearchResponse response = getSearchResponseByScroll(searchCondition);
        long total = response.getHits().getTotalHits();

        logger.info("search by scroll total size : {}, result:\n", total, response);
        return response;
    }

    @Override
    public long searchCountByScroll(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        TransportClient client = getTransportWithRetry();
        SearchRequestBuilder builder = client.prepareSearch();
        builder.setIndices(searchCondition.getIndices().stream().toArray(String[]::new))
                .setTypes(searchCondition.getTypes().stream().toArray(String[]::new))
                .setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_VALUE_MINUTES))
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)  //search_type=scan removed, replaced by sort by _doc
                .setSize(MAX_SEARCH_SIZE);          //max of SIZE hits will be returned for each scroll

        SearchResponse response = builder.get();

        long total = response.getHits().getTotalHits(); //第一次不返回数据
        String scrollId = response.getScrollId();
        logger.info("total={}, scrollId={}, took time={} ms", total, scrollId, response.getTookInMillis());
        //Scroll until no hits are returned
        long count = 0;
        do {
            count += response.getHits().getHits().length;

            response = client.prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_VALUE_MINUTES))
                    .get();

            scrollId = response.getScrollId();  //maybe multi index
            logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());

        } while (response.getHits().getHits().length != 0);

        logger.info("search count result : {}, total : {}", count, total);
        return count;
    }

    @Override
    public List<SearchHit> searchHitsByScroll(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");
        List<SearchHit> result = Lists.newArrayList();

        SearchResponse response = getSearchResponseByScroll(searchCondition);
        String scrollId = response.getScrollId();
        logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());

        int from = searchCondition.getFrom() == null ? 0 : searchCondition.getFrom();
        int scrollSize = MAX_SEARCH_SIZE;
        if (from > 0) {
            scrollSize = from < MAX_SEARCH_SIZE ? from : MAX_SEARCH_SIZE;
        }

        int skipTime = from / scrollSize;
        int lastOffset = from - scrollSize * skipTime;
        int size = searchCondition.getSize();  // limit

        logger.info("from={}, scrollSize={}, skipTime={}, lastOffset={}, size={}", from, scrollSize, skipTime, lastOffset, size);


        long current = 0;
        //skip
        for (int i = 0; i < skipTime; i++) {
            TransportClient client = getTransportWithRetry();
            response = client.prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_VALUE_MINUTES))
                    .get();

            int length = response.getHits().getHits().length;
            scrollId = response.getScrollId();
            current += length;

            logger.info("remain skipTime={}, length={}, current={}, scrollId={}, took time={} ms",
                    skipTime, length, current, scrollId, response.getTookInMillis());
        }


        //从lastOffset 开始读取数据
        int putIn = 0;
        for (int i = lastOffset; i < (lastOffset + size); i++) {
            if (i < response.getHits().getHits().length) {
                result.add(response.getHits().getAt(i));
                putIn++;
            }
        }
        size -= putIn;

        //继续拉取数据
        while (response.getHits().getHits().length > 0 && size > 0){
            TransportClient client = getTransportWithRetry();
            response = client.prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_VALUE_MINUTES))
                    .get();

            int length = response.getHits().getHits().length;
            scrollId = response.getScrollId();
            logger.info("length={}, scrollId={}, took time={} ms", length, scrollId, response.getTookInMillis());


            putIn = 0;
            for (int i= 0; i < size; i++) {
                if (i < response.getHits().getHits().length) {
                    result.add(response.getHits().getAt(i));
                    putIn++;
                }
            }
            size -= putIn;
        }

        return result;
    }

    @Override
    public MultiSearchResponse multiSearch(List<SearchRequestBuilder> searchRequestBuilders) {

        MultiSearchRequestBuilder multiSearchRequestBuilder = esClient.getTransportClient().prepareMultiSearch();
        if (CollectionUtils.isNotEmpty(searchRequestBuilders)) {
            searchRequestBuilders.forEach(searchRequestBuilder -> multiSearchRequestBuilder.add(searchRequestBuilder));
        }

        MultiSearchResponse response = multiSearchRequestBuilder.get();

        return response;
    }


    @Override
    public ExplainResponse explain(String index, String type, String id, QueryBuilder queryBuilder) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(id), "id is null");

        ExplainResponse response = esClient.getTransportClient().prepareExplain(index, type, id)
                .setQuery(queryBuilder)
                .get();

        return response;
    }

    @Override
    public SearchResponse aggregation(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");
        SearchResponse response = getSearchResponse(searchCondition);

        return response;
    }


    private SearchResponse getSearchResponse(SearchCondition searchCondition) {
        SearchResponse searchResponse = null;
        TransportClient transportClient = getTransportWithRetry();  // connection may be close

        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch();
        searchRequestBuilder.setIndices(searchCondition.getIndices().stream().toArray(String[]::new))
                .setTypes(searchCondition.getTypes().stream().toArray(String[]::new))
                .setFrom(searchCondition.getFrom() == null ? 0 : searchCondition.getFrom());


        //size  (size of each shard)
        if (searchCondition.getSize() != null) {
            searchRequestBuilder.setSize(searchCondition.getSize());
        }

        //searchType
        if (searchCondition.getSearchType() != null) {
            searchRequestBuilder.setSearchType(searchCondition.getSearchType());
        }
        //explain
        if (searchCondition.getExplain() != null) {
            searchRequestBuilder.setExplain(searchCondition.getExplain());
        }

        //fetch source includes
        if (CollectionUtils.isNotEmpty(searchCondition.getFetchSourceIncludes())
                || CollectionUtils.isNotEmpty(searchCondition.getFetchSourceExcludes())) {
            String[] includes = CollectionUtils.isNotEmpty(searchCondition.getFetchSourceIncludes()) ? searchCondition.getFetchSourceIncludes().stream().toArray(String[]::new) : null;
            String[] excludes = CollectionUtils.isNotEmpty(searchCondition.getFetchSourceExcludes()) ? searchCondition.getFetchSourceExcludes().stream().toArray(String[]::new) : null;
            searchRequestBuilder.setFetchSource(includes, excludes);
        }

        //store field
        if (CollectionUtils.isNotEmpty(searchCondition.getStoreFields())) {
            searchCondition.getStoreFields().forEach(field -> searchRequestBuilder.addStoredField(field));
        }

        //query
        if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMusts())
                || CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMustNots())
                || CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderShoulds())
                || CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderFilters())) {

            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMusts())) {
                searchCondition.getQueryBuilderMusts().forEach(must -> queryBuilder.must(must));
            }
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMustNots())) {
                searchCondition.getQueryBuilderMustNots().forEach(mustNot -> queryBuilder.mustNot(mustNot));
            }
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderShoulds())) {
                searchCondition.getQueryBuilderShoulds().forEach(should -> queryBuilder.should(should));
            }
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderFilters())) {
                searchCondition.getQueryBuilderFilters().forEach(filter -> queryBuilder.filter(filter));
            }

            logger.debug("bool query builder:{}", queryBuilder);
            searchRequestBuilder.setQuery(queryBuilder);
        }
        
        //sort
        if (CollectionUtils.isNotEmpty(searchCondition.getSortBuilders())) {
            logger.debug("sort builders:{}", searchCondition.getSortBuilders());
            searchCondition.getSortBuilders().forEach(sortBuilder -> searchRequestBuilder.addSort(sortBuilder));
        }

        //agg
        if (CollectionUtils.isNotEmpty(searchCondition.getAggregationBuilders())) {
            searchCondition.getAggregationBuilders().forEach(aggregationBuilder -> searchRequestBuilder.addAggregation(aggregationBuilder));
        }

        searchResponse = searchRequestBuilder.get();   //.execute().actionGet()
        return searchResponse;
    }


    //for agg, count
    private SearchResponse getSearchResponseByScroll(SearchCondition searchCondition) {
        SearchResponse searchResponse = null;
        TransportClient transportClient = getTransportWithRetry();  // connection may be close

        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch();
        searchRequestBuilder.setIndices(searchCondition.getIndices().stream().toArray(String[]::new))
                .setTypes(searchCondition.getTypes().stream().toArray(String[]::new))
                .setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_VALUE_MINUTES));

//        searchRequestBuilder.searchAfter();

        //from, size in scroll can not used directly
        //searchRequestBuilder will use other size;
        int from = searchCondition.getFrom() == null ? 0 : searchCondition.getFrom();
        int scrollSize = MAX_SEARCH_SIZE;
        if (from > 0) {
            scrollSize = from < MAX_SEARCH_SIZE ? from : MAX_SEARCH_SIZE;
        }
        searchRequestBuilder.setSize(scrollSize);   //注意！scroll里面的size是相对于每个分片来说的，所以实际返回的数量是：分片的数量*size


        //searchType
        if (searchCondition.getSearchType() != null) {
            searchRequestBuilder.setSearchType(searchCondition.getSearchType());
        }
        //explain
        if (searchCondition.getExplain() != null) {
            searchRequestBuilder.setExplain(searchCondition.getExplain());
        }

        //fetch source
        if (CollectionUtils.isNotEmpty(searchCondition.getFetchSourceIncludes())) {
            searchRequestBuilder.setFetchSource(searchCondition.getFetchSourceIncludes()
                    .stream().toArray(String[]::new), null);
        }

        //store field
        if (CollectionUtils.isNotEmpty(searchCondition.getStoreFields())) {
            searchCondition.getStoreFields().forEach(field -> searchRequestBuilder.addStoredField(field));
        }

        //query
        if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMusts())
                || CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMustNots())
                || CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderShoulds())
                || CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderFilters())) {

            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMusts())) {
                searchCondition.getQueryBuilderMusts().forEach(must -> queryBuilder.must(must));
            }
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderMustNots())) {
                searchCondition.getQueryBuilderMustNots().forEach(mustNot -> queryBuilder.mustNot(mustNot));
            }
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderShoulds())) {
                searchCondition.getQueryBuilderShoulds().forEach(should -> queryBuilder.should(should));
            }
            if (CollectionUtils.isNotEmpty(searchCondition.getQueryBuilderFilters())) {
                searchCondition.getQueryBuilderFilters().forEach(filter -> queryBuilder.filter(filter));
            }

            logger.debug("bool query builder:{}", queryBuilder);
            searchRequestBuilder.setQuery(queryBuilder);
        }

        //sort
        if (CollectionUtils.isNotEmpty(searchCondition.getSortBuilders())) {
            logger.debug("sort builders:{}", searchCondition.getSortBuilders());
            searchCondition.getSortBuilders().forEach(sortBuilder -> searchRequestBuilder.addSort(sortBuilder));
        } else {
            //不关心顺序，可以通过 sort _doc
            searchRequestBuilder.addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC);
        }

        //agg
        if (CollectionUtils.isNotEmpty(searchCondition.getAggregationBuilders())) {
            searchCondition.getAggregationBuilders().forEach(aggregationBuilder -> searchRequestBuilder.addAggregation(aggregationBuilder));
        }

        searchResponse = searchRequestBuilder.get();   //.execute().actionGet()
        return searchResponse;
    }


    private TransportClient getTransportWithRetry() {
        TransportClient client = esClient.getTransportClient();
        if (client == null) {
            logger.warn("------>TransportClient is null, rebuild");

            esClient.rebuild();
            client = esClient.getTransportClient();
        }
        return client;
    }

}
