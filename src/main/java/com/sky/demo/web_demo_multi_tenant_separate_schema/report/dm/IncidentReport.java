/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm;

import com.google.common.base.Objects;

import java.util.Date;

public class IncidentReport {
    private int id;
    private String name;
    private String remark;
    private int reportType;
    private int incidentType;
    private int showLimit;
    private String filter;
    private String columns;
    private int status;
    private int reportBuildType;
    private String dashboardTypes;
    private int roleId;
    private String lastUpdatedBy;
    private Date lastUpdated;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public int getReportType() {
        return this.reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getIncidentType() {
        return this.incidentType;
    }

    public void setIncidentType(int incidentType) {
        this.incidentType = incidentType;
    }

    public int getShowLimit() {
        return this.showLimit;
    }

    public void setShowLimit(int showLimit) {
        this.showLimit = showLimit;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getColumns() {
        return this.columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReportBuildType() {
        return this.reportBuildType;
    }

    public void setReportBuildType(int reportBuildType) {
        this.reportBuildType = reportBuildType;
    }

    public String getDashboardTypes() {
        return this.dashboardTypes;
    }

    public void setDashboardTypes(String dashboardTypes) {
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

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("id", this.id).add("name", (Object)this.name).add("remark", (Object)this.remark).add("reportType", this.reportType).add("incidentType", this.incidentType).add("showLimit", this.showLimit).add("filter", (Object)this.filter).add("columns", (Object)this.columns).add("status", this.status).add("reportBuildType", this.reportBuildType).add("dashboardTypes", (Object)this.dashboardTypes).add("roleId", this.roleId).add("lastUpdatedBy", (Object)this.lastUpdatedBy).add("lastUpdated", (Object)this.lastUpdated).toString();
    }

    public static enum Status {
        NORMAL(1, ""),
        DELETED(2, "");
        
        private int code;
        private String desc;

        private Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static Status getStatusByCode(int code) {
            for (Status status : Status.values()) {
                if (status.getCode() != code) continue;
                return status;
            }
            return null;
        }
    }

}

