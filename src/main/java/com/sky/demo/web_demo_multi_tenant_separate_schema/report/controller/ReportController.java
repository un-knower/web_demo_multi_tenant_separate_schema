package com.sky.demo.web_demo_multi_tenant_separate_schema.report.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.BaseQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident.NetworkIncidentReportService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by user on 16/12/5.
 */
@RequestMapping("/report")
@Controller
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Resource
    private NetworkIncidentReportService networkIncidentReportService;


    @RequestMapping("/queryNetworkIncident")
    @ResponseBody
    public RetData<Pager<NetworkIncident>> queryNetworkIncident(@RequestBody IncidentReportFilterForm filterForm, HttpServletRequest request,
                                                                         HttpServletResponse response) {
        RetData<Pager<NetworkIncident>> result = null;

        try {
            BaseQueryRequest queryRequest = new BaseQueryRequest();
            queryRequest.setPageNumber(3);
            queryRequest.setPageSize(3);

            Pager<NetworkIncident> networkIncidents = networkIncidentReportService.queryNetworkIncident(queryRequest, filterForm);

            result = RetUtil.buildSuccessRet(networkIncidents);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/getAllFilteredNetworkIncidents")
    @ResponseBody
    public RetData<List<NetworkIncident>> getAllFilteredNetworkIncidents(@RequestBody IncidentReportFilterForm filterForm, HttpServletRequest request,
                                                            HttpServletResponse response) {
        RetData<List<NetworkIncident>> result = null;

        try {
            List<NetworkIncident> networkIncidents = networkIncidentReportService.getAllFilteredNetworkIncidents(filterForm);

            result = RetUtil.buildSuccessRet(networkIncidents);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryCountOfNetworkIncident")
    @ResponseBody
    public RetData<Long> queryCountOfNetworkIncident(@RequestBody IncidentReportFilterForm filterForm, HttpServletRequest request,
                                                                         HttpServletResponse response) {
        RetData<Long> result = null;
        try {
            Long count = networkIncidentReportService.queryCountOfNetworkIncident(filterForm);

            result = RetUtil.buildSuccessRet(count);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


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

    @RequestMapping("/getIncidentByTransactionId")
    @ResponseBody
    public RetData<NetworkIncident> getIncidentByTransactionId(@RequestParam String id, HttpServletRequest request,
                                                    HttpServletResponse response) {
        RetData<NetworkIncident> result = null;

        try {
            NetworkIncident networkIncident = networkIncidentReportService.getIncidentByTransactionId(id);

            result = RetUtil.buildSuccessRet(networkIncident);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


    @RequestMapping("/getIncidentsByIds")
    @ResponseBody
    public RetData<List<NetworkIncident>> getIncidentsByIds(@RequestBody List<Long> ids, HttpServletRequest request,
                                                    HttpServletResponse response) {
        RetData<List<NetworkIncident>> result = null;

        try {
            List<NetworkIncident> networkIncidents = networkIncidentReportService.getIncidentsByIds(ids);

            result = RetUtil.buildSuccessRet(networkIncidents);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/getIncidentsByTransactionIds")
    @ResponseBody
    public RetData<List<NetworkIncident>> getIncidentsByTransactionIds(@RequestBody List<String> ids, HttpServletRequest request,
                                                               HttpServletResponse response) {
        RetData<List<NetworkIncident>> result = null;

        try {
            List<NetworkIncident> networkIncident = networkIncidentReportService.getIncidentsByTransactionIds(ids);

            result = RetUtil.buildSuccessRet(networkIncident);

        } catch (Exception e) {
            logger.error("query error");
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


}
