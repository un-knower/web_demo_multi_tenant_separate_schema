package com.sky.demo.web_demo_multi_tenant_separate_schema.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident.NetworkIncidentReportService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 16/12/5.
 */
@RequestMapping("/report")
@Controller
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Resource
    private NetworkIncidentReportService networkIncidentReportService;


    @RequestMapping("/getIncidentById")
    @ResponseBody
    public RetData<NetworkIncident> getIncidentById(@RequestParam long id, HttpServletRequest request,
                                                    HttpServletResponse response) {
        RetData<NetworkIncident> result = null;

        try {
            NetworkIncident networkIncident = networkIncidentReportService.getIncidentById(id);

            result = RetUtil.buildSuccessRet(networkIncident);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


}
