package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.BaseQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;

import java.util.List;

/**
 * Created by user on 16/12/2.
 */
public interface NetworkIncidentReportService {

    public Pager<NetworkIncident> queryNetworkIncident(BaseQueryRequest queryRequest, IncidentReportFilterForm filterForm);

    public List<NetworkIncident> getAllFilteredNetworkIncidents(IncidentReportFilterForm filterForm);

    public long queryCountOfNetworkIncident(IncidentReportFilterForm filterForm);


    public NetworkIncident getIncidentById(long id);

    public NetworkIncident getIncidentByTransactionId(String transactionId);

    public List<NetworkIncident> getIncidentsByIds(List<Long> ids);

    public List<NetworkIncident> getIncidentsByTransactionIds(List<String> transactionIds);


}
