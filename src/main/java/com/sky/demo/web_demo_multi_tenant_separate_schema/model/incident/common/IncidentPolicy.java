/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class IncidentPolicy implements Serializable {

    private static final long serialVersionUID = -5487728194083227404L;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long incidentId;

    private String policyUuid;
    private String policyName;

    @JsonIgnore
    private int policyPriority;

    private String ruleUuid;
    private String ruleName;
    private String actionUuid;
    private String actionName;
    private int matches;

    @JsonIgnore
    private IncidentSeverityType severityType;
    private int severityTypeCode;

    private boolean isTrickle;      //零星式
    private boolean isVisible;
    private List<IncidentElement> incidentElements = Lists.newArrayList();

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionUuid() {
        return this.actionUuid;
    }

    public void setActionUuid(String actionUuid) {
        this.actionUuid = actionUuid;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncidentElement> getIncidentElements() {
        return this.incidentElements;
    }

    public void setIncidentElements(List<IncidentElement> incidentElements) {
        this.incidentElements = incidentElements;
    }

    public long getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    public int getMatches() {
        return this.matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyUuid() {
        return this.policyUuid;
    }

    public void setPolicyUuid(String policyUuid) {
        this.policyUuid = policyUuid;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleUuid() {
        return this.ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid;
    }

    public IncidentSeverityType getSeverityType() {
        return this.severityType;
    }

    public void setSeverityType(IncidentSeverityType severityType) {
        this.severityType = severityType;
    }

    public int getPolicyPriority() {
        return this.policyPriority;
    }

    public void setPolicyPriority(int policyPriority) {
        this.policyPriority = policyPriority;
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

    public void addIncidentElement(IncidentElement incidentElement) {
        if (null == this.incidentElements) {
            this.incidentElements = Lists.newArrayList();
        }
        this.incidentElements.add(incidentElement);
    }

    @Override
    public String toString() {
        return "IncidentPolicy{" +
                "id=" + id +
                ", incidentId=" + incidentId +
                ", policyUuid='" + policyUuid + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyPriority=" + policyPriority +
                ", ruleUuid='" + ruleUuid + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", actionUuid='" + actionUuid + '\'' +
                ", actionName='" + actionName + '\'' +
                ", matches=" + matches +
                ", severityType=" + severityType +
                ", isTrickle=" + isTrickle +
                ", isVisible=" + isVisible +
                ", incidentElements=" + incidentElements +
                '}';
    }
}

