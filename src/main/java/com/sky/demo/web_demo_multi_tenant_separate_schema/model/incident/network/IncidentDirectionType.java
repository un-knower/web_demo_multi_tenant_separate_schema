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

public enum IncidentDirectionType {
    
    INBOUND(1, "incident_direction_inbound"),
    OUTBOUND(2, "incident_direction_outbound"),
    INTERNAL(3, "incident_direction_internal"),
    ALL(4, "incident_direction_all");
    
    public static final List<IncidentDirectionType> ALL_INCIDENT_DIRECTION_TYPES;
    static {
        ALL_INCIDENT_DIRECTION_TYPES = ImmutableList.of((INBOUND), (OUTBOUND), (INTERNAL), (ALL));
    }
    
    
    private int id;
    private String value;

    private IncidentDirectionType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentDirectionType get(int id) {
        for (IncidentDirectionType directionType : IncidentDirectionType.values()) {
            if (directionType.getId() != id) continue;
            return directionType;
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

