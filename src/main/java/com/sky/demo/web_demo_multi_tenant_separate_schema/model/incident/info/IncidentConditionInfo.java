/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class IncidentConditionInfo implements Serializable{

    private static final long serialVersionUID = -7665595129426500088L;
    private long id;
    private String uuid;
    private String type;
    private List<IncidentElementInfo> incidentElementInfos = Lists.newArrayList();

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncidentElementInfo> getIncidentElementInfos() {
        return this.incidentElementInfos;
    }

    public void setIncidentElementInfos(List<IncidentElementInfo> incidentElementInfos) {
        this.incidentElementInfos = incidentElementInfos;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void addIncidentElementInfo(IncidentElementInfo incidentElementInfo) {
        if (null == incidentElementInfo) {
            this.incidentElementInfos = Lists.newArrayList();
        }
        this.incidentElementInfos.add(incidentElementInfo);
    }

    @Override
    public String toString() {
        return "IncidentConditionInfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                ", incidentElementInfos=" + incidentElementInfos +
                '}';
    }
}

