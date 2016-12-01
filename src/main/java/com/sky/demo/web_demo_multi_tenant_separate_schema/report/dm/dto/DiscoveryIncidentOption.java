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

public class DiscoveryIncidentOption
implements Serializable {
    private static final long serialVersionUID = -8186642117289643518L;
    private long cnt;
    private String jobName;
    private int resourceType;
    private String sourceAddress;

    public long getCnt() {
        return this.cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public String getSourceAddress() {
        return this.sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("cnt", this.cnt).add("jobName", (Object)this.jobName).add("resourceType", this.resourceType).add("sourceAddress", (Object)this.sourceAddress).toString();
    }
}

