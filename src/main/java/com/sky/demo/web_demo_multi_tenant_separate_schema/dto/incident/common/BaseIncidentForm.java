package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/10/20.
 */
public abstract class BaseIncidentForm implements Serializable {

    private static final long serialVersionUID = 8314470968848684563L;
    private long id;
    private String transactionId;
    private int actionTypeCode;
    private int severityTypeCode;
    private int statusTypeCode;
    private boolean isIgnored;
    private String policyNames;  // ;
    private String detectedByName;
    private String tagContent;
    private String breachContents;      // ;
    private String localeDetectTime;    //UTC
    private int maxMatches;
    private long transactionSize;
    private List<IncidentPolicyForm> incidentPolicies = Lists.newArrayList();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(int actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public int getSeverityTypeCode() {
        return severityTypeCode;
    }

    public void setSeverityTypeCode(int severityTypeCode) {
        this.severityTypeCode = severityTypeCode;
    }

    public int getStatusTypeCode() {
        return statusTypeCode;
    }

    public void setStatusTypeCode(int statusTypeCode) {
        this.statusTypeCode = statusTypeCode;
    }

    public boolean isIgnored() {
        return isIgnored;
    }

    public void setIgnored(boolean isIgnored) {
        this.isIgnored = isIgnored;
    }

    public String getPolicyNames() {
        return policyNames;
    }

    public void setPolicyNames(String policyNames) {
        this.policyNames = policyNames;
    }

    public String getDetectedByName() {
        return detectedByName;
    }

    public void setDetectedByName(String detectedByName) {
        this.detectedByName = detectedByName;
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public String getBreachContents() {
        return breachContents;
    }

    public void setBreachContents(String breachContents) {
        this.breachContents = breachContents;
    }

    public String getLocaleDetectTime() {
        return localeDetectTime;
    }

    public void setLocaleDetectTime(String localeDetectTime) {
        this.localeDetectTime = localeDetectTime;
    }

    public int getMaxMatches() {
        return maxMatches;
    }

    public void setMaxMatches(int maxMatches) {
        this.maxMatches = maxMatches;
    }

    public long getTransactionSize() {
        return transactionSize;
    }

    public void setTransactionSize(long transactionSize) {
        this.transactionSize = transactionSize;
    }

    public List<IncidentPolicyForm> getIncidentPolicies() {
        return incidentPolicies;
    }

    public void setIncidentPolicies(List<IncidentPolicyForm> incidentPolicies) {
        this.incidentPolicies = incidentPolicies;
    }

    @Override
    public String toString() {
        return "BaseIncidentForm{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", actionTypeCode=" + actionTypeCode +
                ", severityTypeCode=" + severityTypeCode +
                ", statusTypeCode=" + statusTypeCode +
                ", isIgnored=" + isIgnored +
                ", policyNames='" + policyNames + '\'' +
                ", detectedByName='" + detectedByName + '\'' +
                ", tagContent='" + tagContent + '\'' +
                ", breachContents='" + breachContents + '\'' +
                ", localeDetectTime='" + localeDetectTime + '\'' +
                ", maxMatches=" + maxMatches +
                ", transactionSize=" + transactionSize +
                ", incidentPolicies=" + incidentPolicies +
                '}';
    }
}
