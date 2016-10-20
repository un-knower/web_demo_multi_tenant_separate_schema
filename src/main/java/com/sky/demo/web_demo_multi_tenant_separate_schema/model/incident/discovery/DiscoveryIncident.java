/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.BaseIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.endpoint.IncidentDeviceType;

import java.sql.Timestamp;
import java.util.List;

public class DiscoveryIncident extends BaseIncident {

    private static final long serialVersionUID = 8460565358678684056L;

    private boolean isLocked;
    @JsonIgnore
    private String localeLockStatus;

    @JsonIgnore
    private String localeScanTime;
    @JsonIgnore
    private Timestamp scanTime;
    @JsonIgnore
    private IncidentDiscoveryResourceType resourceType;
    @JsonIgnore
    private String localeResourceType;
    private int resourceTypeCode;

    private String filePath;
    private String folderPath;
    private String fileName;
    private String fileExtension;

    @JsonIgnore
    private String localeFileSize;
    private long fileSize;

    private int fileType;
    private String fileTypeName;
    private String fileOwner;
    private String folderOwner;

    private String localeFileCreatedTime;
    @JsonIgnore
    private Timestamp fileCreatedTime;
    private String localeFileModifiedTime;
    @JsonIgnore
    private Timestamp fileModifiedTime;
    private String localeFileAccessedTime;
    @JsonIgnore
    private Timestamp fileAccessedTime;

    private String hostName;
    private String ip;
    private String sourceAddress;
    private String jobUuid;
    private String jobName;

    @JsonIgnore
    private IncidentFolderType folderType;
    @JsonIgnore
    private String localeFolderType;
    private int folderTypeCode;

    private int segmentId;
    private long segmentTag;

    @JsonIgnore
    private IncidentDeviceType deviceType;
    @JsonIgnore
    private String localeDeviceType;
    private int deviceTypeCode;

    private String operationSystem;

    @JsonIgnore
    private List<IncidentParam> incidentParams = Lists.newArrayList();

    public boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getLocaleScanTime() {
        return this.localeScanTime;
    }

    public void setLocaleScanTime(String localeScanTime) {
        this.localeScanTime = localeScanTime;
    }

    public Timestamp getScanTime() {
        return this.scanTime;
    }

    public void setScanTime(Timestamp scanTime) {
        this.scanTime = scanTime;
    }

    public IncidentDiscoveryResourceType getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(IncidentDiscoveryResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFolderPath() {
        return this.folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getLocaleFileSize() {
        return this.localeFileSize;
    }

    public void setLocaleFileSize(String localeFileSize) {
        this.localeFileSize = localeFileSize;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return this.fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileOwner() {
        return this.fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFolderOwner() {
        return this.folderOwner;
    }

    public void setFolderOwner(String folderOwner) {
        this.folderOwner = folderOwner;
    }

    public String getLocaleFileCreatedTime() {
        return this.localeFileCreatedTime;
    }

    public void setLocaleFileCreatedTime(String localeFileCreatedTime) {
        this.localeFileCreatedTime = localeFileCreatedTime;
    }

    public Timestamp getFileCreatedTime() {
        return this.fileCreatedTime;
    }

    public void setFileCreatedTime(Timestamp fileCreatedTime) {
        this.fileCreatedTime = fileCreatedTime;
    }

    public String getLocaleFileModifiedTime() {
        return this.localeFileModifiedTime;
    }

    public void setLocaleFileModifiedTime(String localeFileModifiedTime) {
        this.localeFileModifiedTime = localeFileModifiedTime;
    }

    public Timestamp getFileModifiedTime() {
        return this.fileModifiedTime;
    }

    public void setFileModifiedTime(Timestamp fileModifiedTime) {
        this.fileModifiedTime = fileModifiedTime;
    }

    public String getLocaleFileAccessedTime() {
        return this.localeFileAccessedTime;
    }

    public void setLocaleFileAccessedTime(String localeFileAccessedTime) {
        this.localeFileAccessedTime = localeFileAccessedTime;
    }

    public Timestamp getFileAccessedTime() {
        return this.fileAccessedTime;
    }

    public void setFileAccessedTime(Timestamp fileAccessedTime) {
        this.fileAccessedTime = fileAccessedTime;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSourceAddress() {
        return this.sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getJobUuid() {
        return this.jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public IncidentFolderType getFolderType() {
        return this.folderType;
    }

    public void setFolderType(IncidentFolderType folderType) {
        this.folderType = folderType;
    }

    public String getLocaleLockStatus() {
        return this.localeLockStatus;
    }

    public void setLocaleLockStatus(String localeLockStatus) {
        this.localeLockStatus = localeLockStatus;
    }

    public String getLocaleResourceType() {
        return this.localeResourceType;
    }

    public void setLocaleResourceType(String localeResourceType) {
        this.localeResourceType = localeResourceType;
    }

    public String getLocaleFolderType() {
        return this.localeFolderType;
    }

    public void setLocaleFolderType(String localeFolderType) {
        this.localeFolderType = localeFolderType;
    }

    public int getSegmentId() {
        return this.segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public long getSegmentTag() {
        return this.segmentTag;
    }

    public void setSegmentTag(long segmentTag) {
        this.segmentTag = segmentTag;
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

    public String getOperationSystem() {
        return this.operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public List<IncidentParam> getIncidentParams() {
        return this.incidentParams;
    }

    public void setIncidentParams(List<IncidentParam> incidentParams) {
        this.incidentParams = incidentParams;
    }

    public void addIncidentParam(IncidentParam incidentParam) {
        if (null == this.incidentParams) {
            this.incidentParams = Lists.newArrayList();
        }
        this.incidentParams.add(incidentParam);
    }

    @Override
    public String toString() {
        return "DiscoveryIncident{" +
                "isLocked=" + isLocked +
                ", localeLockStatus='" + localeLockStatus + '\'' +
                ", localeScanTime='" + localeScanTime + '\'' +
                ", scanTime=" + scanTime +
                ", resourceType=" + resourceType +
                ", localeResourceType='" + localeResourceType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", localeFileSize='" + localeFileSize + '\'' +
                ", fileSize=" + fileSize +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", fileOwner='" + fileOwner + '\'' +
                ", folderOwner='" + folderOwner + '\'' +
                ", localeFileCreatedTime='" + localeFileCreatedTime + '\'' +
                ", fileCreatedTime=" + fileCreatedTime +
                ", localeFileModifiedTime='" + localeFileModifiedTime + '\'' +
                ", fileModifiedTime=" + fileModifiedTime +
                ", localeFileAccessedTime='" + localeFileAccessedTime + '\'' +
                ", fileAccessedTime=" + fileAccessedTime +
                ", hostName='" + hostName + '\'' +
                ", ip='" + ip + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", jobUuid='" + jobUuid + '\'' +
                ", jobName='" + jobName + '\'' +
                ", folderType=" + folderType +
                ", localeFolderType='" + localeFolderType + '\'' +
                ", segmentId=" + segmentId +
                ", segmentTag=" + segmentTag +
                ", deviceType=" + deviceType +
                ", localeDeviceType='" + localeDeviceType + '\'' +
                ", operationSystem='" + operationSystem + '\'' +
                ", incidentParams=" + incidentParams +
                "} " + super.toString();
    }
}

