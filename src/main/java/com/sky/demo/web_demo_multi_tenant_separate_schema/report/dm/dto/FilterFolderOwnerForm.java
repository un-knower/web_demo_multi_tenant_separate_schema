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

public class FilterFolderOwnerForm
implements Serializable {
    private static final long serialVersionUID = 1316108987340343912L;
    private boolean enableFilter;
    private String owners;
    private boolean isFuzzyQuery = true;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public String getOwners() {
        return this.owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public boolean isFuzzyQuery() {
        return this.isFuzzyQuery;
    }

    public void setFuzzyQuery(boolean isFuzzyQuery) {
        this.isFuzzyQuery = isFuzzyQuery;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("owners", (Object)this.owners).add("isFuzzyQuery", this.isFuzzyQuery).toString();
    }
}

