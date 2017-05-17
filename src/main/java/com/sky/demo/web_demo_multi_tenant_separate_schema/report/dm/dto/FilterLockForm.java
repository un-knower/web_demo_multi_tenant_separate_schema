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

public class FilterLockForm
implements Serializable {
    private static final long serialVersionUID = 3696315470538708574L;
    private boolean enableFilter;
    private Integer lockType;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public Integer getLockType() {
        return this.lockType;
    }

    public void setLockType(Integer lockType) {
        this.lockType = lockType;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("lockType", (Object)this.lockType).toString();
    }
}

