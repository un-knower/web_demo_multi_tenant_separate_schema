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

public class FilterTransactionIdForm
implements Serializable {
    private static final long serialVersionUID = 9138643933343013188L;
    private boolean enableFilter;
    private String ids;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public String getIds() {
        return this.ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("ids", (Object)this.ids).toString();
    }
}

