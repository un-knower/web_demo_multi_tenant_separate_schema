package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 17/3/18.
 */
public class EndpointNotificationQueueServiceImpl extends BaseNotificationQueueService implements QueueService {

    private static final Logger logger = LoggerFactory.getLogger(EndpointNotificationQueueServiceImpl.class);

    private static final String ENDPOINT_NOTIFICATION_QUEUE_KEY = "ENDPOINT_NOTIFICATION_QUEUE_KEY";


    @Override
    public String getKey() {
        return ENDPOINT_NOTIFICATION_QUEUE_KEY;
    }


    @Override
    public void putToQueue(String value) {
        getQueueByKey().leftPush(value);

    }

    @Override
    public String getFromQueue() {
        String value = getQueueByKey().rightPop();

        return value;
    }
}
