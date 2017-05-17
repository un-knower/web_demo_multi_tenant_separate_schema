/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 *  com.sky.sps.base.common.base.RelationColumn
 *  org.springframework.format.annotation.DateTimeFormat
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.base.Objects;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class FilterIncidentTimeForm
implements Serializable {
    private static final long serialVersionUID = -3896859230584238070L;
    private boolean enableFilter;
    private PeriodTimeType timeType;
    private Date preciseFrom;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date preciseTo;
    private Integer lastPastDay;
    private boolean timePointOn = false;
    private String timePointFrom;
    private String timePointTo;

    public boolean isEnableFilter() {
        return this.enableFilter;
    }

    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public PeriodTimeType getTimeType() {
        return this.timeType;
    }

    public void setTimeType(PeriodTimeType timeType) {
        this.timeType = timeType;
    }

    public Date getPreciseFrom() {
        return this.preciseFrom;
    }

    public void setPreciseFrom(Date preciseFrom) {
        this.preciseFrom = preciseFrom;
    }

    public Date getPreciseTo() {
        return this.preciseTo;
    }

    public void setPreciseTo(Date preciseTo) {
        this.preciseTo = preciseTo;
    }

    public Integer getLastPastDay() {
        return this.lastPastDay;
    }

    public void setLastPastDay(Integer lastPastDay) {
        this.lastPastDay = lastPastDay;
    }

    public boolean isTimePointOn() {
        return this.timePointOn;
    }

    public void setTimePointOn(boolean timePointOn) {
        this.timePointOn = timePointOn;
    }

    public String getTimePointFrom() {
        return this.timePointFrom;
    }

    public void setTimePointFrom(String timePointFrom) {
        this.timePointFrom = timePointFrom;
    }

    public String getTimePointTo() {
        return this.timePointTo;
    }

    public void setTimePointTo(String timePointTo) {
        this.timePointTo = timePointTo;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("timePointTo", (Object)this.timePointTo).add("timePointFrom", (Object)this.timePointFrom).add("timePointOn", this.timePointOn).add("lastPastDay", (Object)this.lastPastDay).add("preciseTo", (Object)this.preciseTo).add("preciseFrom", (Object)this.preciseFrom).add("timeType", (Object)this.timeType).add("enableFilter", this.enableFilter).toString();
    }
}

