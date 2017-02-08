package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

/**
 * Created by user on 17/1/13.
 */
public interface ValueRedisDao<T> {

    public <T> boolean set(final String key, final T value);
    public <T> boolean set(final String key, final T value, Class<?> clazz);

    public <T> T get(final String key);

    public boolean delete(final String key);
}
