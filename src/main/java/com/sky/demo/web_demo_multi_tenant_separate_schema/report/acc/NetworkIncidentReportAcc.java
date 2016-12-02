package com.sky.demo.web_demo_multi_tenant_separate_schema.report.acc;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/12/2.
 */
public interface NetworkIncidentReportAcc {

    public List<NetworkIncident> selectListOfNetworkIncident(QueryCondition queryCondition);

    public int selectCountOfNetworkIncident(QueryCondition queryCondition);

    public NetworkIncident selectNetworkIncident(QueryCondition queryCondition, Map<String, Object> condition);
}