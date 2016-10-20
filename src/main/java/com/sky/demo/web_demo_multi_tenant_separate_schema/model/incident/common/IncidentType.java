/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

public enum IncidentType {

    NETWORK(1, "incident_network", "network"),
    DISCOVERY(2, "incident_discovery", "discovery"),
    ENDPOINT(3, "incident_endpoint", "endpoint");
    
    private int id;
    private String value;
    private String name;

    private IncidentType(int id, String value, String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }

    public static IncidentType get(int id) {
        for (IncidentType incidentType : IncidentType.values()) {
            if (incidentType.getId() != id) continue;
            return incidentType;
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}

