package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

/**
 * Created by user on 17/1/12.
 */
public interface StringRedisService {

    public void set(String key, String value);

    public String get(String key);

    public void delete(String key);
}
