package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;


import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 17/1/13.
 */
@Repository
public class ValueRedisDaoImpl<T> extends AbstractRedisDao implements ValueRedisDao<T> {


    @Override
    public <T> boolean set(String key, T value) {
//        redisTemplate.boundValueOps(key).set(value);
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        return true;
    }

    @Override
    public <T> boolean set(String key, T value, Class<?> clazz) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(key, (T)clazz.cast(value));
//        redisTemplate.opsForValue().set(key, clazz.cast(value));
        return true;
    }

    @Override
    public <T> T get(String key) {
//        return redisTemplate.boundValueOps(key).get();
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();

        return valueOperations.get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }


}
