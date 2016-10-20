/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  org.joda.time.DateTime
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.google.common.collect.Multimap;
import org.joda.time.DateTime;

import java.io.Serializable;

public class IncidentKey implements Serializable {

    private static final long serialVersionUID = -7781540033442623760L;
    private long incidentId;
    private String transactionId;
    private DateTime incidentTime;
    private String partitionIndex;
    private IncidentType incidentType;
    private Multimap<String, String> trickleIncidentMultimap;

    public long getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public DateTime getIncidentTime() {
        return this.incidentTime;
    }

    public void setIncidentTime(DateTime incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getPartitionIndex() {
        return this.partitionIndex;
    }

    public void setPartitionIndex(String partitionIndex) {
        this.partitionIndex = partitionIndex;
    }

    public IncidentType getIncidentType() {
        return this.incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public Multimap<String, String> getTrickleIncidentMultimap() {
        return this.trickleIncidentMultimap;
    }

    public void setTrickleIncidentMultimap(Multimap<String, String> trickleIncidentMultimap) {
        this.trickleIncidentMultimap = trickleIncidentMultimap;
    }

    public String toString() {
        return this.transactionId;
    }


}

