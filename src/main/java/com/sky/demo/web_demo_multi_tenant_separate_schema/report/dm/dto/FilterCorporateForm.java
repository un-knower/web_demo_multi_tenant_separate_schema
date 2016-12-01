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

public class FilterCorporateForm
implements Serializable {
    private static final long serialVersionUID = 3369889613785049669L;
    private boolean enableFilter;
    private List<Integer> corporates;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<Integer> getCorporates() {
        return this.corporates;
    }

    public void setCorporates(List<Integer> corporates) {
        this.corporates = corporates;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("corporates", this.corporates).toString();
    }
}

