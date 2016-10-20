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

public class IncidentPolicyInfo implements Serializable{

    private static final long serialVersionUID = 7372197887592761650L;
    private long id;
    private String uuid;
    private String name;
    private boolean isTrickle;
    private boolean isVisible;
    private IncidentActionInfo incidentActionInfo;
    private List<IncidentRuleInfo> incidentRuleInfos = Lists.newArrayList();

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncidentRuleInfo> getIncidentRuleInfos() {
        return this.incidentRuleInfos;
    }

    public void setIncidentRuleInfos(List<IncidentRuleInfo> incidentRuleInfos) {
        this.incidentRuleInfos = incidentRuleInfos;
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

    public boolean getIsTrickle() {
        return this.isTrickle;
    }

    public void setIsTrickle(boolean isTrickle) {
        this.isTrickle = isTrickle;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public IncidentActionInfo getIncidentActionInfo() {
        return this.incidentActionInfo;
    }

    public void setIncidentActionInfo(IncidentActionInfo incidentActionInfo) {
        this.incidentActionInfo = incidentActionInfo;
    }

    public void addIncidentRuleInfo(IncidentRuleInfo incidentRuleInfo) {
        if (null == this.incidentRuleInfos) {
            this.incidentRuleInfos = Lists.newArrayList();
        }
        this.incidentRuleInfos.add(incidentRuleInfo);
    }

    @Override
    public String toString() {
        return "IncidentPolicyInfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", isTrickle=" + isTrickle +
                ", isVisible=" + isVisible +
                ", incidentActionInfo=" + incidentActionInfo +
                ", incidentRuleInfos=" + incidentRuleInfos +
                '}';
    }
}

