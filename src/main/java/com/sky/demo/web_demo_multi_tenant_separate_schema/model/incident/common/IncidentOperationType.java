/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;


import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentOperationType {

    NOTICE(0, "incident_operation_notice"),
    COMMENTS(5, "incident_operation_comments"),
    RELEASE(6, "incident_operation_release"),
    NOTICE_MANAGER(7, "incident_operation_notice_manager"),
    NOTICE_OTHERS(8, "incident_operation_notice_others"),
    TAG(12, "incident_operation_tag"),
    IGNORE(13, "incident_operation_ignore");
    
    private int id;
    private String value;

    private IncidentOperationType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentOperationType get(int id) {
        for (IncidentOperationType operationType : IncidentOperationType.values()) {
            if (operationType.getId() != id) continue;
            return operationType;
        }
        return null;
    }

    public String getDisplayName() {
        return Localizer.getMessage((String) this.getValue());
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

