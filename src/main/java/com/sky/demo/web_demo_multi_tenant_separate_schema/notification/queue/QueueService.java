package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.queue;


/**
 * Created by user on 17/3/18.
 */
public interface QueueService {

    public void putToQueue(String value);

    public String getFromQueue();
}
