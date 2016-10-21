package com.sky.demo.web_demo_multi_tenant_separate_schema.task.tenant;

import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AnLogService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.task.DefaultLoopTenantTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by user on 16/10/21.
 */
@Component("tenantAnLogTask")
public class TenantAnLogTask extends DefaultLoopTenantTask {

    private static final Logger logger = LoggerFactory.getLogger(TenantAnLogTask.class);

    @Resource
    private AnLogService anLogService;



    @Override
    protected String getTaskName() {
        return "TaskAnLog";
    }

    @Override
    protected Logger getTaskLogger() {
        return logger;
    }


    @Override
    protected void doTenantTask(TenantForm tenant) throws Exception {



    }



}
