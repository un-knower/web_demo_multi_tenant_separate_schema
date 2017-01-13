package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by user on 17/1/12.
 */
@Repository
public class StringRedisTemplateWrapper {

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

}
