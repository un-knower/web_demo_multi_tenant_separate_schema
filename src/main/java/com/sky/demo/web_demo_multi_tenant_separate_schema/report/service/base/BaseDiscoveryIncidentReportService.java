/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.sky.sps.base.common.echarts.base.EchartsForm
 *  com.sky.sps.dlp.common.report.dm.dto.IncidentReportForm
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;



import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;

import java.util.List;

public interface BaseDiscoveryIncidentReportService
extends IBaseIncidentReportService {
    public List<EchartsForm> queryListOfPolicy(IncidentReportForm var1);

    public List<EchartsForm> queryListOfResourceType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfSeverityType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfTask(IncidentReportForm var1);

    public List<EchartsForm> queryListOfDiscoveryStatistics(IncidentReportForm var1);

    public List<EchartsForm> queryListOfStatusType(IncidentReportForm var1);
}

