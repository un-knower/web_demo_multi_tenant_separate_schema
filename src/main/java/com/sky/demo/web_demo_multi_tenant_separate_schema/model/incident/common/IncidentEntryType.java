/*
 * Decompiled with CFR 0_118.
 *
 * Could not load the following classes:
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;


import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

public enum IncidentEntryType {

    DIRECTORY_USER(1, "incident_entry_directory_user"),
    DIRECTORY_GROUP(2, "incident_entry_directory_group"),
    DIRECTORY_OU(3, "incident_entry_directory_ou"),
    DIRECTORY_COMPUTER(4, "incident_entry_directory_computer"),
    MAIL(5, "incident_entry_mail"),
    USERNAME(6, "incident_entry_username"),
    IP(7, "incident_entry_ip"),
    HOSTNAME(8, "incident_entry_hostname"),
    DEVICE_NAME(9, "incident_entry_device_name"),
    DIRECTORY_ENTRY(100, "incident_entry_directory_entry");

    private int id;
    private String value;

    private IncidentEntryType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentEntryType get(int id) {
        for (IncidentEntryType entryType : IncidentEntryType.values()) {
            if (entryType.getId() != id) continue;
            return entryType;
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

