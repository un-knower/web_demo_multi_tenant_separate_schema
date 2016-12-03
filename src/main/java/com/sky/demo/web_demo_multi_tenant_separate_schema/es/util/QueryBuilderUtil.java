package com.sky.demo.web_demo_multi_tenant_separate_schema.es.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by user on 16/11/30.
 */
public class QueryBuilderUtil {

    private static final Logger logger = LoggerFactory.getLogger(QueryBuilderUtil.class);

    //===================Match All Query===================

    public static QueryBuilder buildMatchAllQuery() {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery()
                .boost(1f)
                ;

        return queryBuilder;
    }


    //===================Full text queries===================

    public static QueryBuilder buildMatchQuery(String fieldName, Object text) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(fieldName, text)
                .operator(Operator.AND)
                .zeroTermsQuery(MatchQuery.ZeroTermsQuery.ALL);

        return queryBuilder;
    }

    public static QueryBuilder buildMultiMathQuery(Object text, String... fieldNames) {
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(text, fieldNames)
                .operator(Operator.AND)
                .zeroTermsQuery(MatchQuery.ZeroTermsQuery.ALL);

        return queryBuilder;
    }

    public static QueryBuilder buildMatchPhraseQuery(String name, Object text) {
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(name, text);

        return queryBuilder;
    }

    public static QueryBuilder buildMatchPhrasePrefixQuery(String name, Object text) {
        QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery(name, text);

        return queryBuilder;
    }

    public static QueryBuilder buildCommonTermsQuery(String fieldName, Object text) {
        QueryBuilder queryBuilder = QueryBuilders.commonTermsQuery(fieldName, text);

        return queryBuilder;
    }

    public static QueryBuilder buildQueryStringQuery(String queryString) {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(queryString);

        return queryBuilder;
    }

    public static QueryBuilder buildSimpleQueryStringQuery(String queryString) {
        QueryBuilder queryBuilder = QueryBuilders.simpleQueryStringQuery(queryString);

        return queryBuilder;
    }


    //===================Term level queries===================

    public static QueryBuilder buildTermQuery(String name, Object value) {
        QueryBuilder queryBuilder = QueryBuilders.termQuery(name, value);

        return queryBuilder;
    }

    public static QueryBuilder buildTermsQuery(String name, Object... values) {
        QueryBuilder queryBuilder = QueryBuilders.termsQuery(name, values);

        return queryBuilder;
    }

    public static QueryBuilder buildRangeQuery(String name, int from, int to, boolean lower, boolean upper) {
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery(name)
                .from(from)
                .to(to)
//                .gte()
//                .lte()
//                .gt()
//                .lt()
                .includeLower(lower)
                .includeUpper(upper);

        return queryBuilder;
    }

    public static QueryBuilder buildExistsQuery(String name) {
        QueryBuilder queryBuilder = QueryBuilders.existsQuery(name);

        return queryBuilder;
    }

    public static QueryBuilder buildPrefixQuery(String name, String prefix) {
        QueryBuilder queryBuilder = QueryBuilders.prefixQuery(name, prefix);

        return queryBuilder;
    }

    public static QueryBuilder buildWildcardQuery(String name, String query) {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery(name, query);

        return queryBuilder;
    }

    public static QueryBuilder buildRegexpQuery(String name, String regexp) {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery(name, regexp);

        return queryBuilder;
    }

    public static QueryBuilder buildTypeQuery(String type) {
        QueryBuilder queryBuilder = QueryBuilders.typeQuery(type);

        return queryBuilder;
    }

    public static QueryBuilder buildIdsQuery(String type, String... ids) {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery(type)
                .addIds(ids);

        return queryBuilder;
    }


    //===================Compound queries===================

    public static QueryBuilder buildConstantScoreQuery(QueryBuilder queryBuilder, float boost) {
        QueryBuilder builder = QueryBuilders.constantScoreQuery(queryBuilder)
                .boost(boost);

        return builder;
    }

    public static QueryBuilder buildBoolQuery(List<QueryBuilder> musts, List<QueryBuilder> mustNots, List<QueryBuilder> shoulds, List<QueryBuilder> filters) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        if (CollectionUtils.isNotEmpty(musts)) {
            musts.forEach(must -> builder.must());
        }
        if (CollectionUtils.isNotEmpty(mustNots)) {
            mustNots.forEach(mustNot -> builder.mustNot(mustNot));
        }
        if (CollectionUtils.isNotEmpty(shoulds)) {
            shoulds.forEach(should -> builder.should(should));
        }
        if (CollectionUtils.isNotEmpty(filters)) {
            filters.forEach(filter -> builder.filter(filter));
        }

        return builder;
    }

    public static QueryBuilder buildDisMaxQuery(List<QueryBuilder> queryBuilders, float boost, float tieBreaker) {
        DisMaxQueryBuilder builder = QueryBuilders.disMaxQuery();

        if (CollectionUtils.isNotEmpty(queryBuilders)) {
            queryBuilders.forEach(queryBuilder -> builder.add(queryBuilder));
        }

        builder.boost(boost)
                .tieBreaker(tieBreaker);

        return builder;
    }

