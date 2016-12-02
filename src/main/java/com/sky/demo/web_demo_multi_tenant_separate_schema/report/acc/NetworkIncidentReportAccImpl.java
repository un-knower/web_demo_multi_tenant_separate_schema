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
import org.elasticsearch.index.query.QueryBuilder;
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

    @Resource
    private EsClient esClient;


    @Override
    public List<NetworkIncident> selectListOfNetworkIncident(QueryCondition queryCondition) {
        Preconditions.checkNotNull(queryCondition, "queryCondition is null!");
        logger.info("QueryCondition:\n", JsonUtil.writeValueAsString(queryCondition));

        SearchRequestBuilder searchRequestBuilder = esClient.getTransportClient()
                .prepareSearch(queryCondition.getIndex())
                .setTypes(queryCondition.getType())
                .setFrom(queryCondition.getFrom())
                .setSize(queryCondition.getSize());

        if (CollectionUtils.isNotEmpty(queryCondition.getQueryBuilders())) {
            QueryBuilder queryBuilder = QueryBuilderUtil.buildBoolQuery(queryCondition.getQueryBuilders(), null ,null, null);

            logger.debug("query builder{}", queryBuilder);
            searchRequestBuilder.setQuery(queryBuilder);
        }

        SearchResponse response = searchRequestBuilder.get();


        return null;
    }

    @Override
    public int selectCountOfNetworkIncident(QueryCondition queryCondition) {
        Preconditions.checkNotNull(queryCondition, "queryCondition is null!");

        return 0;
    }

    @Override
    public NetworkIncident selectNetworkIncident(QueryCondition queryCondition, Map<String, Object> condition) {
        Preconditions.checkNotNull(queryCondition, "queryCondition is null!");

        return null;
    }
}
