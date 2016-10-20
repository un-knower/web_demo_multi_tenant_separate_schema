/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class IncidentHistory implements Serializable {

    private static final long serialVersionUID = -1539714378201237083L;
    private long id;
    private long incidentId;
    private IncidentHistoryOperationType operationType;
    private String operationParams;
    private String comments;
    private String adminName;
    private String localeHistoryTime;
    private Timestamp historyTime;
    private String operationDescription;

    public String getAdminName() {
        return this.adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public IncidentHistoryOperationType getOperationType() {
        return this.operationType;
    }

    public void setOperationType(IncidentHistoryOperationType operationType) {
        this.operationType = operationType;
    }

    public String getOperationParams() {
        return this.operationParams;
    }

    public void setOperationParams(String operationParams) {
        this.operationParams = operationParams;
    }

    public Timestamp getHistoryTime() {
        return this.historyTime;
    }

    public void setHistoryTime(Timestamp historyTime) {
        this.historyTime = historyTime;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    public String getLocaleHistoryTime() {
        return this.localeHistoryTime;
    }

    public void setLocaleHistoryTime(String localeHistoryTime) {
        this.localeHistoryTime = localeHistoryTime;
    }

    public String getOperationDescription() {
        return this.operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    @Override
    public String toString() {
        return "IncidentHistory{" +
                "id=" + id +
                ", incidentId=" + incidentId +
                ", operationType=" + operationType +
                ", operationParams='" + operationParams + '\'' +
                ", comments='" + comments + '\'' +
                ", adminName='" + adminName + '\'' +
                ", localeHistoryTime='" + localeHistoryTime + '\'' +
                ", historyTime=" + historyTime +
                ", operationDescription='" + operationDescription + '\'' +
                '}';
    }
}

