/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentFolderType {
    
    PUBLIC(1, "incident_folder_public"),
    PRIVATE(2, "incident_folder_private");
    
    public static final List<IncidentFolderType> ALL_INCIDENT_FOLDER_TYPES;
    static {
        ALL_INCIDENT_FOLDER_TYPES = ImmutableList.of((PUBLIC), (PRIVATE));
    }
    
    private int id;
    private String value;

    private IncidentFolderType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentFolderType get(int id) {
        for (IncidentFolderType folderType : IncidentFolderType.values()) {
            if (folderType.getId() != id) continue;
            return folderType;
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

