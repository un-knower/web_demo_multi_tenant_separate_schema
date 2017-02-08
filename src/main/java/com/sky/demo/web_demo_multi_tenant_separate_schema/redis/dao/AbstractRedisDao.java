package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Created by user on 17/1/12.
 */
public abstract class AbstractRedisDao<T> {

    @Resource
    protected RedisTemplate<String, T> redisTemplate;

}
