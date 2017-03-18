package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.dispatcher;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17/3/18.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EndpointNotificationDispatcher extends BaseNotificationDispatcher {



    @Override
    protected boolean preExecute() {
        return false;
    }

    @Override
    protected void doExecute() {

    }

    @Override
    protected void postExecute() {

    }
}
