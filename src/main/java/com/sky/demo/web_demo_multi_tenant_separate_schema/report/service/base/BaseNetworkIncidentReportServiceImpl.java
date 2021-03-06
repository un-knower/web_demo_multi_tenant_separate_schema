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
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Constants;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

        String aggFirst = "incidentPolicies.policyName.keyword";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
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

        String aggFirst = "sourceEntryInfo.commonName.keyword";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.SOURCE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfDestination(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.DESTINATION + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "incidentDestinations.destinationEntryInfo.commonName.keyword";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.DESTINATION + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfChannelType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.CHANNEL_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "channelTypeCode";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.CHANNEL_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = null;
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfActionType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.ACTION_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "actionTypeCode";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.ACTION_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfPolicyGroup(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.POLICY_GROUP + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "incidentPolicies.groupName.keyword";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.POLICY_GROUP + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryListOfStatusType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.STATUS_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "statusTypeCode";
        String aggSecond = "severityTypeCode";
        String aggThird = "cnt";
        boolean isTrend = false;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.STATUS_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryTrendListOfSeverityType(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_SEVERITY_TYPE + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "severityTypeCode";
        String aggSecond = "localeDetectTime";
        String aggThird = "cnt";
        boolean isTrend = true;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_SEVERITY_TYPE + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }

    @Override
    public List<EchartsForm> queryTrendListOfPolicyDetectTime(IncidentReportForm form) {
        Preconditions.checkNotNull(form, "report is null");
        logger.info("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_POLICY_DETECT_TIME + ", filter:\n" + JsonUtil.writeValueAsString(form.getFilter()));
        long beginTime = System.currentTimeMillis();

        String aggFirst = "incidentPolicies.policyName.keyword";
        String aggSecond = "localeDetectTime";
        String aggThird = "cnt";
        boolean isTrend = true;

        QueryCondition queryCondition = buildQueryCondition(form, aggFirst, aggSecond, aggThird, isTrend);

        List<EchartsForm> list = Lists.newArrayList();
        SearchResponse response = networkIncidentReportAcc.searchNetworkIncident(queryCondition);
        if (response != null) {
//            logger.debug("-----> SearchResponse : \n{}", JsonUtil.writeValueAsString(response));

            SearchHits searchHits = response.getHits();
            logger.info("------> SearchHit total : {}", searchHits.totalHits());

            buildEcharsList(aggFirst, aggSecond, list, response, isTrend);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("=========> IncidentDashboardType:" + IncidentDashboardType.TREND_POLICY_DETECT_TIME + ", cost:" + (endTime - beginTime) + ", list:\n" + JsonUtil.writeValueAsString(list));
        return list;
    }


    private QueryCondition buildQueryCondition(IncidentReportForm form, String aggFirst, String aggSecond, String aggThird, boolean isTrend) {
        QueryCondition queryCondition = initQueryCondition();
        queryCondition.setType(IncidentType.NETWORK.getName());
        queryCondition.setFrom(0);
        queryCondition.setSize(0);

        List<String> fetchSourceIncludes;
        if (StringUtils.isNotBlank(aggFirst)) {
            fetchSourceIncludes = Lists.newArrayList("COUNT", aggFirst, aggSecond);
        } else {
            fetchSourceIncludes = Lists.newArrayList("COUNT", aggSecond);
        }
        queryCondition.setFetchSourceIncludes(fetchSourceIncludes);

        List<String> storeFields;
        if (StringUtils.isNotBlank(aggFirst)) {
            storeFields = Lists.newArrayList(aggFirst, aggSecond);
        } else {
            storeFields = Lists.newArrayList(aggSecond);
        }
        queryCondition.setStoreFields(storeFields);


        List<QueryBuilder> queryBuilders = IncidentReportFilterForEsUtil.buildIncidentReportCondition(form.getFilter());
        queryCondition.setBoolQueryMusts(queryBuilders);

        List<AggregationBuilder> aggregationBuilderList = Lists.newArrayList();

        ValueCountAggregationBuilder thirdAggregationBuilder = AggregationBuilders
                .count(aggThird)
                .field("_index");

        AggregationBuilder secondAggregationBuilder = null;
        if (isTrend) {
            secondAggregationBuilder = AggregationBuilders
                    .dateHistogram(aggSecond)
                    .field(aggSecond)
                    .dateHistogramInterval(DateHistogramInterval.DAY)
                    .format(Constants.DATE_PATTERN);
        } else {
            secondAggregationBuilder = AggregationBuilders
                    .terms(aggSecond)
                    .field(aggSecond);
        }

        AggregationBuilder firstAggregationBuilder = null;
        if (StringUtils.isNotBlank(aggFirst)) {
            firstAggregationBuilder = AggregationBuilders
                    .terms(aggFirst)
                    .field(aggFirst)
                    .size(form.getShowLimit())
                    .order(Terms.Order.aggregation(aggThird, false))
                    .subAggregation(secondAggregationBuilder)
                    .subAggregation(thirdAggregationBuilder);
        } else {
            firstAggregationBuilder = secondAggregationBuilder
                    .subAggregation(thirdAggregationBuilder);
        }

//        logger.info("Aggregation info: {}", JsonUtil.writeValueAsString(agg));
        aggregationBuilderList.add(firstAggregationBuilder);
        queryCondition.setAggregationBuilders(aggregationBuilderList);
        return queryCondition;
    }

    private void buildEcharsList(String aggFirst, String aggSecond, List<EchartsForm> list, SearchResponse response, boolean isTrend) {
        if (StringUtils.isNotBlank(aggFirst)) {
            Terms terms = response.getAggregations().get(aggFirst);
            if (terms != null) {
                for (Terms.Bucket bucket : terms.getBuckets()) {
                    if (isTrend) {
                        Histogram histogram = bucket.getAggregations().get(aggSecond);
                        for (Histogram.Bucket subBucket : histogram.getBuckets()) {
                            EchartsForm echartsForm = new EchartsForm();
                            echartsForm.setX(bucket.getKey());
                            echartsForm.setY(subBucket.getDocCount());
                            echartsForm.setZ(subBucket.getKeyAsString());
                            list.add(echartsForm);
                        }
                    } else {
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
        } else if (StringUtils.isNotBlank(aggSecond)) {
            Terms terms = response.getAggregations().get(aggSecond);
            if (terms != null) {
                for (Terms.Bucket bucket : terms.getBuckets()) {
                    EchartsForm echartsForm = new EchartsForm();
                    echartsForm.setY(bucket.getDocCount());
                    echartsForm.setZ(bucket.getKey());
                    list.add(echartsForm);
                }
            }
        }
    }


    public static void main(String[] args) {
        AggregationBuilder agg = AggregationBuilders
                .terms("agg1")
                .field("field");
        System.out.println(JsonUtil.writeValueAsString(agg));


    }
}

