/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.sky.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum PeriodTimeType {
    CUSTOM_TIME(0, "dlp_report_period_time_type_custom_time"),
    LAST_PAST_N_DAY(1, "dlp_report_period_time_type_last_past_n_day"),
    LAST_24_HOURS(2, "dlp_report_period_time_type_last_24_hours"),
    YESTERDAY(3, "dlp_report_period_time_type_yesterday"),
    THIS_WEEK(4, "dlp_report_period_time_type_this_week"),
    LAST_WEEK(5, "dlp_report_period_time_type_last_week"),
    THIS_MONTH(6, "dlp_report_period_time_type_this_month"),
    LAST_MONTH(7, "dlp_report_period_time_type_last_month"),
    THIS_QUARTER(8, "dlp_report_period_time_type_this_quarter"),
    LAST_QUARTER(9, "dlp_report_period_time_type_last_quarter"),
    THIS_YEAR(10, "dlp_report_period_time_type_this_year");
    
    private int code;
    private String desc;
//    public static final List<PeriodTimeType> PERIOD_TIME_TYPES;

    private PeriodTimeType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDisplayName() {
        return Localizer.getMessage((String) this.getDesc());
    }

    public static PeriodTimeType get(int code) {
        for (PeriodTimeType timeType : PeriodTimeType.values()) {
            if (timeType.getCode() != code) continue;
            return timeType;
        }
        return null;
    }

//    static {
//        PERIOD_TIME_TYPES = ImmutableList.of(LAST_PAST_N_DAY, LAST_24_HOURS, (Object)((Object)YESTERDAY), (Object)((Object)THIS_WEEK), (Object)((Object)LAST_WEEK), (Object)((Object)THIS_MONTH), (Object)((Object)LAST_MONTH), (Object)((Object)THIS_QUARTER), (Object)((Object)LAST_QUARTER), (Object)((Object)THIS_YEAR), (Object)((Object)CUSTOM_TIME));
//    }
}

