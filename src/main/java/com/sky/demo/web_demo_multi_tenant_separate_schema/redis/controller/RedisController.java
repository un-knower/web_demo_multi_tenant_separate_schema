package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.ValueRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by user on 17/1/12.
 */
@RequestMapping("/redis")
@Controller
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

//    @Resource
//    private ValueRedisDao<Object> valueRedisDao;

}