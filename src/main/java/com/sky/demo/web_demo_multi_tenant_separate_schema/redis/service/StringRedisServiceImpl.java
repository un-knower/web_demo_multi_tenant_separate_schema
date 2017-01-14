package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.StringRedisTemplateWrapper;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.ValueRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 17/1/12.
 */
@Service
public class StringRedisServiceImpl implements StringRedisService {

    private static final Logger logger = LoggerFactory.getLogger(StringRedisServiceImpl.class);

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    @Resource
    private StringRedisTemplateWrapper stringRedisTemplateWrapper;

    @Resource
    private ValueRedisDao valueRedisDao;


    @Override
    public void set(String key, String value) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template set key : {}", key);

        try {
            lock.lock();
            stringRedisTemplateWrapper.set(key, value);
//        valueRedisDao.set(key, value);

//            condition.signalAll();
        } catch (Exception e) {
            logger.error("redis set error", e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String get(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template get key : {}", key);
        String result = null;
        try {
            lock.lock();

            result = stringRedisTemplateWrapper.get(key);
//        String result = (String)valueRedisDao.get(key);

        } catch (Exception e) {
            logger.error("redis get error", e);
        } finally {
            lock.unlock();
        }

        return result;
    }

    @Override
    public void delete(String key) {
        Preconditions.checkNotNull(key, "key is null!");

        logger.info("string redis template delete key : {}", key);

        try {
            lock.lock();

            stringRedisTemplateWrapper.delete(key);
//        valueRedisDao.delete(key);
        } catch (Exception e) {
            logger.error("redis delete error", e);
        } finally {
            lock.unlock();
        }
    }
}
