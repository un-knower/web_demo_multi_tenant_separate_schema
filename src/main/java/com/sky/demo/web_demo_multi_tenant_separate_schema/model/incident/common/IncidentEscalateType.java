/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;


import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentEscalateType {

    ESCALATE_TO_MANAGER(1, "incident_escalate_to_manager"),
    ESCALATE_TO_OTHERS(2, "incident_escalate_to_others");
    
    private int id;
    private String value;

    private IncidentEscalateType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentEscalateType get(int id) {
        for (IncidentEscalateType escalateType : IncidentEscalateType.values()) {
            if (escalateType.getId() != id) continue;
            return escalateType;
        }
        return null;
    }

    public String getDisplayName() {
        return Localizer.getMessage((String) this.getValue());
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

