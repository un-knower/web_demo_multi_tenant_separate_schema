package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.dispatcher;

import com.sky.demo.web_demo_multi_tenant_separate_schema.notification.manager.NetworkNotificationQueueManagerImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by user on 17/3/18.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NetworkNotificationDispatcher extends BaseNotificationDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(NetworkNotificationDispatcher.class);

    private String value;

    @Resource
    private NetworkNotificationQueueManagerImpl networkNotificationQueueManager;


    @Override
    protected boolean preExecute() {
        value = networkNotificationQueueManager.getInfoFromNotificationQueue();

        if (StringUtils.isBlank(value)) {
            return false;
        }
        return true;
    }

    @Override
    protected void doExecute() {
        //send notification mail
    }

    @Override
    protected void postExecute() {
        value = null;
    }
}
