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

public class FilterWorkModeForm
implements Serializable {
    private static final long serialVersionUID = 5607147495830081342L;
    private boolean enableFilter;
    private Integer WorkModeType;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public Integer getWorkModeType() {
        return this.WorkModeType;
    }

    public void setWorkModeType(Integer workModeType) {
        this.WorkModeType = workModeType;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("WorkModeType", (Object)this.WorkModeType).toString();
    }
}

