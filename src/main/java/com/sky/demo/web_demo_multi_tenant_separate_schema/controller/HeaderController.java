package com.sky.demo.web_demo_multi_tenant_separate_schema.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Account;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AccountService;
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
 * Created by user on 16/9/30.
 */
@RequestMapping("/v1/header")
@Controller
public class HeaderController {

    private static final Logger logger = LoggerFactory.getLogger(HeaderController.class);

    @Resource
    private AccountService accountService;

    @RequestMapping("/getHeader")
    @ResponseBody
    public RetData<String> getHeader(HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            String auth = request.getHeader("Authorization");

            result = RetUtil.buildSuccessRet(auth);
        } catch (Exception e) {
            logger.error("query error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryAccount")
    @ResponseBody
    public RetData<Account> queryAccount(@RequestParam int id, HttpServletRequest request, HttpServletResponse response) {
        RetData<Account> result = null;
        try {
            Account account = accountService.query(id);

            result = RetUtil.buildSuccessRet(account);
        } catch (Exception e) {
            logger.error("query error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }
}
