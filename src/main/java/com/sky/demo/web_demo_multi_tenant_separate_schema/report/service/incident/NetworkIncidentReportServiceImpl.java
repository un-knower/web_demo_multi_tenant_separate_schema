package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.acc.NetworkIncidentReportAcc;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.FilterIncidentIdForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base.BaseNetworkIncidentReportService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.IncidentReportFilterForEsUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
    public List<NetworkIncident> getAllFilteredNetworkIncidents(IncidentReportFilterForm filterForm) {
        List<NetworkIncident> networkIncidents = null;

        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(filterForm);
        try {
//            reportCondition.put("ORDER_BY", "tb.id desc ");
//            reportCondition.put("COLUMN_SELECTED", "* ");
            logger.info("getAllFilteredNetworkIncidents():====> size:{}" + networkIncidents.size());
        }
        catch (Exception e) {
            logger.error("An error occurred when getting network incidents by incident report form", e);
        }
        return networkIncidents;
    }

    @Override
    public int queryCountOfNetworkIncident(IncidentReportFilterForm filterForm) {
        return 0;
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
        return null;
    }

    @Override
    public List<NetworkIncident> getIncidentsByIds(List<Long> ids) {
        return null;
    }

    @Override
    public List<NetworkIncident> getIncidentsByTransactionIds(List<String> transactionIds) {
        return null;
    }


}
