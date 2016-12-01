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

public class FilterChannelForm
implements Serializable {
    private static final long serialVersionUID = -1416466395163776967L;
    private boolean enableFilter;
    private List<Integer> channels;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public List<Integer> getChannels() {
        return this.channels;
    }

    public void setChannels(List<Integer> channels) {
        this.channels = channels;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("enableFilter", this.enableFilter).add("channels", this.channels).toString();
    }
}

