/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm;

import java.io.Serializable;

public class EchartsForm implements Serializable{

    private static final long serialVersionUID = 6474658982944952748L;
    private Object x;
    private String displayX;
    private long y;
    private Object z;
    private String displayZ;

    public Object getX() {
        return this.x;
    }

    public void setX(Object x) {
        this.x = x;
    }

    public String getDisplayX() {
        return this.displayX;
    }

    public void setDisplayX(String displayX) {
        this.displayX = displayX;
    }

    public long getY() {
        return this.y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public Object getZ() {
        return this.z;
    }

    public void setZ(Object z) {
        this.z = z;
    }

    public String getDisplayZ() {
        return this.displayZ;
    }

    public void setDisplayZ(String displayZ) {
        this.displayZ = displayZ;
    }

    public String toString() {
        return "EchartsForm [x=" + this.x + ", displayX=" + this.displayX + ", y=" + this.y + ", z=" + this.z + ", displayZ=" + this.displayZ + "]";
    }
}

