package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;


import org.springframework.stereotype.Repository;

/**
 * Created by user on 17/1/13.
 */
@Repository
public class ValueRedisDaoImpl extends AbstractRedisDao implements ValueRedisDao {


    @Override
    public boolean set(String key, Object value) {
//        redisTemplate.boundValueOps(key).set(value);
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public boolean set(String key, Object value, Class clazz) {
        redisTemplate.opsForValue().set(key, clazz.cast(value));
        return true;
    }

    @Override
    public Object get(String key) {
//        return redisTemplate.boundValueOps(key).get();
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean delete(String key) {
        redisTemplate.delete(key);
        return true;
    }

}
