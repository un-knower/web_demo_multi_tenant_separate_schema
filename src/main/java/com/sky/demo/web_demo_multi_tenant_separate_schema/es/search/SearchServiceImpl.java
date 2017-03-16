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

    @Resource
    private EsClient esClient;

    private static final int MAX_SEARCH_SIZE = 10000;


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
    public SearchResponse searchByScroll(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        TransportClient client = getTransportWithRetry();
        SearchRequestBuilder builder = client.prepareSearch();
        builder.setIndices(searchCondition.getIndices().stream().toArray(String[]::new))
                .setTypes(searchCondition.getTypes().stream().toArray(String[]::new))
                .setScroll(TimeValue.timeValueMinutes(1))
                .setSize(searchCondition.getSize());          //注意啊！scroll里面的size是相对于每个分片来说的，所以实际返回的数量是：分片的数量*size

        SearchResponse response = builder.get();
        String scrollId = response.getScrollId();
        logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());
        //Scroll until no hits are returned
        do {

            response = esClient.getTransportClient()
                    .prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(1))
                    .get();   //.execute().actionGet();

            scrollId = response.getScrollId();  //maybe multi index
            logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());

        } while (response.getHits().getHits().length != 0);

        ClearScrollResponse clearScrollResponse = esClient.getTransportClient()
                .prepareClearScroll().get();
        logger.debug("clear scroll , id={}, isSucceeded={}", scrollId, clearScrollResponse.isSucceeded());
        return response;
    }

    @Override
    public long searchCountByScroll(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        TransportClient client = getTransportWithRetry();
        SearchRequestBuilder builder = client.prepareSearch();
        builder.setIndices(searchCondition.getIndices().stream().toArray(String[]::new))
                .setTypes(searchCondition.getTypes().stream().toArray(String[]::new))
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)  //search_type=scan removed, replaced by sort by _doc
                .setScroll(TimeValue.timeValueMinutes(1))
                .setSize(MAX_SEARCH_SIZE);          //max of SIZE hits will be returned for each scroll

        SearchResponse response = builder.get();

        long total = response.getHits().getTotalHits();

        String scrollId = response.getScrollId();
        logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());
        //Scroll until no hits are returned
        long count = 0;
        do {
            count += response.getHits().getHits().length;

            response = client.prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(1))
                    .get();

            scrollId = response.getScrollId();  //maybe multi index
            logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());

        } while (response.getHits().getHits().length != 0);

        logger.info("search count result : {}, total : {}", count, total);
        return count;
    }

    @Override
    public List<SearchHit> searchByScrollAllHits(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        List<SearchHit> result = Lists.newArrayList();

        SearchRequestBuilder builder = esClient.getTransportClient().prepareSearch();
        builder.setIndices((String[]) searchCondition.getIndices().toArray())
                .setTypes((String[]) searchCondition.getTypes().toArray())
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(TimeValue.timeValueMinutes(1))
                .setQuery(QueryBuilders.termQuery("", ""))
                .setExplain(searchCondition.getExplain());
        SearchResponse response = builder.get();

        String scrollId = response.getScrollId();
        logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());
        //Scroll until no hits are returned
        do {
            for (SearchHit hit : response.getHits().getHits()) {
                //Handle the hit...
                result.add(hit);
            }

            response = esClient.getTransportClient()
                    .prepareSearchScroll(scrollId)
                    .setScroll(TimeValue.timeValueMinutes(1))
                    .get();   //.execute().actionGet();

            scrollId = response.getScrollId();
            logger.info("scrollId={}, took time={} ms", scrollId, response.getTookInMillis());

        } while (response.getHits().getHits().length != 0);

        ClearScrollResponse clearScrollResponse = esClient.getTransportClient()
                .prepareClearScroll().get();
        logger.info("clear scroll , id={}, isSucceeded={}", scrollId, clearScrollResponse.isSucceeded());
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

        //searchType
        if (searchCondition.getSearchType() != null) {
            searchRequestBuilder.setSearchType(searchCondition.getSearchType());
        }
        //explain
        if (searchCondition.getExplain() != null) {
            searchRequestBuilder.setExplain(searchCondition.getExplain());
        }

        //size  (size of each shard)
        if (searchCondition.getSize() != null) {
            searchRequestBuilder.setSize(searchCondition.getSize());
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
        }

        //agg
        if (CollectionUtils.isNotEmpty(searchCondition.getAggregationBuilders())) {
            searchCondition.getAggregationBuilders().forEach(aggregationBuilder -> searchRequestBuilder.addAggregation(aggregationBuilder));
        }

        searchResponse = searchRequestBuilder.get();   //.execute().actionGet()
        return searchResponse;
    }


    private SearchResponse getSearchResponseByScroll(SearchCondition searchCondition) {
        SearchResponse searchResponse = null;
        TransportClient transportClient = getTransportWithRetry();  // connection may be close

        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch();
        searchRequestBuilder.setIndices(searchCondition.getIndices().stream().toArray(String[]::new))
                .setTypes(searchCondition.getTypes().stream().toArray(String[]::new))
                .setFrom(searchCondition.getFrom() == null ? 0 : searchCondition.getFrom());

//        searchRequestBuilder.searchAfter();

        //searchType
        if (searchCondition.getSearchType() != null) {
            searchRequestBuilder.setSearchType(searchCondition.getSearchType());
        }
        //explain
        if (searchCondition.getExplain() != null) {
            searchRequestBuilder.setExplain(searchCondition.getExplain());
        }

        //size  (size of each shard)
        if (searchCondition.getSize() != null) {
            searchRequestBuilder.setSize(searchCondition.getSize());
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
