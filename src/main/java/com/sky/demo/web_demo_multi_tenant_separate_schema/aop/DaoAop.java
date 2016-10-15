package com.sky.demo.web_demo_multi_tenant_separate_schema.aop;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by user on 16/10/15.
 */
@Aspect
@Component
public class DaoAop {

    private static final Logger logger = LoggerFactory.getLogger(DaoAop.class);

    //default schema
    @Before("target(com.sky.demo.web_demo_multi_tenant_separate_schema.basedb.MarkDefaultDao)")
    public void defaultDaoBefore() {
        String dbKey = AppConfig.getItem("jdbc.default.key", "public");
        logger.debug("aop before default schema ==>  set DBContext dbKey={}", dbKey);
        DBContext.setDbKey(dbKey);
    }

    //tenant schema
    @Before("target(com.sky.demo.web_demo_multi_tenant_separate_schema.basedb.MarkTenantDao)")
    public void tenantDaoBefore() {
        String dbKey = DBContext.getTenant().getSchemaName();
        logger.debug("aop before tenant schema  ==>  set DBContext dbKey={}", dbKey);
        DBContext.setDbKey(dbKey);
    }

}
