/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.echarts.base.EchartsForm
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentReportForm
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;


import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;

import java.util.List;

public interface BaseEndpointIncidentReportService
extends IBaseIncidentReportService {
    public List<EchartsForm> queryListOfPolicy(IncidentReportForm var1);

    public List<EchartsForm> queryListOfSource(IncidentReportForm var1);

    public List<EchartsForm> queryListOfDestination(IncidentReportForm var1);

    public List<EchartsForm> queryListOfChannelType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfSeverityType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfActionType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfPolicyGroup(IncidentReportForm var1);

    public List<EchartsForm> queryListOfStatusType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfCorporateType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfDeviceType(IncidentReportForm var1);

    public List<EchartsForm> queryListOfOperationSystem(IncidentReportForm var1);

    public List<EchartsForm> queryListOfRegisterDeviceType(IncidentReportForm var1);

    public List<EchartsForm> queryTrendListOfSeverityType(IncidentReportForm var1);

    public List<EchartsForm> queryTrendListOfPolicyDetectTime(IncidentReportForm var1);
}

