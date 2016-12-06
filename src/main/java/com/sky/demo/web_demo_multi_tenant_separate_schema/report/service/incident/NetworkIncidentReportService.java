package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;

import java.util.List;

/**
 * Created by user on 16/12/2.
 */
public interface NetworkIncidentReportService {

//    public PageView<NetworkIncident> queryNetworkIncident(PageContext var1, IncidentReportFilterForm var2);

    public List<NetworkIncident> getAllFilteredNetworkIncidents(IncidentReportFilterForm filterForm);

    public long queryCountOfNetworkIncident(IncidentReportFilterForm filterForm);


    public NetworkIncident getIncidentById(long id);

    public NetworkIncident getIncidentByTransactionId(String transactionId);

    public List<NetworkIncident> getIncidentsByIds(List<Long> ids);

    public List<NetworkIncident> getIncidentsByTransactionIds(List<String> transactionIds);

//    public PageView<NetworkIncident> searchFilteredIncidents(IncidentFilter var1, PageContext var3);


}
