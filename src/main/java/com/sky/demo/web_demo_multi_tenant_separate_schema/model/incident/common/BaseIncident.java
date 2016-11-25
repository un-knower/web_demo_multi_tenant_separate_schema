/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info.IncidentPolicyInfo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public abstract class BaseIncident implements Serializable {

    private static final long serialVersionUID = 2140829036672018290L;

    private long id;
    private String transactionId;

    @JsonIgnore
    private long trafficId;
    @JsonIgnore
    private IncidentActionType actionType;
    @JsonIgnore
    private String localeActionType;
    private int actionTypeCode;
    @JsonIgnore
    private IncidentSeverityType severityType;
    @JsonIgnore
    private String localeSeverityType;
    private int severityTypeCode;
    @JsonIgnore
    private IncidentStatusType statusType;
    @JsonIgnore
    private String localeStatusType;
    private int statusTypeCode;

    private boolean isIgnored;
    @JsonIgnore
    private String localeIgnoreStatus;

    private String policyNames;  // ; seperated

    @JsonIgnore
    private int assignedToId;
    @JsonIgnore
    private String assignedToName;
    @JsonIgnore
    private String commandName;

    @JsonIgnore
    private String detectedByUuid;
    private String detectedByName;
    @JsonIgnore
    private IncidentAgentType detectAgentType;
    @JsonIgnore
    private String detectAgentHost;

    @JsonIgnore
    private String analyzedByUuid;
    private String analyzedByName;
    @JsonIgnore
    private IncidentAgentType analyzeAgentType;
    @JsonIgnore
    private String analyzeAgentHost;

    private String tagContent;
    private String breachContents;      // ; seperated
    private String localeDetectTime;    //UTC
    private Timestamp detectTime;
    private String localeIncidentTime;  //UTC insert time
    private Timestamp incidentTime;
    private int maxMatches;

    @JsonIgnore
    private String localeTransactionSize;
    private long transactionSize;

    @JsonIgnore
    private long deployVersion;
    @JsonIgnore
    private IncidentActionType policyActionType;
    @JsonIgnore
    private IncidentActionType agentActionType;

    private List<IncidentPolicy> incidentPolicies = Lists.newArrayList();

    @JsonIgnore
    private List<IncidentHistory> incidentHistories = Lists.newArrayList(); //need insert to pg
    @JsonIgnore
    private List<IncidentBreachContent> incidentBreachContents = Lists.newArrayList();
    @JsonIgnore
    private Set<String> breachContentSet = Sets.newTreeSet();
    @JsonIgnore
    private boolean exceedBreachContentsLimit;
    @JsonIgnore
    private List<IncidentPolicyInfo> triggeredIncidentPolicyInfos;
    @JsonIgnore
    private List<IncidentPolicyInfo> originalIncidentPolicyInfos;
    @JsonIgnore
    private IncidentProperties incidentProperties;


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

    public boolean isExceedBreachContentsLimit() {
        return exceedBreachContentsLimit;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getTrafficId() {
        return this.trafficId;
    }

    public void setTrafficId(long trafficId) {
        this.trafficId = trafficId;
    }

    public IncidentActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(IncidentActionType actionType) {
        this.actionType = actionType;
    }

    public IncidentSeverityType getSeverityType() {
        return this.severityType;
    }

    public void setSeverityType(IncidentSeverityType severityType) {
        this.severityType = severityType;
    }

    public IncidentStatusType getStatusType() {
        return this.statusType;
    }

    public void setStatusType(IncidentStatusType statusType) {
        this.statusType = statusType;
    }

    public boolean getIsIgnored() {
        return this.isIgnored;
    }

    public void setIsIgnored(boolean isIgnored) {
        this.isIgnored = isIgnored;
    }

    public String getPolicyNames() {
        return this.policyNames;
    }

    public void setPolicyNames(String policyNames) {
        this.policyNames = policyNames;
    }

    public int getAssignedToId() {
        return this.assignedToId;
    }

    public void setAssignedToId(int assignedToId) {
        this.assignedToId = assignedToId;
    }

    public String getAssignedToName() {
        return this.assignedToName;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getDetectedByUuid() {
        return this.detectedByUuid;
    }

    public void setDetectedByUuid(String detectedByUuid) {
        this.detectedByUuid = detectedByUuid;
    }

    public String getDetectedByName() {
        return this.detectedByName;
    }

    public void setDetectedByName(String detectedByName) {
        this.detectedByName = detectedByName;
    }

    public String getAnalyzedByUuid() {
        return this.analyzedByUuid;
    }

    public void setAnalyzedByUuid(String analyzedByUuid) {
        this.analyzedByUuid = analyzedByUuid;
    }

    public String getAnalyzedByName() {
        return this.analyzedByName;
    }

    public void setAnalyzedByName(String analyzedByName) {
        this.analyzedByName = analyzedByName;
    }

    public IncidentAgentType getDetectAgentType() {
        return this.detectAgentType;
    }

    public void setDetectAgentType(IncidentAgentType detectAgentType) {
        this.detectAgentType = detectAgentType;
    }

    public String getDetectAgentHost() {
        return this.detectAgentHost;
    }

    public void setDetectAgentHost(String detectAgentHost) {
        this.detectAgentHost = detectAgentHost;
    }

    public IncidentAgentType getAnalyzeAgentType() {
        return this.analyzeAgentType;
    }

    public void setAnalyzeAgentType(IncidentAgentType analyzeAgentType) {
        this.analyzeAgentType = analyzeAgentType;
    }

    public String getAnalyzeAgentHost() {
        return this.analyzeAgentHost;
    }

    public void setAnalyzeAgentHost(String analyzeAgentHost) {
        this.analyzeAgentHost = analyzeAgentHost;
    }

    public String getTagContent() {
        return this.tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public String getBreachContents() {
        return this.breachContents;
    }

    public void setBreachContents(String breachContents) {
        this.breachContents = breachContents;
    }

    public String getLocaleDetectTime() {
        return this.localeDetectTime;
    }

    public void setLocaleDetectTime(String localeDetectTime) {
        this.localeDetectTime = localeDetectTime;
    }

    public Timestamp getDetectTime() {
        return this.detectTime;
    }

    public void setDetectTime(Timestamp detectTime) {
        this.detectTime = detectTime;
    }

    public String getLocaleIncidentTime() {
        return this.localeIncidentTime;
    }

    public void setLocaleIncidentTime(String localeIncidentTime) {
        this.localeIncidentTime = localeIncidentTime;
    }

    public Timestamp getIncidentTime() {
        return this.incidentTime;
    }

    public void setIncidentTime(Timestamp incidentTime) {
        this.incidentTime = incidentTime;
    }

    public int getMaxMatches() {
        return this.maxMatches;
    }

    public void setMaxMatches(int maxMatches) {
        this.maxMatches = maxMatches;
    }

    public long getTransactionSize() {
        return this.transactionSize;
    }

    public void setTransactionSize(long transactionSize) {
        this.transactionSize = transactionSize;
    }

    public String getLocaleTransactionSize() {
        return this.localeTransactionSize;
    }

    public void setLocaleTransactionSize(String localeTransactionSize) {
        this.localeTransactionSize = localeTransactionSize;
    }

    public String getLocaleActionType() {
        return this.localeActionType;
    }

    public void setLocaleActionType(String localeActionType) {
        this.localeActionType = localeActionType;
    }

    public String getLocaleSeverityType() {
        return this.localeSeverityType;
    }

    public void setLocaleSeverityType(String localeSeverityType) {
        this.localeSeverityType = localeSeverityType;
    }

    public String getLocaleStatusType() {
        return this.localeStatusType;
    }

    public void setLocaleStatusType(String localeStatusType) {
        this.localeStatusType = localeStatusType;
    }

    public String getLocaleIgnoreStatus() {
        return this.localeIgnoreStatus;
    }

    public void setLocaleIgnoreStatus(String localeIgnoreStatus) {
        this.localeIgnoreStatus = localeIgnoreStatus;
    }

    public long getDeployVersion() {
        return this.deployVersion;
    }

    public void setDeployVersion(long deployVersion) {
        this.deployVersion = deployVersion;
    }

    public IncidentActionType getAgentActionType() {
        return this.agentActionType;
    }

    public void setAgentActionType(IncidentActionType agentActionType) {
        this.agentActionType = agentActionType;
    }

    public IncidentActionType getPolicyActionType() {
        return this.policyActionType;
    }

    public void setPolicyActionType(IncidentActionType policyActionType) {
        this.policyActionType = policyActionType;
    }

    public IncidentProperties getIncidentProperties() {
        return this.incidentProperties;
    }

    public void setIncidentProperties(IncidentProperties incidentProperties) {
        this.incidentProperties = incidentProperties;
    }

    public List<IncidentPolicy> getIncidentPolicies() {
        return this.incidentPolicies;
    }

    public void setIncidentPolicies(List<IncidentPolicy> incidentPolicies) {
        this.incidentPolicies = incidentPolicies;
    }

    public List<IncidentBreachContent> getIncidentBreachContents() {
        return this.incidentBreachContents;
    }

    public void setIncidentBreachContents(List<IncidentBreachContent> incidentBreachContents) {
        this.incidentBreachContents = incidentBreachContents;
    }

    public Set<String> getBreachContentSet() {
        return this.breachContentSet;
    }

    public void setBreachContentSet(Set<String> breachContentSet) {
        this.breachContentSet = breachContentSet;
    }

    public List<IncidentHistory> getIncidentHistories() {
        return this.incidentHistories;
    }

    public void setIncidentHistories(List<IncidentHistory> incidentHistories) {
        this.incidentHistories = incidentHistories;
    }

    public boolean getExceedBreachContentsLimit() {
        return this.exceedBreachContentsLimit;
    }

    public void setExceedBreachContentsLimit(boolean exceedBreachContentsLimit) {
        this.exceedBreachContentsLimit = exceedBreachContentsLimit;
    }

    public List<IncidentPolicyInfo> getOriginalIncidentPolicyInfos() {
        return this.originalIncidentPolicyInfos;
    }

    public void setOriginalIncidentPolicyInfos(List<IncidentPolicyInfo> originalIncidentPolicyInfos) {
        this.originalIncidentPolicyInfos = originalIncidentPolicyInfos;
    }

    public List<IncidentPolicyInfo> getTriggeredIncidentPolicyInfos() {
        return this.triggeredIncidentPolicyInfos;
    }

    public void setTriggeredIncidentPolicyInfos(List<IncidentPolicyInfo> triggeredIncidentPolicyInfos) {
        this.triggeredIncidentPolicyInfos = triggeredIncidentPolicyInfos;
    }

    public void addIncidentPolicy(IncidentPolicy incidentPolicy) {
        if (null == this.incidentPolicies) {
            this.incidentPolicies = Lists.newArrayList();
        }
        this.incidentPolicies.add(incidentPolicy);
    }

    public void addIncidentHistory(IncidentHistory incidentHistory) {
        if (null == this.incidentHistories) {
            this.incidentHistories = Lists.newArrayList();
        }
        this.incidentHistories.add(incidentHistory);
    }

    public void addIncidentBreachContent(IncidentBreachContent incidentBreachContent) {
        if (null == this.incidentBreachContents) {
            this.incidentBreachContents = Lists.newArrayList();
        }
        this.incidentBreachContents.add(incidentBreachContent);
    }

    public void addBreachContent(String breachContent) {
        if (null == this.breachContentSet) {
            this.breachContentSet = Sets.newTreeSet();
        }
        this.breachContentSet.add(breachContent);
    }

    public void addTriggeredIncidentPolicyInfo(IncidentPolicyInfo incidentPolicyInfo) {
        if (null == this.triggeredIncidentPolicyInfos) {
            this.triggeredIncidentPolicyInfos = Lists.newArrayList();
        }
        this.triggeredIncidentPolicyInfos.add(incidentPolicyInfo);
    }

    public void addOriginalIncidentPolicyInfo(IncidentPolicyInfo incidentPolicyInfo) {
        if (null == this.originalIncidentPolicyInfos) {
            this.originalIncidentPolicyInfos = Lists.newArrayList();
        }
        this.originalIncidentPolicyInfos.add(incidentPolicyInfo);
    }

    @Override
    public String toString() {
        return "BaseIncident{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", trafficId=" + trafficId +
                ", actionType=" + actionType +
                ", localeActionType='" + localeActionType + '\'' +
                ", actionTypeCode=" + actionTypeCode +
                ", severityType=" + severityType +
                ", localeSeverityType='" + localeSeverityType + '\'' +
                ", severityTypeCode=" + severityTypeCode +
                ", statusType=" + statusType +
                ", localeStatusType='" + localeStatusType + '\'' +
                ", statusTypeCode=" + statusTypeCode +
                ", isIgnored=" + isIgnored +
                ", localeIgnoreStatus='" + localeIgnoreStatus + '\'' +
                ", policyNames='" + policyNames + '\'' +
                ", assignedToId=" + assignedToId +
                ", assignedToName='" + assignedToName + '\'' +
                ", commandName='" + commandName + '\'' +
                ", detectedByUuid='" + detectedByUuid + '\'' +
                ", detectedByName='" + detectedByName + '\'' +
                ", detectAgentType=" + detectAgentType +
                ", detectAgentHost='" + detectAgentHost + '\'' +
                ", analyzedByUuid='" + analyzedByUuid + '\'' +
                ", analyzedByName='" + analyzedByName + '\'' +
                ", analyzeAgentType=" + analyzeAgentType +
                ", analyzeAgentHost='" + analyzeAgentHost + '\'' +
                ", tagContent='" + tagContent + '\'' +
                ", breachContents='" + breachContents + '\'' +
                ", localeDetectTime='" + localeDetectTime + '\'' +
                ", detectTime=" + detectTime +
                ", localeIncidentTime='" + localeIncidentTime + '\'' +
                ", incidentTime=" + incidentTime +
                ", maxMatches=" + maxMatches +
                ", localeTransactionSize='" + localeTransactionSize + '\'' +
                ", transactionSize=" + transactionSize +
                ", deployVersion=" + deployVersion +
                ", policyActionType=" + policyActionType +
                ", agentActionType=" + agentActionType +
                ", incidentPolicies=" + incidentPolicies +
                ", incidentHistories=" + incidentHistories +
                ", incidentBreachContents=" + incidentBreachContents +
                ", breachContentSet=" + breachContentSet +
                ", exceedBreachContentsLimit=" + exceedBreachContentsLimit +
                ", triggeredIncidentPolicyInfos=" + triggeredIncidentPolicyInfos +
                ", originalIncidentPolicyInfos=" + originalIncidentPolicyInfos +
                ", incidentProperties=" + incidentProperties +
                '}';
    }
}

