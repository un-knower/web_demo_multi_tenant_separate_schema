/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentStatusType {
    
    NEW(1, "incident_status_new"),
    IN_PROCESS(2, "incident_status_in_process"),
    CLOSED(3, "incident_status_closed"),
    FALSE_POSITIVE(4, "incident_status_false_positive"),
    ESCALATED(5, "incident_status_escalated");
    
    public static final List<IncidentStatusType> ALL_INCIDENT_STATUS_TYPES;

    static {
        ALL_INCIDENT_STATUS_TYPES = ImmutableList.of((NEW), (IN_PROCESS), (CLOSED), (FALSE_POSITIVE), (ESCALATED));
    }
    
    private int id;
    private String value;

    private IncidentStatusType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentStatusType get(int id) {
        for (IncidentStatusType statusType : IncidentStatusType.values()) {
            if (statusType.getId() != id) continue;
            return statusType;
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

