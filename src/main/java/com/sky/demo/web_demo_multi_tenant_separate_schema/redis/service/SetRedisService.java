package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 17/2/9.
 */
public interface SetRedisService<T> {


    public <T> long addSet(final String key, final T value);
    public <T> long addSet(final String key, final T value, final long timeout);

    public <T> long addSet(final String key, final List<T> values);
    public <T> long addSet(final String key, final List<T> values, final long timeout);

    public <T> Set<T> getSetMembers(final String key);

    public <T> boolean isContain(final String key, final T value);

    public <T> long remove(final String key, final T value);
    public <T> long remove(final String key, final List<T> values);

    public void delete(final String key);
}
