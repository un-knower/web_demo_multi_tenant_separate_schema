/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentAttachmentStatus {
    
    NO_ATTACHMENT(0, "incident_attachment_status_not_attachment"),
    HAS_ATTACHMENT(1, "incident_attachment_status_has_attachment");
    
    public static final List<IncidentAttachmentStatus> ALL_INCIDENT_ATTACHMENT_STATUS;
    static {
        ALL_INCIDENT_ATTACHMENT_STATUS = ImmutableList.of((NO_ATTACHMENT), (HAS_ATTACHMENT));
    }
    
    private int id;
    private String value;

    private IncidentAttachmentStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentAttachmentStatus get(int id) {
        for (IncidentAttachmentStatus attachmentStatus : IncidentAttachmentStatus.values()) {
            if (attachmentStatus.getId() != id) continue;
            return attachmentStatus;
        }
        return null;
    }

    public String getDisplayName() {
        return Localizer.getMessage((String) this.getValue());
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

