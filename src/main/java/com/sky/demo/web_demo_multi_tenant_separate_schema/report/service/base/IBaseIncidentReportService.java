/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.echarts.base.EchartsForm
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentDashboardType
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentReportForm
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;



import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentDashboardType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;

import java.util.List;

public interface IBaseIncidentReportService {
    public List<EchartsForm> queryListAllInOne(IncidentDashboardType var1, IncidentReportForm var2);
}

