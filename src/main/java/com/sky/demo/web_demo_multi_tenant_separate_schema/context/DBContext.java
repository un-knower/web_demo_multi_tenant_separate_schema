package com.sky.demo.web_demo_multi_tenant_separate_schema.context;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantUserForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantUserService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;

/**
 * Created by user on 16/10/12.
 */
public class DBContext {

    private static final Logger logger = LoggerFactory.getLogger(DBContext.class);

    private static final ThreadLocal<String> dbKeyThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<TenantForm> tenantThreadLocal = new ThreadLocal<>();


    public static void setDbKey(String dbKey) {
        dbKeyThreadLocal.set(dbKey);
    }

    public static String getDbKey() {
        return dbKeyThreadLocal.get();
    }

    public static void removeDbKey() {
        dbKeyThreadLocal.remove();
    }

    public static void setTenant(TenantForm tenant) {
        tenantThreadLocal.set(tenant);

        if (tenant != null) {
            MDC.put("tenant", tenant.getName());
        }
    }

    public static TenantForm getTenant() {
        return tenantThreadLocal.get();
    }

    public static void removeTenant() {
        tenantThreadLocal.remove();
    }



    /**
     * 初始化tenant信息
     * @param userName
     */
    public static void initResourcesByUserName(String userName) {
        Preconditions.checkState(StringUtils.isNotBlank(userName), "userName is blank!!");
        logger.debug("   ====> init Resources user name = " + userName);

        try {
            TenantUserService tenantUserService = SpringUtil.getCtx().getBean(TenantUserService.class);
            TenantUserForm tenantUser = tenantUserService.queryByUserName(userName);
            Preconditions.checkNotNull(tenantUser, "tenant user is null!");

            setDbKey(tenantUser.getTenant().getSchemaName());
            setTenant(tenantUser.getTenant());
        } catch (BeansException e) {
            logger.error("init resources by userName error", e);
        }

        logger.debug("   ====> init Resources by userName: " + userName + ", tenant = " + getTenant().getSchemaName());

    }

    /**
     * 初始化tenant信息
     * @param dbKey
     */
    public static void initResourcesByDbKey(String dbKey) {
        Preconditions.checkState(StringUtils.isNotBlank(dbKey), "dbKey is blank!!");
        logger.debug("   ====> init Resources user name = " + dbKey);

        try {
            TenantService tenantService = SpringUtil.getCtx().getBean(TenantService.class);
            TenantForm tenant = tenantService.queryBySchemaName(dbKey);
            Preconditions.checkNotNull(tenant, "tenant user is null!");

            setDbKey(dbKey);
            setTenant(tenant);
        } catch (BeansException e) {
            logger.error("init resources by dbKey error", e);
        }

        logger.debug("   ====> init Resources by dbKey: " + dbKey + ", tenant = " + getTenant().getSchemaName());

    }

    /**
     * 初始化tenant信息
     * @param token
     */
    public static void initResourcesByToken(String token) {
        Preconditions.checkState(StringUtils.isNotBlank(token), "token is blank!!");
        logger.debug("   ====> init Resources token = " + token);

        try {
            TenantService tenantService = SpringUtil.getCtx().getBean(TenantService.class);
            TenantForm tenant = tenantService.queryByDeviceToken(token);
            Preconditions.checkNotNull(tenant, "tenant is null!");

            setDbKey(tenant.getSchemaName());
            setTenant(tenant);
        } catch (BeansException e) {
            logger.error("init resource by token error", e);
        }

        logger.debug("   ====> init Resources by token: " + token + ", tenant = " + getTenant().getSchemaName());

    }

    /**
     * 释放Context中资源
     */
    public static void releaseResources() {
        logger.debug("   ====> release resources ");

        removeDbKey();
        removeTenant();
        MDC.remove("tenant");
    }

}
