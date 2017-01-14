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
public class ListRedisServiceImpl implements ListRedisService {

    private static final Logger logger = LoggerFactory.getLogger(ListRedisServiceImpl.class);

    @Resource
    private ListRedisDao listRedisDao;

    @Override
    public void push(String key, Object value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list push(), key:{}", key);
        listRedisDao.push(key, value);
    }

    @Override
    public Object pop(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list pop(), key:{}", key);
        return listRedisDao.pop(key);
    }

    @Override
    public void enquene(String key, Object value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list enquene(), key:{}", key);
        listRedisDao.enquene(key, value);
    }

    @Override
    public Object dequene(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list dequene(), key:{}", key);
        return dequene(key);
    }

    @Override
    public long size(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list size(), key:{}", key);
        return listRedisDao.size(key);
    }

    @Override
    public List<Object> range(String key, long start, int end) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list range(), key:{}", key);
        return listRedisDao.range(key, start, end);
    }

    @Override
    public void remove(String key, int i, Object value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list remove(), key:{}", key);
        listRedisDao.remove(key, i, value);
    }

    @Override
    public Object index(String key, long index) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis list index(), key:{}", key);
        return listRedisDao.index(key, index);
    }

    @Override
    public void set(String key, long index, Object value) {
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
