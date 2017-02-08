package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

/**
 * Created by user on 17/2/8.
 */
public interface ValueRedisService<T> {

    public <T> void set(final String key, final T value);

    public <T> T get(String key);

    public void delete(String key);
}
