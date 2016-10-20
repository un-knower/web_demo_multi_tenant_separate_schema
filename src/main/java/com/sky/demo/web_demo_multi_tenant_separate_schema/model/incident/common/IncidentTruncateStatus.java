/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

public enum IncidentTruncateStatus {
    NOT_TRUNCATED(0, false),
    IS_TRUNCATED(1, true);
    
    private int id;
    private boolean value;

    private IncidentTruncateStatus(int id, boolean value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentTruncateStatus get(int id) {
        for (IncidentTruncateStatus truncateStatus : IncidentTruncateStatus.values()) {
            if (truncateStatus.getId() != id) continue;
            return truncateStatus;
        }
        return null;
    }

    public static IncidentTruncateStatus get(boolean value) {
        for (IncidentTruncateStatus truncateStatus : IncidentTruncateStatus.values()) {
            if (truncateStatus.getValue() != value) continue;
            return truncateStatus;
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public boolean getValue() {
        return this.value;
    }
}

