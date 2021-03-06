package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.HashRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/1/14.
 */
@Service
public class HashRedisServiceImpl<T> implements HashRedisService<T> {

    private static final Logger logger = LoggerFactory.getLogger(HashRedisServiceImpl.class);

    @Resource
    private HashRedisDao hashRedisDao;

    @Override
    public <T> boolean addHash(String key, String field, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash add(), key:{}", key);
        return hashRedisDao.addHash(key, field, value);
    }

    @Override
    public <T> boolean addHash(String key, Map<String, T> filedValueMap) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash add(), key:{}", key);
        return hashRedisDao.addHash(key, filedValueMap);
    }

    @Override
    public <T> T getHashField(String key, String field) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash getHashField(), key:{}, field:{}", key, field);
        return (T)hashRedisDao.getHashField(key, field);
    }

    @Override
    public boolean deleteHashField(String key, List<String> field) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash deleteHashField(), key:{}, field:{}", key, field);
        return hashRedisDao.deleteHashField(key, field);
    }
}
