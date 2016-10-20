/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.ArrayList;
import java.util.List;

public enum IncidentDiscoveryResourceType {
    
    FILE_SYSTEM(1, "incident_resource_file_system"),
    SHARE_POINT(2, "incident_resource_share_point"),
    LOTUS_DOMINO(3, "incident_resource_lotus_domino"),
    EXCHANGE(4, "incident_resource_exchange"),
    PST(5, "incident_resource_pst"),
    ENDPOINT(6, "incident_resource_endpoint"),
    LOCAL(7, "incident_resource_local"),
    DATABASE(8, "incident_resource_database");
    
    public static final List<IncidentDiscoveryResourceType> ALL_DISCOVERY_RESOURCE_TYPES;
    public static final List<IncidentDiscoveryResourceType> FILE_RESOURCE_TYPES;
    public static final List<IncidentDiscoveryResourceType> DATABASE_RESOURCE_TYPES;
    public static final List<IncidentDiscoveryResourceType> NETWORK_RESOURCE_TYPES;
    public static final List<IncidentDiscoveryResourceType> ENDPOINT_RESOURCE_TYPES;
    static {
        ALL_DISCOVERY_RESOURCE_TYPES = ImmutableList.of((FILE_SYSTEM), (SHARE_POINT), (LOTUS_DOMINO), (EXCHANGE), (PST), (ENDPOINT), (LOCAL), (DATABASE));
        FILE_RESOURCE_TYPES = ImmutableList.of((FILE_SYSTEM), (SHARE_POINT), (LOTUS_DOMINO), (EXCHANGE), (PST), (LOCAL));
        DATABASE_RESOURCE_TYPES = ImmutableList.of((DATABASE));
        NETWORK_RESOURCE_TYPES = ImmutableList.of((FILE_SYSTEM), (SHARE_POINT), (LOTUS_DOMINO), (EXCHANGE), (PST), (LOCAL), (DATABASE));
        ENDPOINT_RESOURCE_TYPES = ImmutableList.of((ENDPOINT));
    }
    
    
    private int id;
    private String value;

    private IncidentDiscoveryResourceType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentDiscoveryResourceType get(int id) {
        for (IncidentDiscoveryResourceType resourceType : IncidentDiscoveryResourceType.values()) {
            if (resourceType.getId() != id) continue;
            return resourceType;
        }
        return null;
    }

    public static List<Integer> getIdsByResourceTypeList(List<IncidentDiscoveryResourceType> resourceTypeList) {
        ArrayList list = Lists.newArrayList();
        for (IncidentDiscoveryResourceType resourceType : resourceTypeList) {
            list.add(resourceType.getId());
        }
        return list;
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

