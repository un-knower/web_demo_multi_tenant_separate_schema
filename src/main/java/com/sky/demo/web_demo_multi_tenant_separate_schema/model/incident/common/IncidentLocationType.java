/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;


import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentLocationType {

    SUBJECT(1, "incident_location_subject"),
    BODY(2, "incident_location_body"),
    ATTACHMENT(3, "incident_location_attachment"),
    FILE(4, "incident_location_file"),
    BCC(5, "incident_location_bcc"),
    CC(6, "incident_location_cc"),
    TO(7, "incident_location_to"),
    FROM(8, "incident_location_from"),
    SENT(9, "incident_location_sent"),
    PRIORITY(10, "incident_location_property"),
    CONTENT_DESCRIPTOR(11, "incident_location_content_descriptor"),
    HTTP_HEADERS(12, "incident_location_http_headers"),
    META_DATA(13, "incident_location_meta_data"),
    HTTP_GET(14, "incident_location_http_get"),
    AGENT_DESTINATIONS(15, "incident_location_agent_destinations"),
    OTHER(16, "incident_location_other"),
    BY_PASS_FIELD(17, "incident_location_by_pass_field"),
    FORENSICS(18, "incident_location_forensics"),
    FORM_DATA(19, "incident_location_form_data"),
    MIME_HEADERS(20, "incident_location_mime_headers"),
    MESSAGE_ID(21, "incident_location_message_id"),
    FORM_URL_ENCODED(22, "incident_location_form_url_encoded"),
    MIME_X_HEADERS(23, "incident_location_mime_x_headers"),
    UNKNOWN(24, "incident_location_unknown"),
    FINGERPRINT_SOURCE_FILE(100, "incident_location_fingerprint_source_file");
    
    private int id;
    private String value;

    private IncidentLocationType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentLocationType get(int id) {
        for (IncidentLocationType locationType : IncidentLocationType.values()) {
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

