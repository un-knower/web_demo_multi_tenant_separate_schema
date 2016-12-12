package com.sky.demo.web_demo_multi_tenant_separate_schema.report.acc;

import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import org.elasticsearch.action.search.SearchResponse;

import java.util.Map;

/**
 * Created by user on 16/12/2.
 */
public interface NetworkIncidentReportAcc {

    public SearchResponse searchNetworkIncident(QueryCondition queryCondition);
}