//    public static void buildFunctionScoreQuery() {
//        FilterFunctionBuilder[] functions = {
//                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//                        matchQuery("name", "kimchy"),
//                        randomFunction("ABCDEF")),
//                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//                        exponentialDecayFunction("age", 0L, 1L))
//        };
//    }

    public static QueryBuilder buildBoostingQuery(QueryBuilder positiveQuery, QueryBuilder negativeQuery, float boost) {
        BoostingQueryBuilder builder = QueryBuilders.boostingQuery(positiveQuery, negativeQuery)
                .negativeBoost(boost);

        return builder;
    }


    //===================Joining queries===================

    public static QueryBuilder buildNestedQuery(String path, QueryBuilder query, ScoreMode scoreMode) {
        QueryBuilder builder = QueryBuilders.nestedQuery(path, query, scoreMode);

        return builder;
    }

    public static QueryBuilder buildHasChildQuery(String type, QueryBuilder query, ScoreMode scoreMode) {
        QueryBuilder builder = QueryBuilders.hasChildQuery(type, query, scoreMode);

        return builder;
    }

    public static QueryBuilder buildHasParentQuery(String type, QueryBuilder query, boolean score) {
        QueryBuilder builder = QueryBuilders.hasParentQuery(type, query, score);

        return builder;
    }

    //===================Geo queries===================
    //TODO


    //===================Specialized queries===================
    //TODO

    //===================Span queries===================

    public static QueryBuilder buildSpanTermQuery(String name, String value) {
        QueryBuilder queryBuilder = QueryBuilders.spanTermQuery(name, value);

        return queryBuilder;
    }

    /**
     * FuzzyQueryBuilder, PrefixQueryBuilder, RangeQueryBuilder, RegexpQueryBuilder or WildcardQueryBuilder
     * @param multiTermQueryBuilder
     * @return
     */
    public static QueryBuilder buildMultiSpanTermQuery(MultiTermQueryBuilder multiTermQueryBuilder) {
        QueryBuilder queryBuilder = QueryBuilders.spanMultiTermQueryBuilder(multiTermQueryBuilder);

        return queryBuilder;
    }

    public static QueryBuilder buildSpanFirstQuery(SpanQueryBuilder match, int end) {
        QueryBuilder queryBuilder = QueryBuilders.spanFirstQuery(match, end);

        return queryBuilder;
    }

    public static QueryBuilder buildSpanNearQuery(SpanQueryBuilder initialClause, int slop) {
        QueryBuilder queryBuilder = QueryBuilders.spanNearQuery(initialClause, slop);
//                .clauses()
//                .inOrder()
//                .collectPayloads()

        return queryBuilder;
    }

    public static QueryBuilder buildSpanOrQuery(SpanQueryBuilder initialClause) {
        QueryBuilder queryBuilder = QueryBuilders.spanOrQuery(initialClause);
//                .clause(spanTermQuery("field","value2"))
//                .clause(spanTermQuery("field","value3"));
        return queryBuilder;
    }

    public static QueryBuilder buildSpanNotQuery(SpanQueryBuilder include, SpanQueryBuilder exclude) {
        QueryBuilder queryBuilder = QueryBuilders.spanNotQuery(include, exclude);

        return queryBuilder;
    }

    public static QueryBuilder buildSpanContainingQuery(SpanQueryBuilder big, SpanQueryBuilder little) {
        QueryBuilder queryBuilder = QueryBuilders.spanContainingQuery(big, little);

        return queryBuilder;
    }

    public static QueryBuilder buildSpanWithinQuery(SpanQueryBuilder big, SpanQueryBuilder little) {
        QueryBuilder queryBuilder = QueryBuilders.spanWithinQuery(big, little);

        return queryBuilder;
    }
}
