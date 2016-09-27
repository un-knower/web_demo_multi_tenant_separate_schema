package com.sky.demo.web_demo_multi_tenant_separate_schema.controller;

import java.util.List;

import javax.annotation.Resource;

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
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.AnLogForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.AnLogInsertRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.AnLogQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.AnLogUpdateRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AnLogService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;


/**
 * Created by rg on 2015/6/11.
 */
@RequestMapping("/anLog")
@Controller
public class AnLogController {

    private static final Logger logger = LoggerFactory.getLogger(AnLogController.class);

    @Resource
    private AnLogService anLogService;


    @RequestMapping("/query/{id}")
    @ResponseBody
    public RetData<AnLogForm> query(@PathVariable long id) {
        RetData<AnLogForm> result = null;
        try {
            AnLogForm anLogForm = anLogService.query(id);

            result = RetUtil.buildSuccessRet(anLogForm);
        } catch (Exception e) {
            logger.error("query log error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public RetData<Pager<AnLogForm>> queryList(@RequestBody AnLogQueryRequest queryRequest) {

        RetData<Pager<AnLogForm>> result = null;
        try {
            Pager<AnLogForm> ret  = anLogService.queryList(queryRequest);
            result = RetUtil.buildSuccessRet(ret);
        } catch (Exception e) {
            logger.error("query log error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


    @RequestMapping("/add")
    @ResponseBody
    public RetData<String> add(@RequestBody AnLogInsertRequest insertRequest) {
        RetData<String> result = null;
        try {
            boolean isAdd = anLogService.add(insertRequest);
            Preconditions.checkArgument(isAdd, "add error");

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("add log error",e);
            result = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return result;
    }

    @RequestMapping("/addList")
    @ResponseBody
    public RetData<String> addList(@RequestBody List<AnLogInsertRequest> insertRequests) {
        RetData<String> result = null;
        try {
            boolean isAdd = anLogService.addList(insertRequests);
            Preconditions.checkArgument(isAdd, "add error");

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("add log error",e);
            result = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public RetData<String> update(@RequestBody AnLogUpdateRequest updateRequest) {
        RetData<String> result = null;
        try {
            boolean isUpdate = anLogService.update(updateRequest);
            Preconditions.checkArgument(isUpdate, "update error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("update log error",e);
            result = RetUtil.buildErrorRet(RetStatus.UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public RetData<String> delete(@PathVariable long id) {
        RetData<String> result = null;
        try {
            boolean isDelete = anLogService.delete(id);
            Preconditions.checkArgument(isDelete, "delete error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("delete log error",e);
            result = RetUtil.buildErrorRet(RetStatus.DELETE_ERROR);
        }
        return result;
    }
}