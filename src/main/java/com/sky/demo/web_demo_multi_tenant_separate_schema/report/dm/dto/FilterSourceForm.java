/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 *  com.sky.sps.base.common.base.RelationColumn
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;

public class FilterSourceForm
implements Serializable {
    private static final long serialVersionUID = -3499750570402002452L;
    private boolean enableFilter;
    private List<String> sources;
    private String customSources;
    private boolean isFuzzyQuery = true;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<String> getSources() {
        return this.sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getCustomSources() {
        return this.customSources;
    }

    public void setCustomSources(String customSources) {
        this.customSources = customSources;
    }

    public boolean isFuzzyQuery() {
        return this.isFuzzyQuery;
    }

    public void setFuzzyQuery(boolean isFuzzyQuery) {
        this.isFuzzyQuery = isFuzzyQuery;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("sources", this.sources).add("customSources", (Object)this.customSources).add("isFuzzyQuery", this.isFuzzyQuery).toString();
    }
}

