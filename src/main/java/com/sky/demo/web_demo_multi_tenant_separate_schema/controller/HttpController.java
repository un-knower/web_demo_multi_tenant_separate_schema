package com.sky.demo.web_demo_multi_tenant_separate_schema.controller;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 16/10/26.
 */
@RequestMapping("/http")
@Controller
public class HttpController {

    private static final Logger logger = LoggerFactory.getLogger(HttpController.class);


    @Resource
    private TenantService tenantService;

    @Resource
    private RestTemplate restTemplate;


    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RetData<TenantForm> query(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        RetData<TenantForm> result = null;
        try {
            TenantForm TenantForm = tenantService.query(id);

            result = RetUtil.buildSuccessRet(TenantForm);
        } catch (Exception e) {
            logger.error("query log error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/queryList") //, method = RequestMethod.POST PUT
    @ResponseBody
    public RetData<Pager<TenantForm>> queryList(@RequestBody TenantQueryRequest queryRequest, HttpServletRequest request, HttpServletResponse response) {

        RetData<Pager<TenantForm>> result = null;
        try {
            Pager<TenantForm> ret  = tenantService.queryList(queryRequest);
            result = RetUtil.buildSuccessRet(ret);
        } catch (Exception e) {
            logger.error("query log error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public RetData<String> delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isDelete = tenantService.delete(id);
            Preconditions.checkArgument(isDelete, "delete error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("delete log error",e);
            result = RetUtil.buildErrorRet(RetStatus.DELETE_ERROR);
        }
        return result;
    }



    @RequestMapping(value = "/redirect/query/{id}", method = RequestMethod.GET)
    public void redirectQuery(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        String url =  "http://127.0.0.1:8080/web_demo" + "/http/query/" + id;
        logger.info("redirect query, url: {}", url);

        response.setStatus(HttpStatus.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", url);
    }

    @RequestMapping(value = "/redirect/queryList") // method = RequestMethod.POST PUT
    public void redirectQueryList(@RequestBody TenantQueryRequest queryRequest, HttpServletRequest request, HttpServletResponse response) {
        String url = "http://127.0.0.1:8080/web_demo" + "/http/queryList";
        logger.info("redirect query, url: {}", url);

        response.setStatus(HttpStatus.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", url);
    }

    @RequestMapping(value = "/redirect/delete/{id}", method = RequestMethod.DELETE)
    public void redirectDelete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        String url = "http://127.0.0.1:8080/web_demo" + "/http/delete/" + id;
        logger.info("redirect delete, url: {}", url);

        response.setStatus(HttpStatus.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", url);
    }



//    @RequestMapping(value = "/rest/query/{id}", method = RequestMethod.GET)
//    public void restQuery(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
//        String url =  "http://127.0.0.1:8080/web_demo" + "/http/query/" + id;
//        logger.info("redirect query, url: {}", url);
//
//        response.setStatus(HttpStatus.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", url);
//    }
}
