package com.sky.demo.web_demo_multi_tenant_separate_schema.es.document;

import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/11/29.
 */
@Service
public class QueryServiceImpl implements QueryService {

    private static final Logger logger = LoggerFactory.getLogger((QueryServiceImpl.class));

    @Override
    public void query() {
//        QueryBuilder queryBuilder = MatchAllQueryBuilder();
    }
}
