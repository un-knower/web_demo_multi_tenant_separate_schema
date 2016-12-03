package com.sky.demo.web_demo_multi_tenant_separate_schema.es.document;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.EsClient;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto.SearchCondition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.util.CollectionUtil;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
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


    @Override
    public SearchResponse search(String index, String type, QueryBuilder queryBuilder, SortBuilder sortBuilder, int from, int size) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkNotNull(queryBuilder, "QueryBuilder is null");


        logger.info("QueryBuilder : {}", queryBuilder.toString());
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
        SearchRequestBuilder builder = esClient.getTransportClient().prepareSearch();
        builder.setIndices((String[]) searchCondition.getIndexes().toArray())
                .setTypes((String[]) searchCondition.getTypes().toArray())
                .setQuery(searchCondition.getQueryBuilder())
                .setFrom(searchCondition.getFrom())
                .setSize(searchCondition.getSize())
                .setSearchType(searchCondition.getSearchType())
                .setExplain(searchCondition.getExplain());

        if (CollectionUtils.isNotEmpty(searchCondition.getSortBuilders())) {
            searchCondition.getSortBuilders().forEach(sortBuilder -> builder.addSort(sortBuilder));
        }

        SearchResponse response = builder.get();    //.execute().actionGet()

        return response;
    }

    @Override
    public SearchResponse searchUsingScroll(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        SearchRequestBuilder builder = esClient.getTransportClient().prepareSearch();
        builder.setIndices((String[]) searchCondition.getIndexes().toArray())
                .setTypes((String[]) searchCondition.getTypes().toArray())
                .setScroll(new TimeValue(60000))
                .setSize(searchCondition.getSize())          //max of SIZE hits will be returned for each scroll
                .setExplain(searchCondition.getExplain());


        SearchResponse response = builder.get();        //.execute().actionGet();

        return response;
    }

    @Override
    public List<SearchHit> searchUsingScrollAllHits(SearchCondition searchCondition) {
        Preconditions.checkNotNull(searchCondition, "searchCondition is null!");

        List<SearchHit> result = Lists.newArrayList();

        SearchRequestBuilder builder = esClient.getTransportClient().prepareSearch();
        builder.setIndices((String[]) searchCondition.getIndexes().toArray())
                .setTypes((String[]) searchCondition.getTypes().toArray())
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))
                .setQuery(QueryBuilders.termQuery("", ""))
                .setExplain(searchCondition.getExplain());
        SearchResponse response = builder.get();

        //Scroll until no hits are returned
        do {
            for (SearchHit hit : response.getHits().getHits()) {
                //Handle the hit...
                result.add(hit);
            }

            response = esClient.getTransportClient().prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();

        } while (response.getHits().getHits().length != 0);

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
}
