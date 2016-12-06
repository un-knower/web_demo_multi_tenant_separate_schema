package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.BaseQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.acc.NetworkIncidentReportAcc;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.FilterIncidentIdForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.FilterTransactionIdForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base.BaseNetworkIncidentReportService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.IncidentReportFilterForEsUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/12/2.
 */
@Service
public class NetworkIncidentReportServiceImpl extends AbstractIncidentReportService implements NetworkIncidentReportService {

    private static final Logger logger = LoggerFactory.getLogger(NetworkIncidentReportServiceImpl.class);

    @Resource
    private BaseNetworkIncidentReportService baseNetworkIncidentReportService;

    @Resource
    private NetworkIncidentReportAcc networkIncidentReportAcc;


    @Override
    public Pager<NetworkIncident> queryNetworkIncident(BaseQueryRequest queryRequest, IncidentReportFilterForm filterForm) {

        Pager<NetworkIncident> pager = new Pager<NetworkIncident>(queryRequest.getPageNumber(), queryRequest.getPageSize());

        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom((int)pager.getOffset());
        queryCondition.setSize(queryRequest.getPageSize());

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        List<SortBuilder> sortBuilders = Lists.newArrayList();
        SortBuilder sortBuilder = SortBuilders.fieldSort("detectTime")
                .order(SortOrder.DESC);
        sortBuilders.add(sortBuilder);
        queryCondition.setSortBuilders(sortBuilders);

        List<NetworkIncident> networkIncidents = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            if (searchHits.totalHits() > 0) {
                searchHits.forEach(hit -> {
                    NetworkIncident networkIncident = JsonUtil.readValue(hit.getSourceAsString(), NetworkIncident.class);
                    networkIncidents.add(networkIncident);
                });
            }

            pager.setTotalRecords(searchHits.getTotalHits());
            pager.setRows(networkIncidents);
        }
        return pager;
    }

    @Override
    public List<NetworkIncident> getAllFilteredNetworkIncidents(IncidentReportFilterForm filterForm) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom(0);
        queryCondition.setSize(100);

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        List<NetworkIncident> networkIncidents = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            if (searchHits.totalHits() > 0) {
                searchHits.forEach(hit -> {
                    NetworkIncident networkIncident = JsonUtil.readValue(hit.getSourceAsString(), NetworkIncident.class);
                    networkIncidents.add(networkIncident);
                });
            }
        }
        return networkIncidents;
    }

    @Override
    public long queryCountOfNetworkIncident(IncidentReportFilterForm filterForm) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom(0);
        queryCondition.setSize(1);

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        long count = 0;
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            count = searchHits.totalHits();
        }
        return count;
    }

    @Override
    public NetworkIncident getIncidentById(long id) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom(0);
        queryCondition.setSize(1);

        IncidentReportFilterForm filterForm = new IncidentReportFilterForm();
        FilterIncidentIdForm incidentIdForm = new FilterIncidentIdForm();
        incidentIdForm.setEnableFilter(true);
        incidentIdForm.setIds(String.valueOf(id));
        filterForm.setIncidentId(incidentIdForm);

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        NetworkIncident networkIncident = null;
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            if (searchHits.totalHits() > 0 && searchHits.getAt(0) != null) {
                String source = searchHits.getAt(0).getSourceAsString();
                networkIncident = JsonUtil.readValue(source, NetworkIncident.class);
            }
        }
        return networkIncident;
    }

    @Override
    public NetworkIncident getIncidentByTransactionId(String transactionId) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom(0);
        queryCondition.setSize(1);

        IncidentReportFilterForm filterForm = new IncidentReportFilterForm();
        FilterTransactionIdForm transactionIdForm = new FilterTransactionIdForm();
        transactionIdForm.setEnableFilter(true);
        transactionIdForm.setIds(transactionId);
        filterForm.setTransactionId(transactionIdForm);

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        NetworkIncident networkIncident = null;
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            if (searchHits.totalHits() > 0 && searchHits.getAt(0) != null) {
                String source = searchHits.getAt(0).getSourceAsString();
                networkIncident = JsonUtil.readValue(source, NetworkIncident.class);
            }
        }
        return networkIncident;
    }

    @Override
    public List<NetworkIncident> getIncidentsByIds(List<Long> ids) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
//        queryCondition.setFrom(0);
//        queryCondition.setSize(1);

        IncidentReportFilterForm filterForm = new IncidentReportFilterForm();
        FilterIncidentIdForm incidentIdForm = new FilterIncidentIdForm();
        incidentIdForm.setEnableFilter(true);
        incidentIdForm.setIds(Joiner.on(",").skipNulls().join(ids));
        filterForm.setIncidentId(incidentIdForm);

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        List<NetworkIncident> networkIncidents = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            if (searchHits.totalHits() > 0) {
                searchHits.forEach(hit -> {
                    NetworkIncident networkIncident = JsonUtil.readValue(hit.getSourceAsString(), NetworkIncident.class);
                    networkIncidents.add(networkIncident);
                });
            }
        }
        return networkIncidents;
    }

    @Override
    public List<NetworkIncident> getIncidentsByTransactionIds(List<String> transactionIds) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
//        queryCondition.setFrom(0);
//        queryCondition.setSize(1);

        IncidentReportFilterForm filterForm = new IncidentReportFilterForm();
        FilterTransactionIdForm transactionIdForm = new FilterTransactionIdForm();
        transactionIdForm.setEnableFilter(true);
        transactionIdForm.setIds(Joiner.on(",").skipNulls().join(transactionIds));
        filterForm.setTransactionId(transactionIdForm);

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        queryCondition.setBoolQueryMusts(queryBuilders);

        List<NetworkIncident> networkIncidents = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.selectNetworkIncident(queryCondition);
        if (response != null) {
            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            if (searchHits.totalHits() > 0) {
                searchHits.forEach(hit -> {
                    NetworkIncident networkIncident = JsonUtil.readValue(hit.getSourceAsString(), NetworkIncident.class);
                    networkIncidents.add(networkIncident);
                });
            }
        }
        return networkIncidents;
    }


}
