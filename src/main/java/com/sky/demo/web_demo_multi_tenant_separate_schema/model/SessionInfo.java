package com.sky.demo.web_demo_multi_tenant_separate_schema.model;

import java.io.Serializable;

import com.google.common.base.Objects;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantUserForm;

/**
 * Created by user on 16/9/24.
 */
public class SessionInfo implements Serializable {

    private TenantUserForm tenantUser;

    public TenantUserForm getTenantUser() {
        return tenantUser;
    }

    public void setTenantUser(TenantUserForm tenantUser) {
        this.tenantUser = tenantUser;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("tenantUser", tenantUser)
                .toString();
    }
}
