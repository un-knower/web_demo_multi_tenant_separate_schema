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

public enum IncidentIgnoreStatus {
    
    NOT_IGNORED(0, "incident_ignore_not_ignored"),
    IS_IGNORED(1, "incident_ignore_is_ignored");
    
    public static final List<IncidentIgnoreStatus> ALL_INCIDENT_IGNORE_STATUS;
    static {
        ALL_INCIDENT_IGNORE_STATUS = ImmutableList.of((NOT_IGNORED), (IS_IGNORED));
    }
    
    private int id;
    private String value;

    private IncidentIgnoreStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentIgnoreStatus get(int id) {
        for (IncidentIgnoreStatus ignoreStatus : IncidentIgnoreStatus.values()) {
            if (ignoreStatus.getId() != id) continue;
            return ignoreStatus;
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

