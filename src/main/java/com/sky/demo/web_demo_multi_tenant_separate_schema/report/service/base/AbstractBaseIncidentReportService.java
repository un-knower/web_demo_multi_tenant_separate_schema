/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.skyguard.sps.base.common.echarts.base.EchartsForm
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentDashboardType
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentReportForm
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentDashboardType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;


import java.util.List;

public abstract class AbstractBaseIncidentReportService {
    public List<EchartsForm> queryListAllInOne(IncidentDashboardType dashboardType, IncidentReportForm form) {
        Preconditions.checkNotNull(dashboardType, "dashboard type is null!");
        List<EchartsForm> echartsForms = this.getEcharsForms(dashboardType, form);
        return echartsForms;
    }

    protected abstract List<EchartsForm> getEcharsForms(IncidentDashboardType var1, IncidentReportForm var2);

    protected QueryCondition initQueryCondition() {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setIndex("tenant1");  //DBContext.getDbKey()

        return queryCondition;
    }
}

