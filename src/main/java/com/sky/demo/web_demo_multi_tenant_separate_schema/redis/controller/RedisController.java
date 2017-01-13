package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.ListRedisDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao.ValueRedisDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 17/1/12.
 */
@RequestMapping("/redis")
@Controller
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Resource
    private ListRedisDao listRedisDao;


    @RequestMapping("/push")
    @ResponseBody
    public RetData<String> push(@RequestParam String key, @RequestParam Object value, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            listRedisDao.push(key, value);
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("string redis set error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/pop")
    @ResponseBody
    public RetData<Object> pop(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) {
        RetData<Object> ret = null;

        try {
            Object result = listRedisDao.pop(key);
            ret = RetUtil.buildSuccessRet(result);
        } catch (Exception e) {
            logger.error("string redis set error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/enquene")
    @ResponseBody
    public RetData<String> enquene(@RequestParam String key, @RequestParam Object value, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            listRedisDao.enquene(key, value);
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("string redis set error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/dequene")
    @ResponseBody
    public RetData<Object> dequene(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) {
        RetData<Object> ret = null;

        try {
            Object result = listRedisDao.dequene(key);
            ret = RetUtil.buildSuccessRet(result);
        } catch (Exception e) {
            logger.error("string redis set error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }


}
