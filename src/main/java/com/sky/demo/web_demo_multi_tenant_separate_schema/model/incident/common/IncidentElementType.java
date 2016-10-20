/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;


import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentElementType {

    REGULAR_EXPRESSION(1, "incident_element_regular_expression"),
    DICTIONARY(2, "incident_element_dictionary"),
    SCRIPT(3, "incident_element_script"),
    FILE_PROPERTIES(4, "incident_element_file_properties"),
    KEY_PHRASE(5, "incident_element_key_phrase"),
    NLP(6, "incident_element_nlp"),
    FILE_FINGERPRINT(7, "incident_element_file_fingerprint"),
    MACHINE_LEARNING(8, "incident_element_machine_learning"),
    ENDPOINT_LOCATION(9, "incident_element_endpoint_location"),
    FILE_NAME(10, "incident_element_file_name"),
    ATTACHMENT_NUMBER(11, "incident_element_attachment_number"),
    DATABASE_FINGERPRINT(12, "incident_element_database_fingerprint"),
    FILE_SIZE(13, "incident_element_file_size");
    
    private int id;
    private String value;

    private IncidentElementType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentElementType get(int id) {
        for (IncidentElementType elementType : IncidentElementType.values()) {
            if (elementType.getId() != id) continue;
            return elementType;
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

