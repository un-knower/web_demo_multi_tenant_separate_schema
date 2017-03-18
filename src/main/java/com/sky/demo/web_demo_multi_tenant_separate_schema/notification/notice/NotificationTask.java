package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.notice;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 17/3/18.
 */
public abstract class NotificationTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NotificationTask.class);


    private Lock lock = new ReentrantLock();

    private String incidentId;
    private boolean hasEvidence;
    private TenantForm tenant;

    protected NotificationTask(String incidentId, boolean hasEvidence, TenantForm tenantForm) {
        this.incidentId = incidentId;
        this.hasEvidence = hasEvidence;
        this.tenant = tenantForm;
    }

    /**
     * init notification
     * @throws Exception
     */
    protected abstract void initNotificationTask() throws Exception;


    @Override
    public void run() {

        try {
            lock.lock();
            DBContext.setTenant(tenant);

            initNotificationTask();

            //send notification by mail..



        } catch (Exception e) {
            logger.error("send notification error", e);
        } finally {

            DBContext.releaseResources();
            lock.unlock();
        }


    }
}
