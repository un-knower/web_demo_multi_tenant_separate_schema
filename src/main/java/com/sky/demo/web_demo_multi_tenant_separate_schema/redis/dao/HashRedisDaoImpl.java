package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/1/14.
 */
@Repository
public class HashRedisDaoImpl<T> extends AbstractRedisDao implements HashRedisDao<T> {


    @Override
    public <T> boolean addHash(String key, String field, T value) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);
        return true;
    }

    //TODO
    @Override
    public <T> boolean addHash(String key, String field, T value, long timeout) {
        //TODO
        boolean result = true;
//                redisTemplate.execute(new RedisCallback<Boolean>() {
//            @Override
//            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//                //setNX 不存在则增加
//                Boolean result = connection.hSetNX(key.getBytes(), field.getBytes(), String.valueOf(value).getBytes()).booleanValue();
//
//                if (result == true &&  timeout > 0) {
//                    connection.expire(key.getBytes(), timeout);
//                }
//                return result;
//
//            }
//        });
        return result;
    }

    @Override
    public <T> boolean addHash(String key, Map<String, T> filedValueMap) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, filedValueMap);
        return true;
    }

    @Override
    public <T> boolean addHash(String key, Map<String, T> filedValueMap, long timeout) {
        //TODO
        return false;
    }

    @Override
    public <T> T getHashField(String key, String field) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        T result = hashOperations.get(key, field);

//        Object result = redisTemplate.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                return connection.hGet(key.getBytes(), field.getBytes());
//            }
//        });
        return result;
    }

    @Override
    public boolean deleteHashField(String key, List<String> field) {
        redisTemplate.opsForHash().delete(key, field.toArray());
        return true;
    }
}
