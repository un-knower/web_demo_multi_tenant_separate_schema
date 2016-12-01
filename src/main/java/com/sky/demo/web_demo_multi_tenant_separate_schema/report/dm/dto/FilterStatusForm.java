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
import java.util.List;

public class FilterStatusForm
implements Serializable {
    private static final long serialVersionUID = -2160119935065355584L;
    private boolean enableFilter;
    private List<Integer> statuses;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<Integer> getStatuses() {
        return this.statuses;
    }

    public void setStatuses(List<Integer> statuses) {
        this.statuses = statuses;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("statuses", this.statuses).toString();
    }
}

