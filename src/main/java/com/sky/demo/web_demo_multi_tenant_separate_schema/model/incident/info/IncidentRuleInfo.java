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

public class IncidentRuleInfo implements Serializable{

    private static final long serialVersionUID = -5928901527716143983L;
    private long id;
    private String uuid;
    private String name;
    private List<IncidentConditionInfo> incidentConditionInfos = Lists.newArrayList();

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncidentConditionInfo> getIncidentConditionInfos() {
        return this.incidentConditionInfos;
    }

    public void setIncidentConditionInfos(List<IncidentConditionInfo> incidentConditionInfos) {
        this.incidentConditionInfos = incidentConditionInfos;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void addIncidentConditionInfo(IncidentConditionInfo incidentConditionInfo) {
        if (null == this.incidentConditionInfos) {
            this.incidentConditionInfos = Lists.newArrayList();
        }
        this.incidentConditionInfos.add(incidentConditionInfo);
    }

    @Override
    public String toString() {
        return "IncidentRuleInfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", incidentConditionInfos=" + incidentConditionInfos +
                '}';
    }
}

