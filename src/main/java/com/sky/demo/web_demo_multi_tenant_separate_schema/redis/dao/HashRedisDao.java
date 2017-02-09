package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/1/14.
 */
public interface HashRedisDao<T> {

    public <T> boolean addHash(final String key, final String field, final T value);
    public <T> boolean addHash(final String key, final String field, final T value, final long timeout);

    public <T> boolean addHash(final String key, final Map<String, T> filedValueMap);
    public <T> boolean addHash(final String key, final Map<String, T> filedValueMap, final long timeout);


    public <T> T getHashField(final String key, final String field);

    public boolean deleteHashField(final String key, final List<String> field);

    public void delete(final String key);
}
