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

public enum IncidentReleaseStatus {
    
    NOT_RELEASED(0, "incident_release_not_released"),
    IS_RELEASED(1, "incident_release_is_released");
    
    public static final List<IncidentReleaseStatus> ALL_INCIDENT_RELEASE_STATUSES;
    static {
        ALL_INCIDENT_RELEASE_STATUSES = ImmutableList.of((NOT_RELEASED), (IS_RELEASED));
    }
    
    private int id;
    private String value;

    private IncidentReleaseStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentReleaseStatus get(int id) {
        for (IncidentReleaseStatus releaseStatus : IncidentReleaseStatus.values()) {
            if (releaseStatus.getId() != id) continue;
            return releaseStatus;
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

