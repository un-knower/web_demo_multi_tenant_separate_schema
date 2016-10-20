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

public enum IncidentAgentType {

    ATS_AGENT(1, "incident_agent_ats"),
    ICAP_AGENT(2, "incident_agent_icap"),
    DSA_AGENT(3, "incident_agent_dsa"),
    MTA_AGENT(4, "incident_agent_mta"),
    PRINT_AGENT(5, "incident_agent_print"),
    ENDPOINT_AGENT(6, "incident_agent_endpoint"),
    CONTENT_ANALYSIS_ENGINE_AGENT(100, "incident_agent_content_analysis_engine");
    
    private int id;
    private String value;

    private IncidentAgentType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentAgentType get(int id) {
        for (IncidentAgentType agentType : IncidentAgentType.values()) {
            if (agentType.getId() != id) continue;
            return agentType;
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

        
    public static final List<IncidentAgentType> ALL_INCIDENT_AGENT_TYPES = ImmutableList.of((ATS_AGENT), (ICAP_AGENT), (DSA_AGENT), (MTA_AGENT), (CONTENT_ANALYSIS_ENGINE_AGENT));
}

