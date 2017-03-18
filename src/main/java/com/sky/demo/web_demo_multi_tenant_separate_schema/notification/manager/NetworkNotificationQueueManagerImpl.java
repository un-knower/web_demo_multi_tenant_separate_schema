package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.manager;

import com.sky.demo.web_demo_multi_tenant_separate_schema.notification.queue.NetworkNotificationQueueServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 17/3/18.
 */
@Component
public class NetworkNotificationQueueManagerImpl implements NotificationQueueManager {

    private static final Logger logger = LoggerFactory.getLogger(NetworkNotificationQueueManagerImpl.class);

    private Lock networkNotificationLock = new ReentrantLock();
    private Condition condition = networkNotificationLock.newCondition();


    @Resource
    private NetworkNotificationQueueServiceImpl notificationQueueService;

    @Override
    public void putInfoToNotificationQueue(String value) {

        try {
            networkNotificationLock.lock();
            notificationQueueService.putToQueue(value);

            condition.signalAll();      // notify

        } catch (Exception e) {
            logger.error("put network notification to queue error", e);
        } finally {
            networkNotificationLock.unlock();
        }

    }

    @Override
    public String getInfoFromNotificationQueue() {

        String result = null;
        try {
            networkNotificationLock.lock();
            result = notificationQueueService.getFromQueue();

            if (StringUtils.isBlank(result)) {
                condition.await();          //wait  释放锁
            }

        } catch (Exception e) {
            logger.error("get network notification from queue error", e);
        } finally {
            networkNotificationLock.unlock();
        }

        return result;
    }
}
