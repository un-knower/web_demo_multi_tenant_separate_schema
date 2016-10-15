package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant;

import java.io.Serializable;
import java.util.UUID;

import com.google.common.base.Objects;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Tenant;

/**
 * Created by user on 16/9/18.
 */
public class TenantForm implements Serializable {

    private static final long serialVersionUID = 5802983685287791418L;
    private int id;
    private String name;
    private String clientId;
    private String deviceId;
    private String deviceToken;
    private String schemaName;
    private String createTime;
    private Tenant.Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Tenant.Status getStatus() {
        return status;
    }

    public void setStatus(Tenant.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("clientId", clientId)
                .add("deviceId", deviceId)
                .add("deviceToken", deviceToken)
                .add("schemaName", schemaName)
                .add("createTime", createTime)
                .add("status", status)
                .toString();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5 ; ++i) {
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
