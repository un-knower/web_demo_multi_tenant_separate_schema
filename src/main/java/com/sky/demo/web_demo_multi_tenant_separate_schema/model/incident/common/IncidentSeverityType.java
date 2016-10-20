/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentSeverityType {
    
    HIGH(1, "incident_severity_high", "#BF360C"),
    MEDIUM(2, "incident_severity_medium", "#EF6C00"),
    LOW(3, "incident_severity_low", "#FFCD40"),
    INFO(4, "incident_severity_info", "#90CAF9");
    
    public static final List<IncidentSeverityType> ALL_INCIDENT_SEVERITY_TYPES;

    static {
        ALL_INCIDENT_SEVERITY_TYPES = ImmutableList.of((HIGH), (MEDIUM), (LOW), (INFO));
    }
    
    
    private int id;
    private String value;
    private String color;

    private IncidentSeverityType(int id, String value, String color) {
        this.id = id;
        this.value = value;
        this.color = color;
    }

    public static IncidentSeverityType get(int id) {
        for (IncidentSeverityType severityType : IncidentSeverityType.values()) {
            if (severityType.getId() != id) continue;
            return severityType;
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

    public String getColor() {
        return this.color;
    }


}
