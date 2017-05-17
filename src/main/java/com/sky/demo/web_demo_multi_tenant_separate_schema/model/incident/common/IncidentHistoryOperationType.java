/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentHistoryOperationType {

    DETECT_INCIDENT(1, "incident_history_operation_detect_incident"),
    UPDATE_STATUS(2, "incident_history_operation_update_status"),
    UPDATE_SEVERITY(3, "incident_history_operation_update_severity"),
    ASSIGNED_TO(4, "incident_history_operation_assigned_to"),
    ADD_COMMENTS(5, "incident_history_operation_add_comments"),
    RELEASE_INCIDENT(6, "incident_history_operation_release_incident"),
    ESCALATE_TO_MANAGER(7, "incident_history_operation_escalate_to_manager"),
    ESCALATE_TO_OTHERS(8, "incident_history_operation_escalate_to_others"),
    EXECUTE_COMMAND(9, "incident_history_operation_execute_command"),
    LOCK_INCIDENT(10, "incident_history_operation_lock_incident"),
    UNLOCKED_INCIDENT(11, "incident_history_operation_unlock_incident"),
    ADD_TAG(12, "incident_history_operation_add_tag"),
    IGNORE_INCIDENT(13, "incident_history_operation_ignore_incident"),
    SCAN_UPDATE(14, "incident_history_operation_scan_update"),
    LOCK_SCAN_UPDATE(15, "incident_history_operation_lock_scan_update"),
    RELEASE_FAILURE(16, "incident_history_operation_release_failure"),
    SEND_NOTIFICATION(17, "incident_history_operation_send_notification"),
    SEND_SYSLOG(18, "incident_history_operation_send_syslog"),
    CONFIRM_INFORMATION(19, "incident_history_operation_confirm_information");
    
    private int id;
    private String value;

    private IncidentHistoryOperationType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentHistoryOperationType get(int id) {
        for (IncidentHistoryOperationType operationType : IncidentHistoryOperationType.values()) {
            if (operationType.getId() != id) continue;
            return operationType;
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

