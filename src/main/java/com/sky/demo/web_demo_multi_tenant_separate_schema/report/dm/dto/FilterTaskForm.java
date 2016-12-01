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

public class FilterTaskForm
implements Serializable {
    private static final long serialVersionUID = -5445277484876324246L;
    private boolean enableFilter;
    private List<String> tasks;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<String> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("tasks", this.tasks).add("enableFilter", this.enableFilter).toString();
    }
}

