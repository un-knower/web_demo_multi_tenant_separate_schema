package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.StringRedisTemplateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by user on 17/1/12.
 */
@Service
public class StringRedisServiceImpl implements StringRedisService {

    private static final Logger logger = LoggerFactory.getLogger(StringRedisServiceImpl.class);

    @Resource
    private StringRedisTemplateWrapper stringRedisTemplateWrapper;

    @Override
    public void set(String key, String value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template set key : {}", key);
        stringRedisTemplateWrapper.set(key, value);
    }

    @Override
    public String get(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template get key : {}", key);
        return stringRedisTemplateWrapper.get(key);
    }

    @Override
    public void delete(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template delete key : {}", key);
        stringRedisTemplateWrapper.delete(key);
    }
}
