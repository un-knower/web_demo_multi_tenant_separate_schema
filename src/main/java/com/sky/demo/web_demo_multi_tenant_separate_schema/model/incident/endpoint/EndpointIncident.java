/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ArrayListMultimap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Multimap
 *  org.joda.time.DateTime
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.BaseIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentKey;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentTrickleGroup;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery.IncidentParam;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info.IncidentEntryInfo;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.IncidentAttachment;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.IncidentChannelType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.IncidentDestination;
import org.joda.time.DateTime;

import java.util.List;

public class EndpointIncident extends BaseIncident {

    private static final long serialVersionUID = 3526672523203548681L;

    private String details;

    @JsonIgnore
    private IncidentChannelType channelType;
    @JsonIgnore
    private String localeChannelType;
    private int channelTypeCode;

    private String destinations;

    @JsonIgnore
    private long sourceEntryId;

    private String sourceEntryName;
    private String attachmentNames;
    private boolean hasAttachment;
    private boolean hasForensics;
    private String directionTypes;      // ;
    private String directionNames;

    @JsonIgnore
    private IncidentEndpointOperationType operationType;
    @JsonIgnore
    private String localeOperationType;
    private int operationTypeCode;
    @JsonIgnore
    private IncidentDeviceType deviceType;
    @JsonIgnore
    private String localeDeviceType;
    private int deviceTypeCode;
    @JsonIgnore
    private IncidentCorporateType corporateType;
    @JsonIgnore
    private String localeCorporateType;
    private int corporateTypeCode;

    private String operationSystem;
    private String hostname;
    private boolean isVisible;
    private IncidentEntryInfo sourceEntryInfo;
    private List<IncidentDestination> incidentDestinations = Lists.newArrayList();
    private List<IncidentAttachment> incidentAttachments = Lists.newArrayList();

    @JsonIgnore
    private List<IncidentParam> incidentParams = Lists.newArrayList();
    @JsonIgnore
    private Multimap<String, IncidentTrickleGroup> incidentTrickleGroupMultimap = ArrayListMultimap.create();
    @JsonIgnore
    private boolean exceedDestinationsLimit;
    @JsonIgnore
    private boolean isConfirmTimeout;
    @JsonIgnore
    private String confirmReason;
    @JsonIgnore
    private IncidentKey incidentKey;

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public IncidentChannelType getChannelType() {
        return this.channelType;
    }

    public void setChannelType(IncidentChannelType channelType) {
        this.channelType = channelType;
    }

