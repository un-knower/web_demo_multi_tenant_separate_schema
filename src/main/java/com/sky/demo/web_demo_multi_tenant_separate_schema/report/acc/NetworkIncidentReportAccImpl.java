package com.sky.demo.web_demo_multi_tenant_separate_schema.report.acc;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.EsClient;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.util.QueryBuilderUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/12/2.
 */
@Component
public class NetworkIncidentReportAccImpl implements NetworkIncidentReportAcc {

    private static final Logger logger = LoggerFactory.getLogger(NetworkIncidentReportAccImpl.class);

    //@Resource
    private EsClient esClient;


    @Override
    public SearchResponse searchNetworkIncident(QueryCondition queryCondition) {
        Preconditions.checkNotNull(queryCondition, "queryCondition is null!");
        logger.info("QueryCondition:\n{}", JsonUtil.writeValueAsString(queryCondition));

        SearchRequestBuilder searchRequestBuilder = esClient.getTransportClient()
                .prepareSearch(queryCondition.getIndex())
                .setTypes(queryCondition.getType())
                .setFrom(queryCondition.getFrom() == null ? 0 : queryCondition.getFrom());

        if (queryCondition.getSize() != null) {
            searchRequestBuilder.setSize(queryCondition.getSize());
        }

        if (CollectionUtils.isNotEmpty(queryCondition.getFetchSourceIncludes())) {
            searchRequestBuilder.setFetchSource(queryCondition.getFetchSourceIncludes().stream().toArray(String[]::new), null);
        }

        if (CollectionUtils.isNotEmpty(queryCondition.getStoreFields())) {
            queryCondition.getStoreFields().forEach(storeField -> searchRequestBuilder.addStoredField(storeField));
        }

        if (CollectionUtils.isNotEmpty(queryCondition.getBoolQueryMusts())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (CollectionUtils.isNotEmpty(queryCondition.getBoolQueryMusts())) {
                queryCondition.getBoolQueryMusts().forEach(must -> queryBuilder.must(must));
            }
            logger.debug("query builder: {}", queryBuilder);
            searchRequestBuilder.setQuery(queryBuilder);
        }

        if (CollectionUtils.isNotEmpty(queryCondition.getSortBuilders())) {
            logger.debug("sort builders: {}", queryCondition.getSortBuilders());
            queryCondition.getSortBuilders().forEach(sortBuilder -> searchRequestBuilder.addSort(sortBuilder));
        }

        if (CollectionUtils.isNotEmpty(queryCondition.getAggregationBuilders())) {
            logger.debug("aggregation builders: {}", JsonUtil.writeValueAsString(queryCondition.getAggregationBuilders()));
            queryCondition.getAggregationBuilders().forEach(agg -> searchRequestBuilder.addAggregation(agg));
        }

        SearchResponse response = null;
        try {
            response = searchRequestBuilder.get();
        } catch (Exception e) {
            logger.error("get from es error", e);
        }
        return response;
    }
}
