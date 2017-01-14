package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/1/14.
 */
public interface HashRedisService {

    public boolean addHash(final String key, final String field, final Object value);

    public boolean addHash(final String key, final Map<String, Object> filedValueMap);

    public Object getHashField(final String key, final String field);

    public boolean deleteHashField(final String key, final List<String> field);
}
