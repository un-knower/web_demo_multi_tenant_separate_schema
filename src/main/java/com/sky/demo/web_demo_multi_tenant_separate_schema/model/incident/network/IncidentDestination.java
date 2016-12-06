/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentActionType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info.IncidentEntryInfo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class IncidentDestination implements Serializable {

    private static final long serialVersionUID = -8681906966838769976L;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long incidentId;
    @JsonIgnore
    private long destinationEntryId;

    private String destinationEntryName;

    @JsonIgnore
    private IncidentDestinationType destinationType;
    @JsonIgnore
    private String localeDestinationType;
    private int destinationTypeCode;

    @JsonIgnore
    private IncidentActionType actionType;
    @JsonIgnore
    private String localeActionType;
    private int actionTypeCode;

    private boolean isReleased;
    private String releasedBy;
    private String localeReleaseTime;
    @JsonIgnore
    private Timestamp releaseTime;

    @JsonIgnore
    private IncidentDirectionType directionType;
    @JsonIgnore
    private String localeDirectionType;
    private int directionTypeCode;

    @JsonIgnore
    private long urlCategoryId;
    private IncidentEntryInfo destinationEntryInfo;     //目标详情

    private List<IncidentDropAttachment> incidentDropAttachments = Lists.newArrayList();


    public int getDestinationTypeCode() {
        return destinationTypeCode;
    }

    public void setDestinationTypeCode(int destinationTypeCode) {
        this.destinationTypeCode = destinationTypeCode;
    }

    public int getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(int actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }

    public int getDirectionTypeCode() {
        return directionTypeCode;
    }

    public void setDirectionTypeCode(int directionTypeCode) {
        this.directionTypeCode = directionTypeCode;
    }

    public IncidentActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(IncidentActionType actionType) {
        this.actionType = actionType;
    }

    public IncidentEntryInfo getDestinationEntryInfo() {
        return this.destinationEntryInfo;
    }

    public void setDestinationEntryInfo(IncidentEntryInfo destinationEntryInfo) {
        this.destinationEntryInfo = destinationEntryInfo;
    }

    public long getDestinationEntryId() {
        return this.destinationEntryId;
    }

    public void setDestinationEntryId(long destinationEntryId) {
        this.destinationEntryId = destinationEntryId;
    }

    public IncidentDestinationType getDestinationType() {
        return this.destinationType;
    }

    public void setDestinationType(IncidentDestinationType destinationType) {
        this.destinationType = destinationType;
    }

    public IncidentDirectionType getDirectionType() {
        return this.directionType;
    }

    public void setDirectionType(IncidentDirectionType directionType) {
        this.directionType = directionType;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncidentDropAttachment> getIncidentDropAttachments() {
        return this.incidentDropAttachments;
    }

    public void setIncidentDropAttachments(List<IncidentDropAttachment> incidentDropAttachments) {
        this.incidentDropAttachments = incidentDropAttachments;
    }

    public String getDestinationEntryName() {
        return this.destinationEntryName;
    }

    public void setDestinationEntryName(String destinationEntryName) {
        this.destinationEntryName = destinationEntryName;
    }

    public long getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    public boolean getIsReleased() {
        return this.isReleased;
    }

    public void setIsReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }

    public String getLocaleReleaseTime() {
        return this.localeReleaseTime;
    }

    public void setLocaleReleaseTime(String localeReleaseTime) {
        this.localeReleaseTime = localeReleaseTime;
    }

    public String getReleasedBy() {
        return this.releasedBy;
    }

    public void setReleasedBy(String releasedBy) {
        this.releasedBy = releasedBy;
    }

    public Timestamp getReleaseTime() {
        return this.releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public long getUrlCategoryId() {
        return this.urlCategoryId;
    }

    public void setUrlCategoryId(long urlCategoryId) {
        this.urlCategoryId = urlCategoryId;
    }

    public String getLocaleDestinationType() {
        return this.localeDestinationType;
    }

    public void setLocaleDestinationType(String localeDestinationType) {
        this.localeDestinationType = localeDestinationType;
    }

    public String getLocaleActionType() {
        return this.localeActionType;
    }

    public void setLocaleActionType(String localeActionType) {
        this.localeActionType = localeActionType;
    }

    public String getLocaleDirectionType() {
        return this.localeDirectionType;
    }

    public void setLocaleDirectionType(String localeDirectionType) {
        this.localeDirectionType = localeDirectionType;
    }

    public void addIncidentDropAttachment(IncidentDropAttachment incidentDropAttachment) {
        if (null == this.incidentDropAttachments) {
            this.incidentDropAttachments = Lists.newArrayList();
        }
        this.incidentDropAttachments.add(incidentDropAttachment);
    }

    @Override
    public String toString() {
        return "IncidentDestination{" +
                "id=" + id +
                ", incidentId=" + incidentId +
                ", destinationEntryId=" + destinationEntryId +
                ", destinationEntryName='" + destinationEntryName + '\'' +
                ", destinationType=" + destinationType +
                ", localeDestinationType='" + localeDestinationType + '\'' +
                ", destinationTypeCode=" + destinationTypeCode +
                ", actionType=" + actionType +
                ", localeActionType='" + localeActionType + '\'' +
                ", actionTypeCode=" + actionTypeCode +
                ", isReleased=" + isReleased +
                ", releasedBy='" + releasedBy + '\'' +
                ", localeReleaseTime='" + localeReleaseTime + '\'' +
                ", releaseTime=" + releaseTime +
                ", directionType=" + directionType +
                ", localeDirectionType='" + localeDirectionType + '\'' +
                ", directionTypeCode=" + directionTypeCode +
                ", urlCategoryId=" + urlCategoryId +
                ", destinationEntryInfo=" + destinationEntryInfo +
                ", incidentDropAttachments=" + incidentDropAttachments +
                '}';
    }
}

