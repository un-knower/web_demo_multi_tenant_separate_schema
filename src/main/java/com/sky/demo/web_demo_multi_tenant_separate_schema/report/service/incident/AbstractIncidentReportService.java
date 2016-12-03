package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;

/**
 * Created by user on 16/12/2.
 */
public abstract class AbstractIncidentReportService {


    protected QueryCondition initQueryCondition() {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setIndex(DBContext.getDbKey());

        return queryCondition;
    }

}
