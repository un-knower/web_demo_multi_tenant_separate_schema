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

public class FilterFileExtensionForm
implements Serializable {
    private static final long serialVersionUID = 8611383035647358114L;
    private boolean enableFilter;
    private String extensions;
    private boolean isFuzzyQuery = true;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public String getExtensions() {
        return this.extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public boolean isFuzzyQuery() {
        return this.isFuzzyQuery;
    }

    public void setFuzzyQuery(boolean isFuzzyQuery) {
        this.isFuzzyQuery = isFuzzyQuery;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("extensions", (Object)this.extensions).add("isFuzzyQuery", this.isFuzzyQuery).toString();
    }
}

