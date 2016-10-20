package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/10/20.
 */
public class IncidentElementForm implements Serializable {

    private static final long serialVersionUID = -8376966491680639748L;
    private String conditionUuid;
    private String elementUuid;
    private String elementName;
    private int elementTypeCode;
    private int matches;
    private boolean isTruncated;    //截断
    private List<IncidentBreachContentForm> incidentBreachContents = Lists.newArrayList(); //违规内容

    public String getConditionUuid() {
        return conditionUuid;
    }

    public void setConditionUuid(String conditionUuid) {
        this.conditionUuid = conditionUuid;
    }

    public String getElementUuid() {
        return elementUuid;
    }

    public void setElementUuid(String elementUuid) {
        this.elementUuid = elementUuid;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public int getElementTypeCode() {
        return elementTypeCode;
    }

    public void setElementTypeCode(int elementTypeCode) {
        this.elementTypeCode = elementTypeCode;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public List<IncidentBreachContentForm> getIncidentBreachContents() {
        return incidentBreachContents;
    }

    public void setIncidentBreachContents(List<IncidentBreachContentForm> incidentBreachContents) {
        this.incidentBreachContents = incidentBreachContents;
    }

    @Override
    public String toString() {
        return "IncidentElementForm{" +
                "conditionUuid='" + conditionUuid + '\'' +
                ", elementUuid='" + elementUuid + '\'' +
                ", elementName='" + elementName + '\'' +
                ", elementTypeCode=" + elementTypeCode +
                ", matches=" + matches +
                ", isTruncated=" + isTruncated +
                ", incidentBreachContents=" + incidentBreachContents +
                '}';
    }
}
