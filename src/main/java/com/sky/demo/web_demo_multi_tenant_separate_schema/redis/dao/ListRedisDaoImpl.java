package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 17/1/13.
 */
@Repository
public class ListRedisDaoImpl extends AbstractRedisDao implements ListRedisDao {


    @Override
    public void push(final String key, final Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Object pop(final String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public void enquene(final String key, final Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Object dequene(final String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public long size(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public List<Object> range(String key, long start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public void remove(String key, int i, Object value) {
        redisTemplate.opsForList().remove(key, i, value);
    }

    @Override
    public Object index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void set(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public void trim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}
