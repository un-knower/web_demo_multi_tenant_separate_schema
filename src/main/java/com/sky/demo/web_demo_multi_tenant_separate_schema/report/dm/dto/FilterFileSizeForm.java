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

public class FilterFileSizeForm
implements Serializable {
    private static final long serialVersionUID = 8688322835484633827L;
    private boolean enableFilter;
    private Integer lower;
    private Integer upper;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public Integer getLower() {
        return this.lower;
    }

    public void setLower(Integer lower) {
        this.lower = lower;
    }

    public Integer getUpper() {
        return this.upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("upper", (Object)this.upper).add("lower", (Object)this.lower).add("enableFilter", this.enableFilter).toString();
    }
}

