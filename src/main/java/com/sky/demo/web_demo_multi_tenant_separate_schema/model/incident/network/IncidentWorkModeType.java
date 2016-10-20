/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentWorkModeType {
    
    MONITOR_MODE(1, "incident_work_mode_monitor"),
    BLOCK_MODE(2, "incident_work_mode_block");
    
    public static final List<IncidentWorkModeType> ALL_INCIDENT_WORK_MODE_TYPES;
    static {
        ALL_INCIDENT_WORK_MODE_TYPES = ImmutableList.of((MONITOR_MODE), (BLOCK_MODE));
    }
    
    private int id;
    private String value;

    private IncidentWorkModeType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentWorkModeType get(int id) {
        for (IncidentWorkModeType workModeType : IncidentWorkModeType.values()) {
            if (workModeType.getId() != id) continue;
            return workModeType;
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

