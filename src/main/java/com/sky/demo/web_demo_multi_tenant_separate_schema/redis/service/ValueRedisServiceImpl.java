package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.ValueRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by user on 17/2/8.
 */
@Service
public class ValueRedisServiceImpl<T> implements ValueRedisService<T> {

    private static final Logger logger = LoggerFactory.getLogger(ValueRedisServiceImpl.class);

    @Resource
    private ValueRedisDao valueRedisDao;


    @Override
    public <T> void set(String key, T value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template set(), key : {}", key);
        valueRedisDao.set(key, value);

    }

    @Override
    public <T> T get(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template get(), key : {}", key);
        T result = (T)valueRedisDao.get(key);
        return result;
    }

    @Override
    public void delete(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template delete(), key : {}", key);
        valueRedisDao.delete(key);
    }
}
