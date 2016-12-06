/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class IncidentElement implements Serializable {

    private static final long serialVersionUID = 3525426646702499471L;
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long incidentPolicyId;

    private String conditionUuid;
    private String elementUuid;
    private String elementName;

    @JsonIgnore
    private IncidentElementType elementType;
    private int elementTypeCode;

    private int matches;
    private boolean isTruncated;    //截断
    private List<IncidentBreachContent> incidentBreachContents = Lists.newArrayList(); //违规内容

    public int getElementTypeCode() {
        return elementTypeCode;
    }

    public void setElementTypeCode(int elementTypeCode) {
        this.elementTypeCode = elementTypeCode;
    }

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public String getConditionUuid() {
        return this.conditionUuid;
    }

    public void setConditionUuid(String conditionUuid) {
        this.conditionUuid = conditionUuid;
    }

    public IncidentElementType getElementType() {
        return this.elementType;
    }

    public void setElementType(IncidentElementType elementType) {
        this.elementType = elementType;
    }

    public String getElementUuid() {
        return this.elementUuid;
    }

    public void setElementUuid(String elementUuid) {
        this.elementUuid = elementUuid;
    }

    public String getElementName() {
        return this.elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncidentBreachContent> getIncidentBreachContents() {
        return this.incidentBreachContents;
    }

    public void setIncidentBreachContents(List<IncidentBreachContent> incidentBreachContents) {
        this.incidentBreachContents = incidentBreachContents;
    }

    public long getIncidentPolicyId() {
        return this.incidentPolicyId;
    }

    public void setIncidentPolicyId(long incidentPolicyId) {
        this.incidentPolicyId = incidentPolicyId;
    }

    public boolean getIsTruncated() {
        return this.isTruncated;
    }

    public void setIsTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public int getMatches() {
        return this.matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void addIncidentBreachContent(IncidentBreachContent incidentBreachContent) {
        if (null == this.incidentBreachContents) {
            this.incidentBreachContents = Lists.newArrayList();
        }
        this.incidentBreachContents.add(incidentBreachContent);
    }

    @Override
    public String toString() {
        return "IncidentElement{" +
                "id=" + id +
                ", incidentPolicyId=" + incidentPolicyId +
                ", conditionUuid='" + conditionUuid + '\'' +
                ", elementUuid='" + elementUuid + '\'' +
                ", elementName='" + elementName + '\'' +
                ", elementType=" + elementType +
                ", elementTypeCode=" + elementTypeCode +
                ", matches=" + matches +
                ", isTruncated=" + isTruncated +
                ", incidentBreachContents=" + incidentBreachContents +
                '}';
    }
}

