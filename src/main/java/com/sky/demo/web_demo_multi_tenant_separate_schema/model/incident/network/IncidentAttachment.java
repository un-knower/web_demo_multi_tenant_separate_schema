/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class IncidentAttachment implements Serializable {

    private static final long serialVersionUID = -2296458434278559105L;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long incidentId;

    private String fileName;
    private long fileSize;

    @JsonIgnore
    private String localeFileSize;
    private int fileType;
    @JsonIgnore
    private String fileTypeName;
    @JsonIgnore
    private String encodeType;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEncodeType() {
        return this.encodeType;
    }

    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getLocaleFileSize() {
        return this.localeFileSize;
    }

    public void setLocaleFileSize(String localeFileSize) {
        this.localeFileSize = localeFileSize;
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

    public long getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    @Override
    public String toString() {
        return "IncidentAttachment{" +
                "id=" + id +
                ", incidentId=" + incidentId +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", localeFileSize='" + localeFileSize + '\'' +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", encodeType='" + encodeType + '\'' +
                '}';
    }
}

