package com.sky.demo.web_demo_multi_tenant_separate_schema.es.util;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 16/11/30.
 */
public class QueryBuilderUtil {

    private static final Logger logger = LoggerFactory.getLogger(QueryBuilderUtil.class);


    public static QueryBuilder buildMatchAllQuery() {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery()
                .boost(1f)
                ;

        return queryBuilder;
    }

    public static QueryBuilder buildMatchQuery(String name, Object object) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(name, object)
                .operator(Operator.AND)
                .zeroTermsQuery(MatchQuery.ZeroTermsQuery.ALL);

        return queryBuilder;
    }

}
