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
public class HashRedisServiceImpl implements HashRedisService {

    private static final Logger logger = LoggerFactory.getLogger(HashRedisServiceImpl.class);

    @Resource
    private HashRedisDao hashRedisDao;

    @Override
    public boolean addHash(String key, String field, Object value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash add(), key:{}", key);
        hashRedisDao.addHash(key, field, value);
        return true;
    }

    @Override
    public boolean addHash(String key, Map<String, Object> filedValueMap) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash add(), key:{}", key);
        hashRedisDao.addHash(key, filedValueMap);
        return true;
    }

    @Override
    public Object getHashField(String key, String field) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash getHashField(), key:{}, field:{}", key, field);
        return hashRedisDao.getHashField(key, field);
    }

    @Override
    public boolean deleteHashField(String key, List<String> field) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("redis hash deleteHashField(), key:{}, field:{}", key, field);
        hashRedisDao.deleteHashField(key, field);
        return true;
    }
}
