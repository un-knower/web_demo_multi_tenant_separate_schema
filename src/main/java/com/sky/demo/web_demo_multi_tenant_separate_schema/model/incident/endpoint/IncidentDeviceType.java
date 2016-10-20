/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.endpoint;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentDeviceType {
    
    UNKNOWN(0, "incident_device_unknown"),
    DESKTOP(1, "incident_device_desktop"),
    LAPTOP(2, "incident_device_laptop");
    
    public static final List<IncidentDeviceType> ALL_INCIDENT_DEVICE_TYPES;
    static {
        ALL_INCIDENT_DEVICE_TYPES = ImmutableList.of((DESKTOP), (LAPTOP));
    }
   
    private int id;
    private String value;

    private IncidentDeviceType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentDeviceType get(int id) {
        for (IncidentDeviceType deviceType : IncidentDeviceType.values()) {
            if (deviceType.getId() != id) continue;
            return deviceType;
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

