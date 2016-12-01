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

public class FilterPolicyForm
implements Serializable {
    private static final long serialVersionUID = -4256246589099717467L;
    private boolean enableFilter;
    private List<String> policies;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<String> getPolicies() {
        return this.policies;
    }

    public void setPolicies(List<String> policies) {
        this.policies = policies;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("policies", this.policies).toString();
    }
}

