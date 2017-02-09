package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 17/2/9.
 */
public class ZSetRedisDaoImpl<T> extends AbstractRedisDao implements ZSetRedisDao<T> {


    @Override
    public <T> boolean addZSet(String key, T value, double score) {
        ZSetOperations<String, T> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.add(key, value, score);
    }

    @Override
    public <T> Set<T> getZSetMembers(String key) {
        return null;
    }

    @Override
    public <T> boolean isContain(String key, T value) {
        return false;
    }

    @Override
    public <T> long remove(String key, T value) {
        return 0;
    }

    @Override
    public <T> long remove(String key, List<T> values) {
        return 0;
    }

    @Override
    public void delete(String key) {

    }
}
