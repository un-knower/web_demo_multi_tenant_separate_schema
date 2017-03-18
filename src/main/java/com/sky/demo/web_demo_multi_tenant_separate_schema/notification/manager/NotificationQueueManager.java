package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.manager;

/**
 * Created by user on 17/3/18.
 */
public interface NotificationQueueManager {

    public void putInfoToNotificationQueue(String value);

    public String getInfoFromNotificationQueue();

}
