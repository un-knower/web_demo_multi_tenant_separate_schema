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

public enum IncidentEndpointOperationType {
    
    UNKNOWN(0, "incident_endpoint_operation_unknown"),
    COPY_OR_CUT(1, "incident_endpoint_operation_copy_or_cut"),
    PRINT(2, "incident_endpoint_operation_print"),
    PASTE(3, "incident_endpoint_operation_paste"),
    SCREEN_CAPTURE(4, "incident_endpoint_operation_screen_capture"),
    FILE_ACCESS(5, "incident_endpoint_operation_file_access"),
    WEB(6, "incident_endpoint_operation_web"),
    FTP(6, "incident_endpoint_operation_ftp"),
    DOWNLOAD(8, "incident_endpoint_operation_download"),
    EMAIL(9, "incident_endpoint_operation_email");
    
    public static final List<IncidentEndpointOperationType> ALL_INCIDENT_ENDPOINT_OPERATION_TYPES;
    static {
        ALL_INCIDENT_ENDPOINT_OPERATION_TYPES = ImmutableList.of((COPY_OR_CUT), (PRINT), (PASTE), (SCREEN_CAPTURE), (FILE_ACCESS), (DOWNLOAD));
    }
   
    private int id;
    private String value;

    private IncidentEndpointOperationType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentEndpointOperationType get(int id) {
        for (IncidentEndpointOperationType operationType : IncidentEndpointOperationType.values()) {
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

