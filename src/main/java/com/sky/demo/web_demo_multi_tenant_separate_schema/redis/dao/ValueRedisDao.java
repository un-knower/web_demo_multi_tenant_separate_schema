package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

/**
 * Created by user on 17/1/13.
 */
public interface ValueRedisDao {

    public boolean set(final String key, final Object value);
    public boolean set(final String key, final Object value, Class<?> clazz);

    public Object get(final String key);

    public boolean delete(final String key);
}
