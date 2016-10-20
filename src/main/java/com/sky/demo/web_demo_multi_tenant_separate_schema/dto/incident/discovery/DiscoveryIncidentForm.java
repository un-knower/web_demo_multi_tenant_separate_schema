package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.discovery;

import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.BaseIncidentForm;

import java.io.Serializable;

/**
 * Created by user on 16/10/20.
 */
public class DiscoveryIncidentForm extends BaseIncidentForm implements Serializable {

    private static final long serialVersionUID = -7153559457884839740L;
    private boolean isLocked;
    private int resourceTypeCode;
    private String filePath;
    private String folderPath;
    private String fileName;
    private String fileExtension;
    private long fileSize;
    private int fileType;
    private String fileTypeName;
    private String fileOwner;
    private String folderOwner;
    private String localeFileCreatedTime;
    private String localeFileModifiedTime;
    private String localeFileAccessedTime;
    private String hostName;
    private String ip;
    private String sourceAddress;
    private String jobUuid;
    private String jobName;
    private int folderTypeCode;
    private int segmentId;
    private long segmentTag;
    private int deviceTypeCode;
    private String operationSystem;

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getResourceTypeCode() {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(int resourceTypeCode) {
        this.resourceTypeCode = resourceTypeCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFolderOwner() {
        return folderOwner;
    }

    public void setFolderOwner(String folderOwner) {
        this.folderOwner = folderOwner;
    }

    public String getLocaleFileCreatedTime() {
        return localeFileCreatedTime;
    }

    public void setLocaleFileCreatedTime(String localeFileCreatedTime) {
        this.localeFileCreatedTime = localeFileCreatedTime;
    }

    public String getLocaleFileModifiedTime() {
        return localeFileModifiedTime;
    }

    public void setLocaleFileModifiedTime(String localeFileModifiedTime) {
        this.localeFileModifiedTime = localeFileModifiedTime;
    }

    public String getLocaleFileAccessedTime() {
        return localeFileAccessedTime;
    }

    public void setLocaleFileAccessedTime(String localeFileAccessedTime) {
        this.localeFileAccessedTime = localeFileAccessedTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getFolderTypeCode() {
        return folderTypeCode;
    }

    public void setFolderTypeCode(int folderTypeCode) {
        this.folderTypeCode = folderTypeCode;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public long getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(long segmentTag) {
        this.segmentTag = segmentTag;
    }

    public int getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(int deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    @Override
    public String toString() {
        return "DiscoveryIncidentForm{" +
                "isLocked=" + isLocked +
                ", resourceTypeCode=" + resourceTypeCode +
                ", filePath='" + filePath + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", fileOwner='" + fileOwner + '\'' +
                ", folderOwner='" + folderOwner + '\'' +
                ", localeFileCreatedTime='" + localeFileCreatedTime + '\'' +
                ", localeFileModifiedTime='" + localeFileModifiedTime + '\'' +
                ", localeFileAccessedTime='" + localeFileAccessedTime + '\'' +
                ", hostName='" + hostName + '\'' +
                ", ip='" + ip + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", jobUuid='" + jobUuid + '\'' +
                ", jobName='" + jobName + '\'' +
                ", folderTypeCode=" + folderTypeCode +
                ", segmentId=" + segmentId +
                ", segmentTag=" + segmentTag +
                ", deviceTypeCode=" + deviceTypeCode +
                ", operationSystem='" + operationSystem + '\'' +
                "} " + super.toString();
    }
}
