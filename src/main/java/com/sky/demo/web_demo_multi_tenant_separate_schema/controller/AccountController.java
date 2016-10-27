package com.sky.demo.web_demo_multi_tenant_separate_schema.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.account.AccountQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Account;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AccountService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;

/**
 * Created by user on 16/9/19.
 */
@RequestMapping("/account")
@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountService accountService;


    @RequestMapping("/query/{id}")
    @ResponseBody
    public RetData<Account> query(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {   //, @RequestParam String userName
        RetData<Account> result = null;
        try {
            //AppContext.initResourcesByUserName(userName);

            Account Account = accountService.query(id);

            result = RetUtil.buildSuccessRet(Account);
        } catch (Exception e) {
            logger.error("query error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public RetData<Pager<Account>> queryList(@RequestBody AccountQueryRequest queryRequest, HttpServletRequest request, HttpServletResponse response) {

        RetData<Pager<Account>> result = null;
        try {
            Pager<Account> ret  = accountService.queryList(queryRequest);
            result = RetUtil.buildSuccessRet(ret);
        } catch (Exception e) {
            logger.error("query error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


    @RequestMapping("/add")
    @ResponseBody
    public RetData<String> add(@RequestBody Account record, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isAdd = accountService.add(record);
            Preconditions.checkArgument(isAdd, "add error");

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("add error",e);
            result = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return result;
    }

    @RequestMapping("/addList")
    @ResponseBody
    public RetData<String> addList(@RequestBody List<Account> records, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isAdd = accountService.addList(records);
            Preconditions.checkArgument(isAdd, "add error");

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("add error",e);
            result = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public RetData<String> update(@RequestBody Account record, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isUpdate = accountService.update(record);
            Preconditions.checkArgument(isUpdate, "update error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("update error",e);
            result = RetUtil.buildErrorRet(RetStatus.UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public RetData<String> delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isDelete = accountService.delete(id);
            Preconditions.checkArgument(isDelete, "delete error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("delete error",e);
            result = RetUtil.buildErrorRet(RetStatus.DELETE_ERROR);
        }
        return result;
    }

}
