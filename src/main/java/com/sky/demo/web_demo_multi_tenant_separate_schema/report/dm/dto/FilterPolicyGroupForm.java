/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;

public class FilterPolicyGroupForm
implements Serializable {
    private static final long serialVersionUID = -6152223721585457541L;
    private boolean enableFilter;
    private List<String> policyGroups;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<String> getPolicyGroups() {
        return this.policyGroups;
    }

    public void setPolicyGroups(List<String> policyGroups) {
        this.policyGroups = policyGroups;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("policyGroups", this.policyGroups).toString();
    }
}

