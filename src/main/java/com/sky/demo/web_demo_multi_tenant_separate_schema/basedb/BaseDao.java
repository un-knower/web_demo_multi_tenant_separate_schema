package com.sky.demo.web_demo_multi_tenant_separate_schema.basedb;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


/**
 * Created by user on 16/9/18.
 */
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



}