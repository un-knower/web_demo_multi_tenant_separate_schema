/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class IncidentDropAttachment implements Serializable {

    private static final long serialVersionUID = 320974253155237649L;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long incidentDestinationId;

    private String fileName;

    public long getIncidentDestinationId() {
        return this.incidentDestinationId;
    }

    public void setIncidentDestinationId(long incidentDestinationId) {
        this.incidentDestinationId = incidentDestinationId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "IncidentDropAttachment{" +
                "id=" + id +
                ", incidentDestinationId=" + incidentDestinationId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

