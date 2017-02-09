package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 17/2/9.
 */
public interface ZSetRedisDao<T> {

    public <T> boolean addZSet(final String key, final T value, final double score);

    public <T> Set<T> getZSetMembers(final String key);

    public <T> boolean isContain(final String key, final T value);

    public <T> long remove(final String key, final T value);
    public <T> long remove(final String key, final List<T> values);

    public void delete(final String key);

}
