/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm;

public enum ReportType {
    DETAIL(1, "detail"),
    DASHBOARD(2, "dashboard"),
    TREND(3, "trend");
    
    private int code;
    private String desc;

    private ReportType(int code, String desc) {
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

    public static ReportType getReportTypeByCode(int code) {
        for (ReportType type : ReportType.values()) {
            if (type.getCode() != code) continue;
            return type;
        }
        return null;
    }
}