    public String getDestinations() {
        return this.destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public long getSourceEntryId() {
        return this.sourceEntryId;
    }

    public void setSourceEntryId(long sourceEntryId) {
        this.sourceEntryId = sourceEntryId;
    }

    public String getSourceEntryName() {
        return this.sourceEntryName;
    }

    public void setSourceEntryName(String sourceEntryName) {
        this.sourceEntryName = sourceEntryName;
    }

    public String getAttachmentNames() {
        return this.attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public boolean getHasAttachment() {
        return this.hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public IncidentEndpointOperationType getOperationType() {
        return this.operationType;
    }

    public void setOperationType(IncidentEndpointOperationType operationType) {
        this.operationType = operationType;
    }

    public IncidentDeviceType getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(IncidentDeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocaleDeviceType() {
        return this.localeDeviceType;
    }

    public void setLocaleDeviceType(String localeDeviceType) {
        this.localeDeviceType = localeDeviceType;
    }

    public IncidentCorporateType getCorporateType() {
        return this.corporateType;
    }

    public void setCorporateType(IncidentCorporateType corporateType) {
        this.corporateType = corporateType;
    }

    public String getLocaleCorporateType() {
        return this.localeCorporateType;
    }

    public void setLocaleCorporateType(String localeCorporateType) {
        this.localeCorporateType = localeCorporateType;
    }

    public String getOperationSystem() {
        return this.operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getHasForensics() {
        return this.hasForensics;
    }

    public void setHasForensics(boolean hasForensics) {
        this.hasForensics = hasForensics;
    }

    public String getDirectionTypes() {
        return this.directionTypes;
    }

    public void setDirectionTypes(String directionTypes) {
        this.directionTypes = directionTypes;
    }

    public String getDirectionNames() {
        return this.directionNames;
    }

    public void setDirectionNames(String directionNames) {
        this.directionNames = directionNames;
    }

    public String getLocaleChannelType() {
        return this.localeChannelType;
    }

    public void setLocaleChannelType(String localeChannelType) {
        this.localeChannelType = localeChannelType;
    }

    public String getLocaleOperationType() {
        return this.localeOperationType;
    }

    public void setLocaleOperationType(String localeOperationType) {
        this.localeOperationType = localeOperationType;
    }

    public IncidentEntryInfo getSourceEntryInfo() {
        return this.sourceEntryInfo;
    }

    public void setSourceEntryInfo(IncidentEntryInfo sourceEntryInfo) {
        this.sourceEntryInfo = sourceEntryInfo;
    }

    public List<IncidentDestination> getIncidentDestinations() {
        return this.incidentDestinations;
    }

    public void setIncidentDestinations(List<IncidentDestination> incidentDestinations) {
        this.incidentDestinations = incidentDestinations;
    }

    public List<IncidentAttachment> getIncidentAttachments() {
        return this.incidentAttachments;
    }

    public void setIncidentAttachments(List<IncidentAttachment> incidentAttachments) {
        this.incidentAttachments = incidentAttachments;
    }

    public List<IncidentParam> getIncidentParams() {
        return this.incidentParams;
    }

    public void setIncidentParams(List<IncidentParam> incidentParams) {
        this.incidentParams = incidentParams;
    }

    public Multimap<String, IncidentTrickleGroup> getIncidentTrickleGroupMultimap() {
        return this.incidentTrickleGroupMultimap;
    }

    public void setIncidentTrickleGroupMultimap(Multimap<String, IncidentTrickleGroup> incidentTrickleGroupMultimap) {
        this.incidentTrickleGroupMultimap = incidentTrickleGroupMultimap;
    }

    public boolean getExceedDestinationsLimit() {
        return this.exceedDestinationsLimit;
    }

    public void setExceedDestinationsLimit(boolean exceedDestinationsLimit) {
        this.exceedDestinationsLimit = exceedDestinationsLimit;
    }

    public boolean getIsConfirmTimeout() {
        return this.isConfirmTimeout;
    }

    public void setIsConfirmTimeout(boolean isConfirmTimeout) {
        this.isConfirmTimeout = isConfirmTimeout;
    }

    public String getConfirmReason() {
        return this.confirmReason;
    }

    public void setConfirmReason(String confirmReason) {
        this.confirmReason = confirmReason;
    }

    public void addIncidentAttachment(IncidentAttachment incidentAttachment) {
        if (null == this.incidentAttachments) {
            this.incidentAttachments = Lists.newArrayList();
        }
        this.incidentAttachments.add(incidentAttachment);
    }

    public void addIncidentDestination(IncidentDestination incidentDestination) {
        if (null == this.incidentDestinations) {
            this.incidentDestinations = Lists.newArrayList();
        }
        this.incidentDestinations.add(incidentDestination);
    }

    public void addIncidentParam(IncidentParam incidentParam) {
        if (null == this.incidentParams) {
            this.incidentParams = Lists.newArrayList();
        }
        this.incidentParams.add(incidentParam);
    }

    public void addIncidentTrickleGroup(String policyUuid, IncidentTrickleGroup incidentTrickleGroup) {
        if (null == this.incidentTrickleGroupMultimap) {
            this.incidentTrickleGroupMultimap = ArrayListMultimap.create();
        }
        this.incidentTrickleGroupMultimap.put(policyUuid, incidentTrickleGroup);
    }

    public IncidentKey getIncidentKey() {
        if (null == this.incidentKey) {
            this.incidentKey = new IncidentKey();
            this.incidentKey.setIncidentId(this.getId());
            this.incidentKey.setTransactionId(this.getTransactionId());
            this.incidentKey.setIncidentTime(new DateTime((Object)this.getIncidentTime()));
            this.incidentKey.setIncidentType(IncidentType.ENDPOINT);
        }
        return this.incidentKey;
    }

    public void setIncidentKey(IncidentKey incidentKey) {
        this.incidentKey = incidentKey;
    }

    @Override
    public String toString() {
        return "EndpointIncident{" +
                "details='" + details + '\'' +
                ", channelType=" + channelType +
                ", localeChannelType='" + localeChannelType + '\'' +
                ", destinations='" + destinations + '\'' +
                ", sourceEntryId=" + sourceEntryId +
                ", sourceEntryName='" + sourceEntryName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", hasForensics=" + hasForensics +
                ", directionTypes='" + directionTypes + '\'' +
                ", directionNames='" + directionNames + '\'' +
                ", operationType=" + operationType +
                ", localeOperationType='" + localeOperationType + '\'' +
                ", deviceType=" + deviceType +
                ", localeDeviceType='" + localeDeviceType + '\'' +
                ", corporateType=" + corporateType +
                ", localeCorporateType='" + localeCorporateType + '\'' +
                ", operationSystem='" + operationSystem + '\'' +
                ", hostname='" + hostname + '\'' +
                ", isVisible=" + isVisible +
                ", sourceEntryInfo=" + sourceEntryInfo +
                ", incidentDestinations=" + incidentDestinations +
                ", incidentAttachments=" + incidentAttachments +
                ", incidentParams=" + incidentParams +
                ", incidentTrickleGroupMultimap=" + incidentTrickleGroupMultimap +
                ", exceedDestinationsLimit=" + exceedDestinationsLimit +
                ", isConfirmTimeout=" + isConfirmTimeout +
                ", confirmReason='" + confirmReason + '\'' +
                ", incidentKey=" + incidentKey +
                "} " + super.toString();
    }
}

