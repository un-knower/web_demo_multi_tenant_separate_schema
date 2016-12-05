package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.incident;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.QueryCondition;
import org.apache.commons.lang.StringUtils;

/**
 * Created by user on 16/12/2.
 */
public abstract class AbstractIncidentReportService {


    protected QueryCondition initQueryCondition() {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setIndex(StringUtils.isNotBlank(DBContext.getDbKey()) ? DBContext.getDbKey() : "tenant1");

        return queryCondition;
    }

}
