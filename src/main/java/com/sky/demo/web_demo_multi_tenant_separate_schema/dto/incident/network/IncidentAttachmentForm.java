package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network;

import java.io.Serializable;

/**
 * Created by user on 16/10/20.
 */
public class IncidentAttachmentForm implements Serializable {

    private static final long serialVersionUID = -9129094027595568735L;
    private String fileName;
    private long fileSize;
    private int fileType;

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

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "IncidentAttachmentForm{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileType=" + fileType +
                '}';
    }
}
