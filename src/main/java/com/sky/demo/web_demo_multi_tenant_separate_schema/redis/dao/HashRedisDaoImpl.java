package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/1/14.
 */
@Repository
public class HashRedisDaoImpl extends AbstractRedisDao implements HashRedisDao {


    @Override
    public boolean addHash(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
        return true;
    }

    //TODO
    @Override
    public boolean addHash(String key, String field, Object value, long timeout) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                //setNX 不存在则增加
                Boolean result = connection.hSetNX(key.getBytes(), field.getBytes(), value.toString().getBytes()).booleanValue();

                if (result == true &&  timeout > 0) {
                    connection.expire(key.getBytes(), timeout);
                }
                return result;

            }
        });
        return result;
    }

    @Override
    public boolean addHash(String key, Map<String, Object> filedValueMap) {
        redisTemplate.opsForHash().putAll(key, filedValueMap);
        return true;
    }

    @Override
    public boolean addHash(String key, Map<String, Object> filedValueMap, long timeout) {
        return false;
    }

    @Override
    public Object getHashField(String key, String field) {
        Object result = redisTemplate.opsForHash().get(key, field);

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
