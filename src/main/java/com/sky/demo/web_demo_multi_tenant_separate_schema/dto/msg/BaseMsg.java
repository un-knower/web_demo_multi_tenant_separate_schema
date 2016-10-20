package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.msg;

import java.io.Serializable;

/**
 * Created by user on 16/10/20.
 */
public abstract class BaseMsg implements Serializable {

    private static final long serialVersionUID = 4258116414658506710L;
    private String tenantCode;
    private String tenantType;
    private String incidentType;

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    @Override
    public String toString() {
        return "BaseMsg{" +
                "tenantCode='" + tenantCode + '\'' +
                ", tenantType='" + tenantType + '\'' +
                ", incidentType='" + incidentType + '\'' +
                '}';
    }
}
