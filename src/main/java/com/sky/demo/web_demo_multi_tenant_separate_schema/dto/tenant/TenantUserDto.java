package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Objects;

/**
 * Created by user on 16/9/23.
 */
public class TenantUserDto implements Serializable {

    private static final long serialVersionUID = -1812985166632918032L;
    private int id;
    private int tenantId;
    private String userName;
    private Date createTime;
    private int status;

    private String tenantToken;
    private String tenantName;
    private String tenantDbName;
    private Date tenantCreateTime;
    private int tenantStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTenantToken() {
        return tenantToken;
    }

    public void setTenantToken(String tenantToken) {
        this.tenantToken = tenantToken;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantDbName() {
        return tenantDbName;
    }

    public void setTenantDbName(String tenantDbName) {
        this.tenantDbName = tenantDbName;
    }

    public Date getTenantCreateTime() {
        return tenantCreateTime;
    }

    public void setTenantCreateTime(Date tenantCreateTime) {
        this.tenantCreateTime = tenantCreateTime;
    }

    public int getTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(int tenantStatus) {
        this.tenantStatus = tenantStatus;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("tenantId", tenantId)
                .add("userName", userName)
                .add("createTime", createTime)
                .add("status", status)
                .add("tenantToken", tenantToken)
                .add("tenantName", tenantName)
                .add("tenantDbName", tenantDbName)
                .add("tenantCreateTime", tenantCreateTime)
                .add("tenantStatus", tenantStatus)
                .toString();
    }
}