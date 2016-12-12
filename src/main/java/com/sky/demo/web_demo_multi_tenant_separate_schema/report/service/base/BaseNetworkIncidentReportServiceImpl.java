package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.acc.NetworkIncidentReportAcc;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao.NetworkIncidentReportDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentDashboardType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.IncidentReportForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.IncidentReportFilterForEsUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.util.IncidentReportFilterUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BaseNetworkIncidentReportServiceImpl
extends AbstractBaseIncidentReportService
implements BaseNetworkIncidentReportService {

    private static final Logger logger = LoggerFactory.getLogger(BaseNetworkIncidentReportServiceImpl.class);

    @Resource
    private NetworkIncidentReportDao networkIncidentReportDao;

    @Resource
    private NetworkIncidentReportAcc networkIncidentReportAcc;

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
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.POLICY + ", filter:\n" + JsonUtil.writeValueAsString( form.getFilter()));
        long beginTime = System.currentTimeMillis();

        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom(0);
        queryCondition.setSize(0);

        String aggFirst = "incidentPolicies.policyName.keyword";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";

        List<String> fetchSourceIncludes = Lists.newArrayList("COUNT", aggFirst, aggSecond);
        queryCondition.setFetchSourceIncludes(fetchSourceIncludes);

        List<String> storeFields = Lists.newArrayList(aggFirst, aggSecond);
        queryCondition.setStoreFields(storeFields);


        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(form.getFilter());
        queryCondition.setBoolQueryMusts(queryBuilders);

        List<AggregationBuilder> aggregationBuilderList = Lists.newArrayList();
        ValueCountAggregationBuilder valueCountAggregationBuilder = AggregationBuilders
                .count(aggThird)
                .field("_index");

        AggregationBuilder agg = AggregationBuilders
                .terms(aggFirst)
                .field(aggFirst)
                .size(form.getShowLimit())
                .order(Terms.Order.aggregation(aggThird, false))
//                .showTermDocCountError(true)
                .subAggregation(AggregationBuilders
                        .terms(aggSecond)
                        .field(aggSecond))
//                        .showTermDocCountError(true)

//                        .order(Terms.Order.aggregation("_count", false)))
                        .subAggregation(valueCountAggregationBuilder);

        logger.info("Aggregation info: {}", JsonUtil.writeValueAsString(agg));
        aggregationBuilderList.add(agg);
        queryCondition.setAggregationBuilders(aggregationBuilderList);


        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            Terms terms = response.getAggregations().get(aggFirst);
            if (terms != null) {
                for (Terms.Bucket bucket : terms.getBuckets()) {
                    Terms subTerms = bucket.getAggregations().get(aggSecond);
                    for (Terms.Bucket subBucket : subTerms.getBuckets()) {
                        EchartsForm echartsForm = new EchartsForm();
                        echartsForm.setX(bucket.getKey());
                        echartsForm.setY(subBucket.getDocCount());
                        echartsForm.setZ(subBucket.getKey());
                        list.add(echartsForm);
                    }
                }
            }
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.POLICY + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfSource(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.SOURCE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.source_entry_id ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.source_common_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfSource(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.SOURCE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfDestination(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.DESTINATION + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.destination_common_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.destination_common_name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfDestination(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.DESTINATION + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfChannelType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.CHANNEL_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.channel_type ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.channel_type, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfChannelType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.CHANNEL_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfSeverityType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfActionType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.ACTION_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.action_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfActionType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.ACTION_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfPolicyGroup(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.POLICY_GROUP + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.name, incident.severity_type ");
        condition.put("INCIDENT_ORDER_BY", "incident.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfPolicyGroup(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.POLICY_GROUP + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfStatusType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.STATUS_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.status_type, tb.severity_type ");
        condition.put("ORDER_BY", "tb.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectListOfStatusType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.STATUS_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryTrendListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.detect_time, tb.severity_type ");
        condition.put("ORDER_BY", "tb.detect_time, tb.severity_type ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectTrendListOfSeverityType(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryTrendListOfPolicyDetectTime(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_POLICY_DETECT_TIME + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();
        Map condition = IncidentReportFilterUtil.buildIncidentReportCondition(form.getFilter());
        condition.put("GROUP_BY", "tb.policy_name ");
        condition.put("ORDER_BY", "cnt desc ");
        condition.put("LIMIT", form.getShowLimit());
        condition.put("INCIDENT_GROUP_BY", "incident.detect_time, incident.policy_name ");
        condition.put("INCIDENT_ORDER_BY", "incident.detect_time ");
        List<EchartsForm> list = this.networkIncidentReportDao.selectTrendListOfPolicyDetectTime(condition);
        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_POLICY_DETECT_TIME + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }


    public static void main(String[] args) {
        AggregationBuilder agg = AggregationBuilders
                .terms("agg1")
                .field("field");
        System.out.println(JsonUtil.writeValueAsString(agg));


    }
}

