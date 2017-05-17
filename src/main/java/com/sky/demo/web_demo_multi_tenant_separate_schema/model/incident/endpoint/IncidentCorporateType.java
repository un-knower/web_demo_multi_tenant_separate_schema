/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.endpoint;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentCorporateType {
    
    UNKNOWN(0, "incident_corporate_unknown"),
    INTERNAL(1, "incident_corporate_internal"),
    EXTERNAL(2, "incident_corporate_external");
    
    public static final List<IncidentCorporateType> ALL_INCIDENT_LOCATION_TYPES;
    static {
        ALL_INCIDENT_LOCATION_TYPES = ImmutableList.of((INTERNAL), (EXTERNAL));
    }
   
    private int id;
    private String value;

    private IncidentCorporateType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentCorporateType get(int id) {
        for (IncidentCorporateType locationType : IncidentCorporateType.values()) {
            if (locationType.getId() != id) continue;
            return locationType;
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

