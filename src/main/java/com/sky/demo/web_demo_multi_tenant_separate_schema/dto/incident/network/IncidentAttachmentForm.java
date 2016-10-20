package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network;

import java.io.Serializable;

/**
 * Created by user on 16/10/20.
 */
public class IncidentAttachmentForm implements Serializable {

    private static final long serialVersionUID = -9129094027595568735L;
    private String fileName;
    private long fileSize;
    private String localeFileSize;
    private int fileType;
    private String fileTypeName;
    private String encodeType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getLocaleFileSize() {
        return localeFileSize;
    }

    public void setLocaleFileSize(String localeFileSize) {
        this.localeFileSize = localeFileSize;
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

    public String getEncodeType() {
        return encodeType;
    }

    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }

    @Override
    public String toString() {
        return "IncidentAttachmentForm{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", localeFileSize='" + localeFileSize + '\'' +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", encodeType='" + encodeType + '\'' +
                '}';
    }
}
