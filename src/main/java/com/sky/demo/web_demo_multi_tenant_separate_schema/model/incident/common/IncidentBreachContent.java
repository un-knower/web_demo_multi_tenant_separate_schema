/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class IncidentBreachContent implements Serializable {

    private static final long serialVersionUID = -7842334458589605334L;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long incidentElementId;

    private String content;
    @JsonIgnore
    private IncidentLocationType locationType;
    private int locationTypeCode;
    private String locationTypesPath;
    private String locationNamesPath;
    private double similarity;
    private int matches;
    private int fileType;

    @JsonIgnore
    private String fileTypeName;
    @JsonIgnore
    private String encodeType;

    public int getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setLocationTypeCode(int locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncodeType() {
        return this.encodeType;
    }

    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIncidentElementId() {
        return this.incidentElementId;
    }

    public void setIncidentElementId(long incidentElementId) {
        this.incidentElementId = incidentElementId;
    }

    public String getLocationNamesPath() {
        return this.locationNamesPath;
    }

    public void setLocationNamesPath(String locationNamesPath) {
        this.locationNamesPath = locationNamesPath;
    }

    public IncidentLocationType getLocationType() {
        return this.locationType;
    }

    public void setLocationType(IncidentLocationType locationType) {
        this.locationType = locationType;
    }

    public String getLocationTypesPath() {
        return this.locationTypesPath;
    }

    public void setLocationTypesPath(String locationTypesPath) {
        this.locationTypesPath = locationTypesPath;
    }

    public double getSimilarity() {
        return this.similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public int getMatches() {
        return this.matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "IncidentBreachContent{" +
                "id=" + id +
                ", incidentElementId=" + incidentElementId +
                ", content='" + content + '\'' +
                ", locationType=" + locationType +
                ", locationTypeCode=" + locationTypeCode +
                ", locationTypesPath='" + locationTypesPath + '\'' +
                ", locationNamesPath='" + locationNamesPath + '\'' +
                ", similarity=" + similarity +
                ", matches=" + matches +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", encodeType='" + encodeType + '\'' +
                '}';
    }
}

