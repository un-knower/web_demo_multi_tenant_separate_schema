package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.ListRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 17/1/14.
 */
@Service
public class ListRedisServiceImpl<T> implements ListRedisService<T> {

    private static final Logger logger = LoggerFactory.getLogger(ListRedisServiceImpl.class);

    @Resource
    private ListRedisDao listRedisDao;


    //for stack
    @Override
    public <T> void push(String key, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list push(), key:{}", key);
        listRedisDao.push(key, value);
    }

    @Override
    public <T> T pop(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list pop(), key:{}", key);
        return (T)listRedisDao.pop(key);
    }

    //for queue
    @Override
    public <T> void enquene(String key, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list enquene(), key:{}", key);
        listRedisDao.enquene(key, value);
    }

    @Override
    public <T> T dequene(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list dequene(), key:{}", key);
        return (T)listRedisDao.dequene(key);
    }



    @Override
    public long size(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list size(), key:{}", key);
        return listRedisDao.size(key);
    }

    @Override
    public <T> List<T> range(String key, long start, int end) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list range(), key:{}", key);
        return listRedisDao.range(key, start, end);
    }

    @Override
    public <T> void remove(String key, int i, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list remove(), key:{}", key);
        listRedisDao.remove(key, i, value);
    }

    @Override
    public <T> T index(String key, long index) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list index(), key:{}", key);
        return (T)listRedisDao.index(key, index);
    }

    @Override
    public <T> void set(String key, long index, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list set(), key:{}", key);
        listRedisDao.set(key, index, value);
    }

    @Override
    public void trim(String key, long start, long end) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list trim(), key:{}", key);
        listRedisDao.trim(key, start, end);
    }
}
