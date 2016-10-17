package com.sky.demo.web_demo_multi_tenant_separate_schema.basedb;

import javax.annotation.Resource;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.AppContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by user on 16/9/18.
 */
@Deprecated
public abstract class BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";


    @Resource
    private JdbcTemplate defaultJdbcTemplate;          //default_db
    @Resource
    private NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate;      //default_db


    //default_db
    public JdbcTemplate getDefaultJdbcTemplate() {
        //此方式有问题
//        try {
//            final Connection connection = defaultJdbcTemplate.getDataSource().getConnection();
////            connection.createStatement().execute("set schema '" + getSchema() + "'");
//
//            logger.debug("====>schema:{}", getSchema());
//            connection.createStatement().execute("set search_path='" + getSchema() + "'");
//        } catch (SQLException e) {
//            logger.error("switch schema error", e);
//            throw new RuntimeException("switch schema error");
//        }

        return defaultJdbcTemplate;
    }

    public void setDefaultJdbcTemplate(JdbcTemplate defaultJdbcTemplate) {
        this.defaultJdbcTemplate = defaultJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getDefaultNamedParameterJdbcTemplate() {
        return defaultNamedParameterJdbcTemplate;
    }

    public void setDefaultNamedParameterJdbcTemplate(NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate) {
        this.defaultNamedParameterJdbcTemplate = defaultNamedParameterJdbcTemplate;
    }


    /**
     * 获取schema
     * @return
     */
    public String getSchema() {
        String schema = "public";
        try {
            TenantForm tenant = AppContext.getTenant();
            if (tenant != null) {
                schema = tenant.getSchemaName();
            }
        } catch (Exception e) {
            logger.error("get schema error", e);
        }
        return schema;
    }



    /**
     * 获取schema
     * @return
     */
    public String getSchemaDot() {
        return getSchema() + ".";
    }
}
