package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.endpoint;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.BaseIncidentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.info.IncidentEntryInfoForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.IncidentAttachmentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.IncidentDestinationForm;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/10/20.
 */
public class EndpointIncidentForm extends BaseIncidentForm implements Serializable {

    private static final long serialVersionUID = 6086739996748250034L;
    private String details;
    private int channelTypeCode;
    private String destinations;
    private String sourceEntryName;
    private String attachmentNames;
    private boolean hasAttachment;
    private boolean hasForensics;
    private String directionTypes;      // ;
    private String directionNames;
    private int operationTypeCode;
    private int deviceTypeCode;
    private int corporateTypeCode;
    private String operationSystem;
    private String hostname;
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

    public boolean isHasForensics() {
        return hasForensics;
    }

    public void setHasForensics(boolean hasForensics) {
        this.hasForensics = hasForensics;
    }

    public String getDirectionTypes() {
        return directionTypes;
    }

    public void setDirectionTypes(String directionTypes) {
        this.directionTypes = directionTypes;
    }

    public String getDirectionNames() {
        return directionNames;
    }

    public void setDirectionNames(String directionNames) {
        this.directionNames = directionNames;
    }

    public int getOperationTypeCode() {
        return operationTypeCode;
    }

    public void setOperationTypeCode(int operationTypeCode) {
        this.operationTypeCode = operationTypeCode;
    }

    public int getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(int deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public int getCorporateTypeCode() {
        return corporateTypeCode;
    }

    public void setCorporateTypeCode(int corporateTypeCode) {
        this.corporateTypeCode = corporateTypeCode;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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
        return "EndpointIncidentForm{" +
                "details='" + details + '\'' +
                ", channelTypeCode=" + channelTypeCode +
                ", destinations='" + destinations + '\'' +
                ", sourceEntryName='" + sourceEntryName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", hasForensics=" + hasForensics +
                ", directionTypes='" + directionTypes + '\'' +
                ", directionNames='" + directionNames + '\'' +
                ", operationTypeCode=" + operationTypeCode +
                ", deviceTypeCode=" + deviceTypeCode +
                ", corporateTypeCode=" + corporateTypeCode +
                ", operationSystem='" + operationSystem + '\'' +
                ", hostname='" + hostname + '\'' +
                ", isVisible=" + isVisible +
                ", sourceEntryInfo=" + sourceEntryInfo +
                ", incidentDestinations=" + incidentDestinations +
                ", incidentAttachments=" + incidentAttachments +
                "} " + super.toString();
    }
}
