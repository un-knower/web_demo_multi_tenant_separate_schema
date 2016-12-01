/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.echarts.base.EchartsForm
 *  com.skyguard.sps.dlp.common.incident.dm.discovery.DiscoveryIncident
 *  com.skyguard.sps.dlp.common.report.dm.dto.DiscoveryIncidentOption
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao;


import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery.DiscoveryIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.DiscoveryIncidentOption;

import java.util.List;
import java.util.Map;

public interface DiscoveryIncidentReportDao {
    public List<EchartsForm> selectListOfPolicy(Map<String, Object> var1);

    public List<EchartsForm> selectListOfResourceType(Map<String, Object> var1);

    public List<EchartsForm> selectListOfSeverityType(Map<String, Object> var1);

    public List<EchartsForm> selectListOfTask(Map<String, Object> var1);

    public List<EchartsForm> selectListOfDiscoveryStatistics(Map<String, Object> var1);

    public List<EchartsForm> selectListOfStatusType(Map<String, Object> var1);

    public List<DiscoveryIncidentOption> selectListOfTaskName(Map<String, Object> var1);

    public List<DiscoveryIncidentOption> selectListOfTaskNameAndResourceType(Map<String, Object> var1);

    public List<DiscoveryIncidentOption> selectListOfSourceAddress(Map<String, Object> var1);

    public List<DiscoveryIncident> selectListOfDiscoveryIncident(Map<String, Object> var1);

    public int selectCountOfDiscoveryIncident(Map<String, Object> var1);
}

