package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by user on 16/10/20.
 */
public class IncidentBreachContentForm implements Serializable {

    private static final long serialVersionUID = 8029127221550507803L;
    private String content;
    private int locationTypeCode;
    private String locationTypesPath;
    private String locationNamesPath;
    private double similarity;
    private int matches;
    private int fileType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setLocationTypeCode(int locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public String getLocationTypesPath() {
        return locationTypesPath;
    }

    public void setLocationTypesPath(String locationTypesPath) {
        this.locationTypesPath = locationTypesPath;
    }

    public String getLocationNamesPath() {
        return locationNamesPath;
    }

    public void setLocationNamesPath(String locationNamesPath) {
        this.locationNamesPath = locationNamesPath;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "IncidentBreachContentForm{" +
                "content='" + content + '\'' +
                ", locationTypeCode=" + locationTypeCode +
                ", locationTypesPath='" + locationTypesPath + '\'' +
                ", locationNamesPath='" + locationNamesPath + '\'' +
                ", similarity=" + similarity +
                ", matches=" + matches +
                ", fileType=" + fileType +
                '}';
    }
}
