package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/10/20.
 */
public class IncidentPolicyForm implements Serializable {

    private static final long serialVersionUID = -7880147587104681712L;
    private String policyUuid;
    private String policyName;
    private String ruleUuid;
    private String ruleName;
    private String actionUuid;
    private String actionName;
    private int matches;
    private int severityTypeCode;
    private boolean isTrickle;      //零星式
    private boolean isVisible;
    private List<IncidentElementForm> incidentElements = Lists.newArrayList();

    public String getPolicyUuid() {
        return policyUuid;
    }

    public void setPolicyUuid(String policyUuid) {
        this.policyUuid = policyUuid;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getRuleUuid() {
        return ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getActionUuid() {
        return actionUuid;
    }

    public void setActionUuid(String actionUuid) {
        this.actionUuid = actionUuid;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getSeverityTypeCode() {
        return severityTypeCode;
    }

    public void setSeverityTypeCode(int severityTypeCode) {
        this.severityTypeCode = severityTypeCode;
    }

    public boolean isTrickle() {
        return isTrickle;
    }

    public void setTrickle(boolean isTrickle) {
        this.isTrickle = isTrickle;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public List<IncidentElementForm> getIncidentElements() {
        return incidentElements;
    }

    public void setIncidentElements(List<IncidentElementForm> incidentElements) {
        this.incidentElements = incidentElements;
    }

    @Override
    public String toString() {
        return "IncidentPolicyForm{" +
                "policyUuid='" + policyUuid + '\'' +
                ", policyName='" + policyName + '\'' +
                ", ruleUuid='" + ruleUuid + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", actionUuid='" + actionUuid + '\'' +
                ", actionName='" + actionName + '\'' +
                ", matches=" + matches +
                ", severityTypeCode=" + severityTypeCode +
                ", isTrickle=" + isTrickle +
                ", isVisible=" + isVisible +
                ", incidentElements=" + incidentElements +
                '}';
    }
}
