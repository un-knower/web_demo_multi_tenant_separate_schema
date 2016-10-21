package com.sky.demo.web_demo_multi_tenant_separate_schema.task;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by rg on 8/2/15.
 */
public abstract class DefaultTask {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTask.class);

    protected String taskName;
    protected Logger taskLogger;

    protected abstract String getTaskName();
    protected abstract Logger getTaskLogger();

    protected abstract void doTask() throws Exception;

    protected final void doExecute() {
        taskLogger = getTaskLogger();
        taskLogger.info("**** schedule task **** | taskName={} begin...", getTaskName());
        run();
        taskLogger.info("**** schedule task **** | taskName={} end...", getTaskName());

    }

    public final void run() {
        long startTime = System.currentTimeMillis();

        try {
            String traceId = UUID.randomUUID().toString().replaceAll("-", "");
            MDC.put("traceId", traceId);

            doTask();
        } catch (Exception e) {
            logger.error("execute task error, taskName={}", taskName, e);

        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("task execute time : {}", endTime - startTime);

            DBContext.releaseResources();
            MDC.remove("traceId");
        }

    }

}
