package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.base;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by user on 17/1/12.
 */
@Component
public class BaseStringRedisTemplateWrapper {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public void set(String key, String value) {
        stringRedisTemplate.boundValueOps(key).set(value);
    }

    public String get(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }


    /**
     * 返回BoundList, 可扩展为Queue，Stack
     * @param key
     * @return
     */
    public BoundListOperations<String, String> getBoundList(String key) {
        return stringRedisTemplate.boundListOps(key);
    }

}
