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

public enum IncidentActionType {

    PERMIT(1, "incident_action_permit"),
    BLOCK(2, "incident_action_block"),
    CONFIRM(3, "incident_action_confirm"),
    DROP_ATTACHMENT(4, "incident_action_drop_attachment"),
    ENCRYPTED(5, "incident_action_encrypted"),
    QUARANTINED(6, "incident_action_quarantined"),
    ENDPOINT_ENCRYPTED(7, "incident_action_endpoint_encrypted"),
    RELEASED(100, "incident_action_released"),
    RELEASING(101, "incident_action_releasing"),
    ENDPOINT_CONFIRM_PERMIT(102, "incident_action_endpoint_confirm_permit"),
    ENDPOINT_CONFIRM_BLOCK(103, "incident_action_endpoint_confirm_block"),
    ENDPOINT_CONFIRM_TIMEOUT_PERMIT(104, "incident_action_endpoint_confirm_timeout_permit"),
    ENDPOINT_CONFIRM_TIMEOUT_BLOCK(105, "incident_action_endpoint_confirm_timeout_block"),
    ENDPOINT_IMMEDIATE_BLOCK(106, "incident_action_endpoint_immediate_block");
    
    public static final List<IncidentActionType> ALL_NETWORK_INCIDENT_ACTION_TYPES;
    public static final List<IncidentActionType> ALL_ENDPOINT_INCIDENT_ACTION_TYPES;

    static {
        ALL_NETWORK_INCIDENT_ACTION_TYPES = ImmutableList.of(PERMIT, BLOCK, DROP_ATTACHMENT, ENCRYPTED, QUARANTINED, RELEASED);
        ALL_ENDPOINT_INCIDENT_ACTION_TYPES = ImmutableList.of(PERMIT, BLOCK, ENDPOINT_ENCRYPTED, ENDPOINT_CONFIRM_PERMIT, ENDPOINT_CONFIRM_BLOCK, ENDPOINT_CONFIRM_TIMEOUT_PERMIT, ENDPOINT_CONFIRM_TIMEOUT_BLOCK, ENDPOINT_IMMEDIATE_BLOCK);
    }

    private int id;
    private String value;

    private IncidentActionType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentActionType get(int id) {
        for (IncidentActionType actionType : IncidentActionType.values()) {
            if (actionType.getId() != id) continue;
            return actionType;
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

