/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Preconditions
 *  com.sky.sps.base.common.echarts.base.EchartsForm
 *  com.sky.sps.base.utils.json.JsonUtils
 *  com.sky.sps.dlp.common.endpoint.dm.constant.EndpointTerminalType
 *  com.sky.sps.dlp.common.report.dm.dto.IncidentDashboardType
 *  com.sky.sps.dlp.common.report.dm.dto.IncidentReportFilterForm
 *  com.sky.sps.dlp.common.report.dm.dto.IncidentReportForm
 *  com.sky.sps.dlp.common.report.util.IncidentReportFilterUtil
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao.EndpointIncidentReportDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentDashboardType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.IncidentReportFilterUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.invoke.LambdaMetafactory;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BaseEndpointIncidentReportServiceImpl
extends AbstractBaseIncidentReportService
implements BaseEndpointIncidentReportService {
    private static final Logger logger = LoggerFactory.getLogger(BaseEndpointIncidentReportServiceImpl.class);
    @Resource
    private EndpointIncidentReportDao endpointIncidentReportDao;

    @Override
    protected List<EchartsForm> getEcharsForms(IncidentDashboardType dashboardType, IncidentReportForm form) {
        List<EchartsForm> echartsForms = null;
        switch (dashboardType) {
            case POLICY: {
                echartsForms = this.queryListOfPolicy(form);
                break;
            }
            case SOURCE: {
                echartsForms = this.queryListOfSource(form);
                break;
            }
            case DESTINATION: {
                echartsForms = this.queryListOfDestination(form);
                break;
            }
            case CHANNEL_TYPE: {
                echartsForms = this.queryListOfChannelType(form);
                break;
            }
            case SEVERITY_TYPE: {
                echartsForms = this.queryTrendListOfSeverityType(form);
                break;
            }
            case ACTION_TYPE: {
                echartsForms = this.queryListOfActionType(form);
                break;
            }
            case POLICY_GROUP: {
                echartsForms = this.queryListOfPolicyGroup(form);
                break;
            }
            case STATUS_TYPE: {
                echartsForms = this.queryListOfStatusType(form);
                break;
            }
            case ENDPOINT_CORPORATE_TYPE: {
                echartsForms = this.queryListOfCorporateType(form);
                break;
            }
            case ENDPOINT_DEVICE_TYPE: {
                echartsForms = this.queryListOfDeviceType(form);
                break;
            }
            case ENDPOINT_OPERATION_SYSTEM: {
                echartsForms = this.queryListOfOperationSystem(form);
                break;
            }
            case ENDPOINT_REGISTER_DEVICE_TYPE: {
                echartsForms = this.queryListOfRegisterDeviceType(form);
                break;
            }
            case TREND_SEVERITY_TYPE: {
                echartsForms = this.queryTrendListOfSeverityType(form);
                break;
            }
            case TREND_POLICY_DETECT_TIME: {
                echartsForms = this.queryTrendListOfPolicyDetectTime(form);
                break;
            }
        }
        return echartsForms;
    }

    @Override
    public List<EchartsForm> queryListOfPolicy(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.POLICY + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.policy_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.policy_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfPolicy(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.POLICY + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfSource(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.SOURCE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.source_entry_id ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.source_common_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfSource(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.SOURCE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfDestination(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.DESTINATION + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.destination_common_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.destination_common_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfDestination(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.DESTINATION + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfChannelType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.CHANNEL_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.channel_type ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.channel_type, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfChannelType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.CHANNEL_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfSeverityType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfActionType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ACTION_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.action_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfActionType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ACTION_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfPolicyGroup(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.POLICY_GROUP + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfPolicyGroup(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.POLICY_GROUP + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfStatusType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.STATUS_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.status_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfStatusType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.STATUS_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfCorporateType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_CORPORATE_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.corporate_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfCorporateType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_CORPORATE_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfDeviceType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_DEVICE_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.device_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfDeviceType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_DEVICE_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfOperationSystem(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_OPERATION_SYSTEM + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.operation_system ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.operation_system, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfOperationSystem(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_OPERATION_SYSTEM + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfRegisterDeviceType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_REGISTER_DEVICE_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "terminal_type ");
        condition.put("ORDER_BY", "cnt desc ");
        List terminalTypes = Lists.newArrayList();
        String types = Joiner.on((String)",").skipNulls().join(terminalTypes);
        condition.put("TERMINAL_TYPE", types);
        List<EchartsForm> list = this.endpointIncidentReportDao.selectListOfRegisterDeviceType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.ENDPOINT_REGISTER_DEVICE_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryTrendListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.TREND_SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.detect_time, tb.severity_type ");
        condition.put("ORDER_BY", "tb.detect_time, tb.severity_type ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectTrendListOfSeverityType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.TREND_SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryTrendListOfPolicyDetectTime(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.TREND_POLICY_DETECT_TIME + ", filter:\n" + JsonUtil.writeValueAsString((Object) form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm) form.getFilter());
        condition.put("GROUP_BY", "tb.policy_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.detect_time, incident.policy_name ");
        condition.put("INCIDENT_ORDER_BY", "incident.detect_time ");
        List<EchartsForm> list = this.endpointIncidentReportDao.selectTrendListOfPolicyDetectTime(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.TREND_POLICY_DETECT_TIME + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

}

