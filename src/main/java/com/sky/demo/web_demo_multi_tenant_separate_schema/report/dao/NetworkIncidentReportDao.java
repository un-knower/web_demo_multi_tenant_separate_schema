/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.echarts.base.EchartsForm
 *  com.skyguard.sps.dlp.common.incident.dm.network.NetworkIncident
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao;


import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;

import java.util.List;
import java.util.Map;

public interface NetworkIncidentReportDao {
    public List<EchartsForm> selectListOfPolicy(Map<String, Object> var1);

    public List<EchartsForm> selectListOfSource(Map<String, Object> var1);

    public List<EchartsForm> selectListOfDestination(Map<String, Object> var1);

    public List<EchartsForm> selectListOfChannelType(Map<String, Object> var1);

    public List<EchartsForm> selectListOfSeverityType(Map<String, Object> var1);

    public List<EchartsForm> selectListOfActionType(Map<String, Object> var1);

    public List<EchartsForm> selectListOfPolicyGroup(Map<String, Object> var1);

    public List<EchartsForm> selectListOfStatusType(Map<String, Object> var1);

    public List<EchartsForm> selectTrendListOfSeverityType(Map<String, Object> var1);

    public List<EchartsForm> selectTrendListOfPolicyDetectTime(Map<String, Object> var1);

    public List<NetworkIncident> selectListOfNetworkIncident(Map<String, Object> var1);

    public int selectCountOfNetworkIncident(Map<String, Object> var1);
}

