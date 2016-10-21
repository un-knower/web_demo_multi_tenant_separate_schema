package com.sky.demo.web_demo_multi_tenant_separate_schema.task;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SpringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.List;
import java.util.UUID;

/**
 * Created by user on 16/10/21.
 */
public abstract class DefaultLoopTenantTask extends DefaultTask {

    private static final Logger logger = LoggerFactory.getLogger(DefaultLoopTenantTask.class);

    protected boolean hasException;
    protected String errMsg;

    public boolean isHasException() {
        return hasException;
    }

    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 循环Tenant前的处理，有需要时重载此方法
     * @return
     * true时继续此task，false时终止此task
     */
    protected boolean beforeLoop() {
        return true;
    }

    /**
     * 循环Tenant时需要跳过，有需要时重载此方法
     * @return
     * true时跳过此task，false时执行此task
     */
    protected boolean isSkipTenant(TenantForm tenant) {
        return false;
    }

    /**
     * 循环Tenant后的处理，有需要时重载此方法
     */
    protected void afterLoop() {

    }

    protected abstract void doTenantTask(TenantForm tenant) throws Exception;


    @Override
    protected void doTask() throws Exception {
        if (!beforeLoop()) {
            logger.warn("**** schedule task **** {} before loop exit", taskName);
            return;
        }

        TenantService tenantService = SpringUtil.getCtx().getBean(TenantService.class);
        List<TenantForm> tenants = tenantService.queryList(Lists.newArrayList());
        if (CollectionUtils.isEmpty(tenants)) {
            logger.warn("**** schedule task **** {} find no tenants exit", taskName);
            return;
        }

        for (TenantForm tenant : tenants) {

            try {
                String traceId = UUID.randomUUID().toString().replaceAll("-", "");
                MDC.put("traceId", traceId);
                DBContext.releaseResources();
                DBContext.initResourcesByDbKey(tenant.getSchemaName());

                if (isSkipTenant(tenant)) {
                    continue;
                }

                doTenantTask(tenant);
            } catch (Exception e) {
                logger.error("**** schedule task **** {0} on tenant {1} fail", taskName, tenant.getSchemaName(), e);

                hasException = true;
                errMsg = "**** schedule task **** " + taskName +" on tenant " + tenant.getSchemaName() + " fail, " + e.getMessage();
            } finally {
                MDC.remove("traceId");
                DBContext.releaseResources();
            }
        }

        afterLoop();
    }
}
