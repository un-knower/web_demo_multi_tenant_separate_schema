/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ArrayListMultimap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Multimap
 *  org.joda.time.DateTime
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.BaseIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentKey;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentTrickleGroup;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info.IncidentEntryInfo;
import org.joda.time.DateTime;

import java.util.List;

public class NetworkIncident extends BaseIncident {

    private static final long serialVersionUID = -448610705439643394L;

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

    private boolean isReleased;
    @JsonIgnore
    private String localeReleaseStatus;

    private boolean hasForensics;

    @JsonIgnore
    private String directionTypes;
    @JsonIgnore
    private String directionNames;
    @JsonIgnore
    private IncidentWorkModeType workModeType;
    @JsonIgnore
    private String localeWorkModeType;
    private int workModeTypeCode;

    private boolean isVisible;
    private IncidentEntryInfo sourceEntryInfo;
    private List<IncidentDestination> incidentDestinations = Lists.newArrayList();
    private List<IncidentAttachment> incidentAttachments = Lists.newArrayList();

    @JsonIgnore
    private Multimap<String, IncidentTrickleGroup> incidentTrickleGroupMultimap = ArrayListMultimap.create();  //零星式关系
    @JsonIgnore
    private boolean exceedDestinationsLimit;
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

    public boolean getIsReleased() {
        return this.isReleased;
    }

    public void setIsReleased(boolean isReleased) {
        this.isReleased = isReleased;
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

    public IncidentWorkModeType getWorkModeType() {
        return this.workModeType;
    }

    public void setWorkModeType(IncidentWorkModeType workModeType) {
        this.workModeType = workModeType;
    }

    public String getLocaleWorkModeType() {
        return this.localeWorkModeType;
    }

    public void setLocaleWorkModeType(String localeWorkModeType) {
        this.localeWorkModeType = localeWorkModeType;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getLocaleChannelType() {
        return this.localeChannelType;
    }

    public void setLocaleChannelType(String localeChannelType) {
        this.localeChannelType = localeChannelType;
    }

    public String getLocaleReleaseStatus() {
        return this.localeReleaseStatus;
    }

    public void setLocaleReleaseStatus(String localeReleaseStatus) {
        this.localeReleaseStatus = localeReleaseStatus;
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

    public boolean getExceedDestinationsLimit() {
        return this.exceedDestinationsLimit;
    }

    public void setExceedDestinationsLimit(boolean exceedDestinationsLimit) {
        this.exceedDestinationsLimit = exceedDestinationsLimit;
    }

    public Multimap<String, IncidentTrickleGroup> getIncidentTrickleGroupMultimap() {
        return this.incidentTrickleGroupMultimap;
    }

    public void setIncidentTrickleGroupMultimap(Multimap<String, IncidentTrickleGroup> incidentTrickleGroupMultimap) {
        this.incidentTrickleGroupMultimap = incidentTrickleGroupMultimap;
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
            this.incidentKey.setIncidentType(IncidentType.NETWORK);
        }
        return this.incidentKey;
    }

    public void setIncidentKey(IncidentKey incidentKey) {
        this.incidentKey = incidentKey;
    }

    @Override
    public String toString() {
        return "NetworkIncident{" +
                "details='" + details + '\'' +
                ", channelType=" + channelType +
                ", localeChannelType='" + localeChannelType + '\'' +
                ", destinations='" + destinations + '\'' +
                ", sourceEntryId=" + sourceEntryId +
                ", sourceEntryName='" + sourceEntryName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", isReleased=" + isReleased +
                ", localeReleaseStatus='" + localeReleaseStatus + '\'' +
                ", hasForensics=" + hasForensics +
                ", directionTypes='" + directionTypes + '\'' +
                ", directionNames='" + directionNames + '\'' +
                ", workModeType=" + workModeType +
                ", localeWorkModeType='" + localeWorkModeType + '\'' +
                ", isVisible=" + isVisible +
                ", sourceEntryInfo=" + sourceEntryInfo +
                ", incidentDestinations=" + incidentDestinations +
                ", incidentAttachments=" + incidentAttachments +
                ", incidentTrickleGroupMultimap=" + incidentTrickleGroupMultimap +
                ", exceedDestinationsLimit=" + exceedDestinationsLimit +
                ", incidentKey=" + incidentKey +
                "} " + super.toString();
    }
}

