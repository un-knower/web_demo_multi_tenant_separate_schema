/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 *  com.sky.sps.base.common.base.Relation
 *  com.sky.sps.base.common.base.RelationColumn
 *  com.sky.sps.base.common.echarts.series.SeriesType
 *  javax.validation.constraints.Size
 *  org.hibernate.validator.constraints.NotBlank
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.github.abel533.echarts.code.SeriesType;
import com.google.common.base.Objects;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.IncidentReport;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.ReportBuildType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.ReportType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class IncidentReportForm
implements Serializable {
    private static final long serialVersionUID = -5303166722804522896L;
    private Integer id;
    @NotBlank(message="\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    @Size(max=32, message="\u957f\u5ea6\u5e94\u5c0f\u4e8e32")
    private String name;
    @Size(max=255, message="\u957f\u5ea6\u5e94\u5c0f\u4e8e255")
    private String remark;
    private IncidentType incidentType;
    private ReportType reportType;
    private int showLimit;
    private IncidentReportFilterForm filter;
    private List<Integer> columns;
    private IncidentReport.Status status;
    private ReportBuildType reportBuildType;
    private List<IncidentDashboardType> dashboardTypes;
    private int roleId;
    private String lastUpdatedBy;
    private String lastUpdated;
    private int scheduleTaskCount;
    private SeriesType seriesType;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public IncidentType getIncidentType() {
        return this.incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public ReportType getReportType() {
        return this.reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public int getShowLimit() {
        return this.showLimit;
    }

    public void setShowLimit(int showLimit) {
        this.showLimit = showLimit;
    }

    public IncidentReportFilterForm getFilter() {
        return this.filter;
    }

    public void setFilter(IncidentReportFilterForm filter) {
        this.filter = filter;
    }

    public List<Integer> getColumns() {
        return this.columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }

    public IncidentReport.Status getStatus() {
        return this.status;
    }

    public void setStatus(IncidentReport.Status status) {
        this.status = status;
    }

    public ReportBuildType getReportBuildType() {
        return this.reportBuildType;
    }

    public void setReportBuildType(ReportBuildType reportBuildType) {
        this.reportBuildType = reportBuildType;
    }

    public List<IncidentDashboardType> getDashboardTypes() {
        return this.dashboardTypes;
    }

    public void setDashboardTypes(List<IncidentDashboardType> dashboardTypes) {
        this.dashboardTypes = dashboardTypes;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getScheduleTaskCount() {
        return this.scheduleTaskCount;
    }

    public void setScheduleTaskCount(int scheduleTaskCount) {
        this.scheduleTaskCount = scheduleTaskCount;
    }

    public SeriesType getSeriesType() {
        return this.seriesType;
    }

    public void setSeriesType(SeriesType seriesType) {
        this.seriesType = seriesType;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("id", (Object)this.id).add("name", (Object)this.name).add("remark", (Object)this.remark).add("incidentType", (Object)this.incidentType).add("reportType", (Object)this.reportType).add("showLimit", this.showLimit).add("filter", (Object)this.filter).add("columns", this.columns).add("status", (Object)this.status).add("reportBuildType", (Object)this.reportBuildType).add("dashboardTypes", this.dashboardTypes).add("roleId", this.roleId).add("lastUpdatedBy", (Object)this.lastUpdatedBy).add("lastUpdated", (Object)this.lastUpdated).add("scheduleTaskCount", this.scheduleTaskCount).add("seriesType", (Object)this.seriesType).toString();
    }


    public static void main(String[] args) {
        IncidentReportForm incidentReportForm = new IncidentReportForm();

        incidentReportForm.setId(1);
        incidentReportForm.setName("Policy");
        incidentReportForm.setRemark("remark");
        incidentReportForm.setIncidentType(IncidentType.NETWORK);
        incidentReportForm.setReportType(ReportType.DASHBOARD);
        incidentReportForm.setShowLimit(10);

        IncidentReportFilterForm filterForm = new IncidentReportFilterForm();
        FilterTimeForm timeForm = new FilterTimeForm();
        timeForm.setEnableFilter(true);
        timeForm.setTimeType(PeriodTimeType.LAST_PAST_N_DAY);
        timeForm.setLastPastDay(100);
        filterForm.setTime(timeForm);
        incidentReportForm.setFilter(filterForm);


        System.out.println(JsonUtil.writeValueAsString(incidentReportForm));


    }

}

