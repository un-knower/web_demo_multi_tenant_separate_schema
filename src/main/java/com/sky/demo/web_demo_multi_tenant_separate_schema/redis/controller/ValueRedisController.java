package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service.ValueRedisService;
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
 * Created by user on 17/2/8.
 */
@RequestMapping("/redis/value")
@Controller
public class ValueRedisController {

    private static final Logger logger = LoggerFactory.getLogger(ValueRedisController.class);

    @Resource
    private ValueRedisService valueRedisService;

    @RequestMapping("/set")
    @ResponseBody
    public RetData<String> set(@RequestParam String key, @RequestParam String value, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            valueRedisService.set(key, value);
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("string redis set error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/get")
    @ResponseBody
    public RetData<String> get(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            String result = (String)valueRedisService.get(key);
            ret = RetUtil.buildSuccessRet(result);
        } catch (Exception e) {
            logger.error("string redis get error", e);
            ret = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return ret;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RetData<String> delete(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            valueRedisService.delete(key);
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("string redis delete error", e);
            ret = RetUtil.buildErrorRet(RetStatus.DELETE_ERROR);
        }
        return ret;
    }
}
