/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Lists
 *  com.skyguard.sps.base.common.echarts.base.EchartsForm
 *  com.skyguard.sps.base.utils.common.SqlUtil
 *  com.skyguard.sps.base.utils.json.JsonUtils
 *  com.skyguard.sps.dlp.common.report.dm.dto.DiscoveryIncidentOption
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentDashboardType
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentReportFilterForm
 *  com.skyguard.sps.dlp.common.report.dm.dto.IncidentReportForm
 *  com.skyguard.sps.dlp.common.report.util.IncidentReportFilterUtil
 *  org.apache.commons.collections.CollectionUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao.DiscoveryIncidentReportDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.DiscoveryIncidentOption;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentDashboardType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportFilterForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.IncidentReportFilterUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.SqlUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
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
public class BaseDiscoveryIncidentReportServiceImpl
extends AbstractBaseIncidentReportService
implements BaseDiscoveryIncidentReportService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDiscoveryIncidentReportServiceImpl.class);
    @Resource
    private DiscoveryIncidentReportDao discoveryIncidentReportDao;

    @Override
    protected List<EchartsForm> getEcharsForms(IncidentDashboardType dashboardType, IncidentReportForm form) {
        List<EchartsForm> echartsForms = null;
        switch (dashboardType) {
            case POLICY: {
                echartsForms = this.queryListOfPolicy(form);
                break;
            }
            case RESOURCE_TYPE: {
                echartsForms = this.queryListOfResourceType(form);
                break;
            }
            case SEVERITY_TYPE: {
                echartsForms = this.queryListOfSeverityType(form);
                break;
            }
            case TASK: {
                echartsForms = this.queryListOfTask(form);
                break;
            }
            case STATUS_TYPE: {
                echartsForms = this.queryListOfStatusType(form);
                break;
            }
            case DISCOVERY_STATISTICS: {
                echartsForms = this.queryListOfDiscoveryStatistics(form);
                break;
            }
            case FILE_SENSITIVE: {
                logger.warn("base incident query do not support this type:" + (Object)dashboardType);
                break;
            }
            case DATABASE_SENSITIVE: {
                logger.warn("base incident query do not support this type:" + (Object)dashboardType);
                break;
            }
            case DISCOVERY_TASK_WITH_POLICY_STATISTICS: {
                logger.warn("base incident query do not support this type:" + (Object)dashboardType);
                break;
            }
        }
        return echartsForms;
    }

    @Override
    public List<EchartsForm> queryListOfPolicy(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.POLICY + ", filter:\n" + JsonUtil.writeValueAsString((Object) form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.policy_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.policy_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.discoveryIncidentReportDao.selectListOfPolicy(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.POLICY + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfResourceType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.RESOURCE_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.resource_type ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.resource_type, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.discoveryIncidentReportDao.selectListOfResourceType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.RESOURCE_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.severity_type");
        condition.put("ORDER_BY", "tb.severity_type");
        List<EchartsForm> list = this.discoveryIncidentReportDao.selectListOfSeverityType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfTask(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.TASK + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.job_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.job_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.discoveryIncidentReportDao.selectListOfTask(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.TASK + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfDiscoveryStatistics(IncidentReportForm form) {
        List list2 = null;
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.DISCOVERY_STATISTICS + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm)form.getFilter());
        condition.put("GROUP_BY", "tb.job_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        List<DiscoveryIncidentOption> taskOptions = this.discoveryIncidentReportDao.selectListOfTaskName(condition);
        if (CollectionUtils.isNotEmpty(taskOptions)) {
            Map discoveryStatisticCondition = IncidentReportFilterUtil.buildIncidentReportCondition4DiscoveryStatistic((IncidentReportFilterForm)form.getFilter());
            List taskNames = Lists.newArrayList();
            discoveryStatisticCondition.put("TASKS", SqlUtil.buildColumnsWithQuote(taskNames));
            discoveryStatisticCondition.put("GROUP_BY", "name ");
            discoveryStatisticCondition.put("ORDER_BY", "cnt desc ");
            list2 = this.discoveryIncidentReportDao.selectListOfDiscoveryStatistics(discoveryStatisticCondition);
        } else {
            list2 = Lists.newArrayList();
        }
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.DISCOVERY_STATISTICS + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString((Object)list2));
        return list2;
    }

    @Override
    public List<EchartsForm> queryListOfStatusType(IncidentReportForm form) {
        Preconditions.checkNotNull((Object)form, (Object)"report is null");
        logger.info("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.STATUS_TYPE + ", filter:\n" + JsonUtil.writeValueAsString((Object)form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition((IncidentReportFilterForm) form.getFilter());
        condition.put("GROUP_BY", "tb.status_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.discoveryIncidentReportDao.selectListOfStatusType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + (Object)IncidentDashboardType.STATUS_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

}

