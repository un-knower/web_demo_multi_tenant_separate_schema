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

public enum IncidentDestinationType {
    
    TO(1, "incident_destination_to"),
    CC(2, "incident_destination_cc"),
    BCC(3, "incident_destination_bcc");
    
    public static final List<IncidentDestinationType> ALL_INCIDENT_DESTINATION_TYPES;
    static {
        ALL_INCIDENT_DESTINATION_TYPES = ImmutableList.of((TO), (CC), (BCC));
    }
    
    private int id;
    private String value;

    private IncidentDestinationType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentDestinationType get(int id) {
        for (IncidentDestinationType destinationType : IncidentDestinationType.values()) {
            if (destinationType.getId() != id) continue;
            return destinationType;
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

