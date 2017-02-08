package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 17/1/13.
 */
@Repository
public class ListRedisDaoImpl<T> extends AbstractRedisDao implements ListRedisDao<T> {


    @Override
    public <T> void push(final String key, final T value) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        listOperations.leftPush(key, value);
    }

    @Override
    public <T> T pop(final String key) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        return listOperations.leftPop(key);
    }

    @Override
    public <T> void enquene(final String key, final T value) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        listOperations.leftPush(key, value);
    }

    @Override
    public <T> T dequene(final String key) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        return listOperations.rightPop(key);
    }

    @Override
    public long size(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public <T> List<T> range(String key, long start, int end) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        return listOperations.range(key, start, end);
    }

    @Override
    public <T> void remove(String key, int i, T value) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        listOperations.remove(key, i, value);
    }

    @Override
    public <T> T index(String key, long index) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        return listOperations.index(key, index);
    }

    @Override
    public <T> void set(String key, long index, T value) {
        ListOperations<String, T> listOperations =  redisTemplate.opsForList();
        listOperations.set(key, index, value);
    }

    @Override
    public void trim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}
