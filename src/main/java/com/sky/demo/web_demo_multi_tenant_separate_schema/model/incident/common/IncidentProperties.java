/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import java.io.Serializable;

public class IncidentProperties implements Serializable {

    private static final long serialVersionUID = -395213445547206357L;
    private String databaseName;
    private String tableName;
    private String contentEncoding;
    private String specifiedPolicies;
    private String filePath;
    private String fileName;
    private String fileSize;
    private String fileCreatedTime;
    private String fileAccessedTime;
    private String fileModifiedTime;
    private String fileOwner;
    private String folderOwner;
    private String hostname;
    private String ipAddress;
    private String checksum;
    private String filePermission;
    private String imSource;
    private String imDestination;
    private String sourceAppUuid;
    private String destinationAppUuid;
    private String sourceProcessName;
    private String destinationProcessName;
    private String sourceProcessId;
    private String DestinationProcessId;
    private String sourceThreadId;
    private String destinationThreadId;
    private String sourceProcessSid;
    private String destinationProcessSid;
    private String sourceAppPath;
    private String destinationAppPath;
    private String operationSystem;
    private String deviceType;
    private String emailInfo;
    private String internalDomain;
    private String fileType;
    private String agentVersion;
    private String agentHostname;
    private String agentIpAddress;

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getSpecifiedPolicies() {
        return this.specifiedPolicies;
    }

    public void setSpecifiedPolicies(String specifiedPolicies) {
        this.specifiedPolicies = specifiedPolicies;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileAccessedTime() {
        return this.fileAccessedTime;
    }

    public void setFileAccessedTime(String fileAccessedTime) {
        this.fileAccessedTime = fileAccessedTime;
    }

    public String getFileCreatedTime() {
        return this.fileCreatedTime;
    }

    public void setFileCreatedTime(String fileCreatedTime) {
        this.fileCreatedTime = fileCreatedTime;
    }

    public String getFileModifiedTime() {
        return this.fileModifiedTime;
    }

    public void setFileModifiedTime(String fileModifiedTime) {
        this.fileModifiedTime = fileModifiedTime;
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

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getFilePermission() {
        return this.filePermission;
    }

    public void setFilePermission(String filePermission) {
        this.filePermission = filePermission;
    }

    public String getImSource() {
        return this.imSource;
    }

    public void setImSource(String imSource) {
        this.imSource = imSource;
    }

    public String getImDestination() {
        return this.imDestination;
    }

    public void setImDestination(String imDestination) {
        this.imDestination = imDestination;
    }

    public String getSourceAppUuid() {
        return this.sourceAppUuid;
    }

    public void setSourceAppUuid(String sourceAppUuid) {
        this.sourceAppUuid = sourceAppUuid;
    }

    public String getDestinationAppUuid() {
        return this.destinationAppUuid;
    }

    public void setDestinationAppUuid(String destinationAppUuid) {
        this.destinationAppUuid = destinationAppUuid;
    }

    public String getSourceProcessName() {
        return this.sourceProcessName;
    }

    public void setSourceProcessName(String sourceProcessName) {
        this.sourceProcessName = sourceProcessName;
    }

    public String getDestinationProcessName() {
        return this.destinationProcessName;
    }

    public void setDestinationProcessName(String destinationProcessName) {
        this.destinationProcessName = destinationProcessName;
    }

    public String getSourceProcessId() {
        return this.sourceProcessId;
    }

    public void setSourceProcessId(String sourceProcessId) {
        this.sourceProcessId = sourceProcessId;
    }

    public String getDestinationProcessId() {
        return this.DestinationProcessId;
    }

    public void setDestinationProcessId(String destinationProcessId) {
        this.DestinationProcessId = destinationProcessId;
    }

    public String getSourceThreadId() {
        return this.sourceThreadId;
    }

    public void setSourceThreadId(String sourceThreadId) {
        this.sourceThreadId = sourceThreadId;
    }

    public String getDestinationThreadId() {
        return this.destinationThreadId;
    }

    public void setDestinationThreadId(String destinationThreadId) {
        this.destinationThreadId = destinationThreadId;
    }

    public String getSourceProcessSid() {
        return this.sourceProcessSid;
    }

    public void setSourceProcessSid(String sourceProcessSid) {
        this.sourceProcessSid = sourceProcessSid;
    }

    public String getDestinationProcessSid() {
        return this.destinationProcessSid;
    }

    public void setDestinationProcessSid(String destinationProcessSid) {
        this.destinationProcessSid = destinationProcessSid;
    }

    public String getSourceAppPath() {
        return this.sourceAppPath;
    }

    public void setSourceAppPath(String sourceAppPath) {
        this.sourceAppPath = sourceAppPath;
    }

    public String getDestinationAppPath() {
        return this.destinationAppPath;
    }

    public void setDestinationAppPath(String destinationAppPath) {
        this.destinationAppPath = destinationAppPath;
    }

    public String getOperationSystem() {
        return this.operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getEmailInfo() {
        return this.emailInfo;
    }

    public void setEmailInfo(String emailInfo) {
        this.emailInfo = emailInfo;
    }

    public String getInternalDomain() {
        return this.internalDomain;
    }

    public void setInternalDomain(String internalDomain) {
        this.internalDomain = internalDomain;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAgentVersion() {
        return this.agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    }

    public String getAgentHostname() {
        return this.agentHostname;
    }

    public void setAgentHostname(String agentHostname) {
        this.agentHostname = agentHostname;
    }

    public String getAgentIpAddress() {
        return this.agentIpAddress;
    }

    public void setAgentIpAddress(String agentIpAddress) {
        this.agentIpAddress = agentIpAddress;
    }

    @Override
    public String toString() {
        return "IncidentProperties{" +
                "databaseName='" + databaseName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", contentEncoding='" + contentEncoding + '\'' +
                ", specifiedPolicies='" + specifiedPolicies + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileCreatedTime='" + fileCreatedTime + '\'' +
                ", fileAccessedTime='" + fileAccessedTime + '\'' +
                ", fileModifiedTime='" + fileModifiedTime + '\'' +
                ", fileOwner='" + fileOwner + '\'' +
                ", folderOwner='" + folderOwner + '\'' +
                ", hostname='" + hostname + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", checksum='" + checksum + '\'' +
                ", filePermission='" + filePermission + '\'' +
                ", imSource='" + imSource + '\'' +
                ", imDestination='" + imDestination + '\'' +
                ", sourceAppUuid='" + sourceAppUuid + '\'' +
                ", destinationAppUuid='" + destinationAppUuid + '\'' +
                ", sourceProcessName='" + sourceProcessName + '\'' +
                ", destinationProcessName='" + destinationProcessName + '\'' +
                ", sourceProcessId='" + sourceProcessId + '\'' +
                ", DestinationProcessId='" + DestinationProcessId + '\'' +
                ", sourceThreadId='" + sourceThreadId + '\'' +
                ", destinationThreadId='" + destinationThreadId + '\'' +
                ", sourceProcessSid='" + sourceProcessSid + '\'' +
                ", destinationProcessSid='" + destinationProcessSid + '\'' +
                ", sourceAppPath='" + sourceAppPath + '\'' +
                ", destinationAppPath='" + destinationAppPath + '\'' +
                ", operationSystem='" + operationSystem + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", emailInfo='" + emailInfo + '\'' +
                ", internalDomain='" + internalDomain + '\'' +
                ", fileType='" + fileType + '\'' +
                ", agentVersion='" + agentVersion + '\'' +
                ", agentHostname='" + agentHostname + '\'' +
                ", agentIpAddress='" + agentIpAddress + '\'' +
                '}';
    }
}

