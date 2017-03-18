package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.notice;

import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;

/**
 * Created by user on 17/3/18.
 */
public class EndpointNotificationTask extends NotificationTask {

    protected EndpointNotificationTask(String incidentId, boolean hasEvidence, TenantForm tenantForm) {
        super(incidentId, hasEvidence, tenantForm);
    }

    @Override
    protected void initNotificationTask() throws Exception {

    }
}
