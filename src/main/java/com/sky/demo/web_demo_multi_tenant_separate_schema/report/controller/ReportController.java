package com.sky.demo.web_demo_multi_tenant_separate_schema.report.controller;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.BaseQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base.BaseNetworkIncidentReportService;
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

    @Resource
    private BaseNetworkIncidentReportService baseNetworkIncidentReportService;


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
            logger.error("query error", e);
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
            logger.error("query error", e);
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
            logger.error("query error", e);
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
            logger.error("query error", e);
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
            logger.error("query error", e);
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
            logger.error("query error", e);
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
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


    @RequestMapping("/queryListOfPolicy")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfPolicy(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfPolicy(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfSource")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfSource(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfSource(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfDestination")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfDestination(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfDestination(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfChannelType")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfChannelType(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfChannelType(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfSeverityType")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfSeverityType(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfSeverityType(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfActionType")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfActionType(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfActionType(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfPolicyGroup")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfPolicyGroup(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfPolicyGroup(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryTrendListOfSeverityType")
    @ResponseBody
    public RetData<List<EchartsForm>> queryTrendListOfSeverityType(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryTrendListOfSeverityType(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryTrendListOfPolicyDetectTime")
    @ResponseBody
    public RetData<List<EchartsForm>> queryTrendListOfPolicyDetectTime(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryTrendListOfPolicyDetectTime(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryListOfStatusType")
    @ResponseBody
    public RetData<List<EchartsForm>> queryListOfStatusType(@RequestBody IncidentReportForm form, HttpServletRequest request, HttpServletResponse response) {
        RetData<List<EchartsForm>> result = null;

        try {
            List<EchartsForm> list = baseNetworkIncidentReportService.queryListOfStatusType(form);

            result = RetUtil.buildSuccessRet(list);

        } catch (Exception e) {
            logger.error("query error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }


}
