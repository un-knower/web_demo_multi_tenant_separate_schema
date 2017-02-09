package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 17/2/8.
 */
@Repository
public class SetRedisDaoImpl<T> extends AbstractRedisDao implements SetRedisDao<T> {

    @Override
    public <T> long addSet(String key, T value) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.add(key, value);
    }

    @Override
    public <T> long addSet(String key, T value, long timeout) {

        Long result = (Long)redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long result = connection.sAdd(key.getBytes(), String.valueOf(value).getBytes());
                if (result == 0)
                    return result;
                if (timeout > 0)
                    connection.expire(key.getBytes(), timeout);
                return result;
            }
        });
        return result;
    }

    @Override
    public <T> long addSet(String key, List<T> values) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.add(key, (T[]) values.toArray());
    }

    @Override
    public <T> long addSet(String key, List<T> values, long timeout) {

        //TODO
        return 0;
    }

    @Override
    public <T> Set<T> getSetMembers(String key) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.members(key);
    }

    @Override
    public <T> boolean isContain(String key, T value) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.isMember(key, value);
    }

    @Override
    public <T1> long remove(String key, T1 value) {
        return 0;
    }

    @Override
    public <T> long remove(String key, List<T> values) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.remove(key, values.toArray());
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

}
