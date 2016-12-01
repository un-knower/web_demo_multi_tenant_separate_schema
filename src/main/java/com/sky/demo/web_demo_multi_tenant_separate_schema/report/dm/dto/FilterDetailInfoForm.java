/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 *  com.skyguard.sps.base.common.base.RelationColumn
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.base.Objects;

import java.io.Serializable;

public class FilterDetailInfoForm
implements Serializable {
    private static final long serialVersionUID = 6674492954887078124L;
    private boolean enableFilter;
    private String details;
    private boolean isFuzzyQuery = true;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isFuzzyQuery() {
        return this.isFuzzyQuery;
    }

    public void setFuzzyQuery(boolean isFuzzyQuery) {
        this.isFuzzyQuery = isFuzzyQuery;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("details", (Object)this.details).add("isFuzzyQuery", this.isFuzzyQuery).toString();
    }
}

