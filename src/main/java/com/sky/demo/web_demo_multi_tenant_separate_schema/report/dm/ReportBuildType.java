/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm;

public enum ReportBuildType {
    BUILD_IN(1, ""),
    USER_DEFINED(2, "");
    
    private int code;
    private String desc;

    private ReportBuildType(int code, String desc) {
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

    public static ReportBuildType getBuildTypeByCode(int code) {
        for (ReportBuildType type : ReportBuildType.values()) {
            if (type.getCode() != code) continue;
            return type;
        }
        return null;
    }
}

