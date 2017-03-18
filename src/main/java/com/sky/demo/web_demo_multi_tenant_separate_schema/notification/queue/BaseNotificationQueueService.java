package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.queue;

import com.sky.demo.web_demo_multi_tenant_separate_schema.notification.base.BaseStringRedisTemplateWrapper;
import org.springframework.data.redis.core.BoundListOperations;

import javax.annotation.Resource;

/**
 * Created by user on 17/3/18.
 */
public abstract class BaseNotificationQueueService {

    @Resource
    private BaseStringRedisTemplateWrapper redisTemplateWrapper;

    /**
     * 根据key获取队列
     * @return
     */
    public BoundListOperations<String, String> getQueueByKey() {
        return redisTemplateWrapper.getBoundList(this.getKey());
    }


    public abstract String getKey();
}
