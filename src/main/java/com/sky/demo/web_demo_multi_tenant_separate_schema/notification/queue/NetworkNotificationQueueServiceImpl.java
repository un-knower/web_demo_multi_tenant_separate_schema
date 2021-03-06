package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 17/3/18.
 */
@Service
public class NetworkNotificationQueueServiceImpl extends BaseNotificationQueueService implements QueueService {

    private static final Logger logger = LoggerFactory.getLogger(NetworkNotificationQueueServiceImpl.class);

    private static final String NETWORK_NOTIFICATION_QUEUE_KEY = "NETWORK_NOTIFICATION_QUEUE_KEY";

    @Override
    public String getKey() {
        return NETWORK_NOTIFICATION_QUEUE_KEY;
    }

    @Override
    public void putToQueue(String value) {
        logger.info("put to queue={}， value={}", getKey(), value);
        getQueueByKey().leftPush(value);
    }

    @Override
    public String getFromQueue() {
        String value = getQueueByKey().rightPop();
        logger.info("get from queue={}, value={}", getKey(), value);
        return value;
    }
}
