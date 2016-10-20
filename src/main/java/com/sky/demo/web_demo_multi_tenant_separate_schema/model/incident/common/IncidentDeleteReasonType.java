/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;


import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentDeleteReasonType {

    FALSE_POSITIVE(1, "incident_delete_false_positive"),
    RESOLVED(2, "incident_delete_resolved"),
    NOT_RELEVANT(3, "incident_delete_no_relevant"),
    ARCHIVED(4, "incident_delete_archived"),
    OTHER(5, "incident_delete_other");
    
    private int id;
    private String value;

    private IncidentDeleteReasonType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentDeleteReasonType get(int id) {
        for (IncidentDeleteReasonType reasonType : IncidentDeleteReasonType.values()) {
            if (reasonType.getId() != id) continue;
            return reasonType;
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
}

