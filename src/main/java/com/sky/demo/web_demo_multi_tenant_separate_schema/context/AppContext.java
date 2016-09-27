package com.sky.demo.web_demo_multi_tenant_separate_schema.context;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantUserForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantUserService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SpringUtil;

/**
 * Created by user on 16/9/20.
 */
public class AppContext implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AppContext.class);

    private static ThreadLocal<TenantForm> tenantThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<TenantUserForm> tenantUserThreadLocal = new ThreadLocal<>();


    public static TenantForm getTenant() {
        return tenantThreadLocal.get();
    }

    public static void setTenant(TenantForm tenant) {
        tenantThreadLocal.set(tenant);

        if (tenant != null) {
            MDC.put("tenant", tenant.getName());
        }
    }

    public static void releaseTenant() {
        tenantThreadLocal.remove();
    }

    public static TenantUserForm getTenantUser() {
        return tenantUserThreadLocal.get();
    }

    public static void setTenantUser(TenantUserForm tenantUser) {
        tenantUserThreadLocal.set(tenantUser);
    }

    public static void releaseTenantUser() {
        tenantUserThreadLocal.remove();
    }




    /**
     * 初始化tenant，JdbcTemplate信息
     * @param userName
     */
    public static void initAppResourcesByUserName(String userName) {
        Preconditions.checkState(StringUtils.isNotBlank(userName), "userName is blank!!");
        logger.debug("init App Resources user name = " + userName);

        try {
            TenantUserService tenantUserService = SpringUtil.getCtx().getBean(TenantUserService.class);
            TenantUserForm tenantUser = tenantUserService.queryByUserName(userName);
            Preconditions.checkNotNull(tenantUser, "tenant user is null!");

            setTenantUser(tenantUser);
            setTenant(tenantUser.getTenant());

        } catch (BeansException e) {
            logger.error("init app resources by userName error", e);
        }

        try {
            logger.debug("   ====> init AppResources by userName: " + userName + ", tenantUser=" + getTenantUser().getUserName());
        } catch (Exception e) {
            logger.error("print app resource error");
        }

    }

    /**
     * 初始化tenant，JdbcTemplate信息
     * @param token
     */
    public static void initAppResourcesByToken(String token) {
        Preconditions.checkState(StringUtils.isNotBlank(token), "token is blank!!");
        logger.debug("init App Resources token = " + token);

        try {
            TenantService tenantService = SpringUtil.getCtx().getBean(TenantService.class);
            TenantForm tenant = tenantService.queryByToken(token);
            Preconditions.checkNotNull(tenant, "tenant is null!");

            setTenant(tenant);
        } catch (BeansException e) {
            logger.error("init app resource by token error", e);
        }

        try {
            logger.debug("   ====> init AppResources by token: " + token);
        } catch (Exception e) {
            logger.error("print app resource error");
        }
    }

    /**
     * 释放AppContext中资源
     */
    public static void releaseAppResources() {
        releaseTenant();
        releaseTenantUser();
    }
}
