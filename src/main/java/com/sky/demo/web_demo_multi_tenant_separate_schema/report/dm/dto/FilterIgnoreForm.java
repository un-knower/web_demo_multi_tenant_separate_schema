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
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentIgnoreStatus;

import java.io.Serializable;

public class FilterIgnoreForm
implements Serializable {
    private static final long serialVersionUID = -1839804952185453685L;
    private boolean enableFilter;
    private Integer ignoredType;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public Integer getIgnoredType() {
        if (this.ignoredType == null) {
            this.ignoredType = IncidentIgnoreStatus.NOT_IGNORED.getId();
        }
        return this.ignoredType;
    }

    public void setIgnoredType(Integer ignoredType) {
        this.ignoredType = ignoredType;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("ignoredType", (Object)this.ignoredType).toString();
    }
}

