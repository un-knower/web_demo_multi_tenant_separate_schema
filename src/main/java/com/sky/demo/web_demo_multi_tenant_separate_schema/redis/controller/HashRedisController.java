package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dto.HashRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.redis.service.HashRedisService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 17/1/14.
 */
@RequestMapping("/redis/hash")
@Controller
public class HashRedisController {

    private static final Logger logger = LoggerFactory.getLogger(HashRedisController.class);

    @Resource
    private HashRedisService hashRedisService;

    @RequestMapping("/add")
    @ResponseBody
    public RetData<String> add(@RequestBody HashRequest hashRequest, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            hashRedisService.addHash(hashRequest.getKey(), hashRequest.getField(), hashRequest.getValue());
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("redis hash add() error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/addMap")
    @ResponseBody
    public RetData<String> addMap(@RequestBody HashRequest hashRequest, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            hashRedisService.addHash(hashRequest.getKey(), hashRequest.getField(), hashRequest.getFieldValueMap());
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("redis hash addMap() error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/getHashField")
    @ResponseBody
    public RetData<Object> getHashField(@RequestBody HashRequest hashRequest, HttpServletRequest request, HttpServletResponse response) {
        RetData<Object> ret = null;

        try {
            Object result = hashRedisService.getHashField(hashRequest.getKey(), hashRequest.getField());
            ret = RetUtil.buildSuccessRet(result);
        } catch (Exception e) {
            logger.error("redis hash getHashField() error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RetData<String> delete(@RequestBody HashRequest hashRequest, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> ret = null;

        try {
            hashRedisService.deleteHashField(hashRequest.getKey(), hashRequest.getToDelFields());
            ret = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("redis hash delete() error", e);
            ret = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return ret;
    }
}
