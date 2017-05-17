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
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.IncidentReleaseStatus;

import java.io.Serializable;

public class FilterReleaseForm
implements Serializable {
    private static final long serialVersionUID = -993204083022174276L;
    private boolean enableFilter;
    private Integer releaseType;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public Integer getReleaseType() {
        if (this.releaseType == null) {
            this.releaseType = IncidentReleaseStatus.NOT_RELEASED.getId();
        }
        return this.releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("releaseType", (Object)this.releaseType).toString();
    }
}

