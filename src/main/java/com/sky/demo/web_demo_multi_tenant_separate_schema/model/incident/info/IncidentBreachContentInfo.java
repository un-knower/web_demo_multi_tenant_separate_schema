/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info;

import java.io.Serializable;

public class IncidentBreachContentInfo implements Serializable {

    private static final long serialVersionUID = -1596296972610809239L;
    private String content;
    private String location;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "IncidentBreachContentInfo{" +
                "content='" + content + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

