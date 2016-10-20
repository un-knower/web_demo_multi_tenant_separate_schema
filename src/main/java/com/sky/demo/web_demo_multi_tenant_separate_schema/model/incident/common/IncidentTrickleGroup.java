/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.joda.time.DateTime
 *  org.joda.time.Duration
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class IncidentTrickleGroup implements Serializable {

    private static final long serialVersionUID = -5981049412685032771L;
    private long id;
    private String transactionId;
    private String policyUuid;
    private String policyName;
    private long groupId;
    private int groupOrder;
    private String associatedTransactionIds;
    private List<String> transactionIds = Lists.newArrayList();
    private Timestamp updateTime;
    private int incidentCount;
    private int totalMatches;
    private IncidentSeverityType severityType;
    private String localeSeverityType;
    private DateTime startTime;
    private String localeStartTime;
    private DateTime endTime;
    private String localeEndTime;
    private Duration duration;
    private String localeDuration;
    private String sourceEntryName;

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

    public String getPolicyUuid() {
        return this.policyUuid;
    }

    public void setPolicyUuid(String policyUuid) {
        this.policyUuid = policyUuid;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public int getGroupOrder() {
        return this.groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getAssociatedTransactionIds() {
        return this.associatedTransactionIds;
    }

    public void setAssociatedTransactionIds(String associatedTransactionIds) {
        this.associatedTransactionIds = associatedTransactionIds;
    }

    public List<String> getTransactionIds() {
        return this.transactionIds;
    }

    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

    public int getIncidentCount() {
        return this.incidentCount;
    }

    public void setIncidentCount(int incidentCount) {
        this.incidentCount = incidentCount;
    }

    public int getTotalMatches() {
        return this.totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public IncidentSeverityType getSeverityType() {
        return this.severityType;
    }

    public void setSeverityType(IncidentSeverityType severityType) {
        this.severityType = severityType;
    }

    public String getLocaleSeverityType() {
        return this.localeSeverityType;
    }

    public void setLocaleSeverityType(String localeSeverityType) {
        this.localeSeverityType = localeSeverityType;
    }

    public DateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public String getLocaleStartTime() {
        return this.localeStartTime;
    }

    public void setLocaleStartTime(String localeStartTime) {
        this.localeStartTime = localeStartTime;
    }

    public DateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocaleEndTime() {
        return this.localeEndTime;
    }

    public void setLocaleEndTime(String localeEndTime) {
        this.localeEndTime = localeEndTime;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getLocaleDuration() {
        return this.localeDuration;
    }

    public void setLocaleDuration(String localeDuration) {
        this.localeDuration = localeDuration;
    }

    public String getSourceEntryName() {
        return this.sourceEntryName;
    }

    public void setSourceEntryName(String sourceEntryName) {
        this.sourceEntryName = sourceEntryName;
    }

    @Override
    public String toString() {
        return "IncidentTrickleGroup{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", policyUuid='" + policyUuid + '\'' +
                ", policyName='" + policyName + '\'' +
                ", groupId=" + groupId +
                ", groupOrder=" + groupOrder +
                ", associatedTransactionIds='" + associatedTransactionIds + '\'' +
                ", transactionIds=" + transactionIds +
                ", updateTime=" + updateTime +
                ", incidentCount=" + incidentCount +
                ", totalMatches=" + totalMatches +
                ", severityType=" + severityType +
                ", localeSeverityType='" + localeSeverityType + '\'' +
                ", startTime=" + startTime +
                ", localeStartTime='" + localeStartTime + '\'' +
                ", endTime=" + endTime +
                ", localeEndTime='" + localeEndTime + '\'' +
                ", duration=" + duration +
                ", localeDuration='" + localeDuration + '\'' +
                ", sourceEntryName='" + sourceEntryName + '\'' +
                '}';
    }
}

