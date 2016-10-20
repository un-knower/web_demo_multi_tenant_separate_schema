/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class IncidentElementInfo implements Serializable{

    private static final long serialVersionUID = -1097120839953884499L;
    private long id;
    private String uuid;
    private String name;
    private int matches;
    private boolean isTruncated;
    private String htmlBreachContents;
    private String plainBreachContents;
    private String formattedBreachContents;
    private Map<String, String> breachContentMap = Maps.newHashMap();
    private List<IncidentBreachContentInfo> incidentBreachContentInfos = Lists.newArrayList();

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getMatches() {
        return this.matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public boolean getIsTruncated() {
        return this.isTruncated;
    }

    public void setIsTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public String getHtmlBreachContents() {
        return this.htmlBreachContents;
    }

    public void setHtmlBreachContents(String htmlBreachContents) {
        this.htmlBreachContents = htmlBreachContents;
    }

    public String getPlainBreachContents() {
        return this.plainBreachContents;
    }

    public void setPlainBreachContents(String plainBreachContents) {
        this.plainBreachContents = plainBreachContents;
    }

    public String getFormattedBreachContents() {
        return this.formattedBreachContents;
    }

    public void setFormattedBreachContents(String formattedBreachContents) {
        this.formattedBreachContents = formattedBreachContents;
    }

    public Map<String, String> getBreachContentMap() {
        return this.breachContentMap;
    }

    public void setBreachContentMap(Map<String, String> breachContentMap) {
        this.breachContentMap = breachContentMap;
    }

    public List<IncidentBreachContentInfo> getIncidentBreachContentInfos() {
        return this.incidentBreachContentInfos;
    }

    public void setIncidentBreachContentInfos(List<IncidentBreachContentInfo> incidentBreachContentInfos) {
        this.incidentBreachContentInfos = incidentBreachContentInfos;
    }

    public void addIncidentBreachContentInfo(IncidentBreachContentInfo incidentBreachContentInfo) {
        if (null == this.incidentBreachContentInfos) {
            this.incidentBreachContentInfos = Lists.newArrayList();
        }
        this.incidentBreachContentInfos.add(incidentBreachContentInfo);
    }

    public void addBreachContent(String location, String breachContent) {
        if (null == this.breachContentMap) {
            this.breachContentMap = Maps.newHashMap();
        }
        this.breachContentMap.put(location, breachContent);
    }

    @Override
    public String toString() {
        return "IncidentElementInfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", matches=" + matches +
                ", isTruncated=" + isTruncated +
                ", htmlBreachContents='" + htmlBreachContents + '\'' +
                ", plainBreachContents='" + plainBreachContents + '\'' +
                ", formattedBreachContents='" + formattedBreachContents + '\'' +
                ", breachContentMap=" + breachContentMap +
                ", incidentBreachContentInfos=" + incidentBreachContentInfos +
                '}';
    }
}

