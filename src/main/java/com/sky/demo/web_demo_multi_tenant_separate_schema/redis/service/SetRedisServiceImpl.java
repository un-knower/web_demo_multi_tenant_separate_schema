package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.SetRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 17/2/9.
 */
@Service
public class SetRedisServiceImpl<T> implements SetRedisService<T> {

    private static final Logger logger = LoggerFactory.getLogger(SetRedisServiceImpl.class);

    @Resource
    private SetRedisDao setRedisDao;

    @Override
    public <T> long addSet(String key, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set add(), key:{}", key);
        return setRedisDao.addSet(key, value);
    }

    @Override
    public <T> long addSet(String key, T value, long timeout) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set add(), key:{}", key);
        return setRedisDao.addSet(key, value);
    }

    @Override
    public <T> long addSet(String key, List<T> values) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set add(), key:{}", key);
        return setRedisDao.addSet(key, values);
    }

    @Override
    public <T> long addSet(String key, List<T> values, long timeout) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set add(), key:{}", key);
        return setRedisDao.addSet(key, values);
    }

    @Override
    public <T> Set<T> getSetMembers(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set members(), key:{}", key);
        return setRedisDao.getSetMembers(key);
    }

    @Override
    public <T> boolean isContain(String key, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set isContain(), key:{}", key);
        return setRedisDao.isContain(key, value);
    }

    @Override
    public <T> long remove(String key, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set remove(), key:{}", key);
        return setRedisDao.remove(key, value);
    }

    @Override
    public <T> long remove(String key, List<T> values) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set remove(), key:{}", key);
        return setRedisDao.remove(key, values);
    }

    @Override
    public void delete(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis set delete(), key:{}", key);
        setRedisDao.delete(key);
    }
}
