package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.BaseIncidentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.info.IncidentEntryInfoForm;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/10/20.
 */
public class NetworkIncidentForm extends BaseIncidentForm implements Serializable {

    private static final long serialVersionUID = 2660982442839156927L;
    private String details;
    private int channelTypeCode;
    private String destinations;
    private String sourceEntryName;
    private String attachmentNames;
    private boolean hasAttachment;
    private boolean isReleased;
    private String localeReleaseStatus;
    private boolean hasForensics;
    private int workModeTypeCode;
    private boolean isVisible;
    private IncidentEntryInfoForm sourceEntryInfo;
    private List<IncidentDestinationForm> incidentDestinations = Lists.newArrayList();
    private List<IncidentAttachmentForm> incidentAttachments = Lists.newArrayList();

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getChannelTypeCode() {
        return channelTypeCode;
    }

    public void setChannelTypeCode(int channelTypeCode) {
        this.channelTypeCode = channelTypeCode;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getSourceEntryName() {
        return sourceEntryName;
    }

    public void setSourceEntryName(String sourceEntryName) {
        this.sourceEntryName = sourceEntryName;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }

    public String getLocaleReleaseStatus() {
        return localeReleaseStatus;
    }

    public void setLocaleReleaseStatus(String localeReleaseStatus) {
        this.localeReleaseStatus = localeReleaseStatus;
    }

    public boolean isHasForensics() {
        return hasForensics;
    }

    public void setHasForensics(boolean hasForensics) {
        this.hasForensics = hasForensics;
    }

    public int getWorkModeTypeCode() {
        return workModeTypeCode;
    }

    public void setWorkModeTypeCode(int workModeTypeCode) {
        this.workModeTypeCode = workModeTypeCode;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public IncidentEntryInfoForm getSourceEntryInfo() {
        return sourceEntryInfo;
    }

    public void setSourceEntryInfo(IncidentEntryInfoForm sourceEntryInfo) {
        this.sourceEntryInfo = sourceEntryInfo;
    }

    public List<IncidentDestinationForm> getIncidentDestinations() {
        return incidentDestinations;
    }

    public void setIncidentDestinations(List<IncidentDestinationForm> incidentDestinations) {
        this.incidentDestinations = incidentDestinations;
    }

    public List<IncidentAttachmentForm> getIncidentAttachments() {
        return incidentAttachments;
    }

    public void setIncidentAttachments(List<IncidentAttachmentForm> incidentAttachments) {
        this.incidentAttachments = incidentAttachments;
    }

    @Override
    public String toString() {
        return "NetworkIncidentForm{" +
                "details='" + details + '\'' +
                ", channelTypeCode=" + channelTypeCode +
                ", destinations='" + destinations + '\'' +
                ", sourceEntryName='" + sourceEntryName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", isReleased=" + isReleased +
                ", localeReleaseStatus='" + localeReleaseStatus + '\'' +
                ", hasForensics=" + hasForensics +
                ", workModeTypeCode=" + workModeTypeCode +
                ", isVisible=" + isVisible +
                ", sourceEntryInfo=" + sourceEntryInfo +
                ", incidentDestinations=" + incidentDestinations +
                ", incidentAttachments=" + incidentAttachments +
                "} " + super.toString();
    }
}
