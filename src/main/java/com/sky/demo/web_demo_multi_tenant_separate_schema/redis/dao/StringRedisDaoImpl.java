package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by user on 17/1/12.
 */
@Repository
public class StringRedisDaoImpl<K, V> extends AbstractRedisDao<K, V> {


    public void set(String key, String value) {
        redisTemplate.boundValueOps(key).set(value);
    }
}
