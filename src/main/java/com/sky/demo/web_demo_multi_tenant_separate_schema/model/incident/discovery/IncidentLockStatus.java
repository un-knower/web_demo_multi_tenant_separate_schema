/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentLockStatus {

    NOT_LOCKED(0, "incident_lock_not_locked"),
    IS_LOCKED(1, "incident_lock_is_locked");
    
    public static final List<IncidentLockStatus> ALL_INCIDENT_LOCK_STATUSES;
    static {
        ALL_INCIDENT_LOCK_STATUSES = ImmutableList.of((NOT_LOCKED), (IS_LOCKED));
    }
    
    
    private int id;
    private String value;

    private IncidentLockStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentLockStatus get(int id) {
        for (IncidentLockStatus lockStatus : IncidentLockStatus.values()) {
            if (lockStatus.getId() != id) continue;
            return lockStatus;
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

