package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.info.IncidentEntryInfoForm;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/10/20.
 */
public class IncidentDestinationForm implements Serializable {

    private static final long serialVersionUID = 7526768000148082333L;
    private String destinationEntryName;
    private int destinationTypeCode;
    private int actionTypeCode;
    private boolean isReleased;
    private String releasedBy;
    private String localeReleaseTime;
    private int directionTypeCode;
    private IncidentEntryInfoForm destinationEntryInfo;     //目标详情
    private List<String> incidentDropAttachments = Lists.newArrayList();

    public String getDestinationEntryName() {
        return destinationEntryName;
    }

    public void setDestinationEntryName(String destinationEntryName) {
        this.destinationEntryName = destinationEntryName;
    }

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

    public String getReleasedBy() {
        return releasedBy;
    }

    public void setReleasedBy(String releasedBy) {
        this.releasedBy = releasedBy;
    }

    public String getLocaleReleaseTime() {
        return localeReleaseTime;
    }

    public void setLocaleReleaseTime(String localeReleaseTime) {
        this.localeReleaseTime = localeReleaseTime;
    }

    public int getDirectionTypeCode() {
        return directionTypeCode;
    }

    public void setDirectionTypeCode(int directionTypeCode) {
        this.directionTypeCode = directionTypeCode;
    }

    public IncidentEntryInfoForm getDestinationEntryInfo() {
        return destinationEntryInfo;
    }

    public void setDestinationEntryInfo(IncidentEntryInfoForm destinationEntryInfo) {
        this.destinationEntryInfo = destinationEntryInfo;
    }

    public List<String> getIncidentDropAttachments() {
        return incidentDropAttachments;
    }

    public void setIncidentDropAttachments(List<String> incidentDropAttachments) {
        this.incidentDropAttachments = incidentDropAttachments;
    }

    @Override
    public String toString() {
        return "IncidentDestinationForm{" +
                "destinationEntryName='" + destinationEntryName + '\'' +
                ", destinationTypeCode=" + destinationTypeCode +
                ", actionTypeCode=" + actionTypeCode +
                ", isReleased=" + isReleased +
                ", releasedBy='" + releasedBy + '\'' +
                ", localeReleaseTime='" + localeReleaseTime + '\'' +
                ", directionTypeCode=" + directionTypeCode +
                ", destinationEntryInfo=" + destinationEntryInfo +
                ", incidentDropAttachments=" + incidentDropAttachments +
                '}';
    }
}
