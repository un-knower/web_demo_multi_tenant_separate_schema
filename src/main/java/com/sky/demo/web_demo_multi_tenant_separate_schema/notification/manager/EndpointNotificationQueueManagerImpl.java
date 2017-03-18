package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.manager;

import com.sky.demo.web_demo_multi_tenant_separate_schema.notification.queue.EndpointNotificationQueueServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by user on 17/3/18.
 */
@Component
public class EndpointNotificationQueueManagerImpl implements NotificationQueueManager {

    private static final Logger logger = LoggerFactory.getLogger(EndpointNotificationQueueManagerImpl.class);

    @Resource
    private EndpointNotificationQueueServiceImpl endpointNotificationQueueService;


    @Override
    public void putInfoToNotificationQueue(String value) {

    }

    @Override
    public String getInfoFromNotificationQueue() {
        return null;
    }
}
