package com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/11/29.
 */
public class SearchCondition implements Serializable {

    private List<String> indices;
    private List<String> types;

    private List<QueryBuilder> queryBuilderMusts;      //for bool query must
    private List<QueryBuilder> queryBuilderMustNots;   //for bool query must nots
    private List<QueryBuilder> queryBuilderShoulds;    //for bool query shoulds
    private List<QueryBuilder> queryBuilderFilters;    //for bool query filters

    private List<SortBuilder> sortBuilders;
    private List<AggregationBuilder> aggregationBuilders;

    private Integer from;
    private Integer size;
    private List<String> fetchSourceIncludes;   //for includes
    private List<String> storeFields;           //for stores

    private SearchType searchType;
    private Boolean explain;


    public List<String> getIndices() {
        return indices;
    }

    public void setIndices(List<String> indices) {
        this.indices = indices;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<QueryBuilder> getQueryBuilderMusts() {
        return queryBuilderMusts;
    }

    public void setQueryBuilderMusts(List<QueryBuilder> queryBuilderMusts) {
        this.queryBuilderMusts = queryBuilderMusts;
    }

    public List<QueryBuilder> getQueryBuilderMustNots() {
        return queryBuilderMustNots;
    }

    public void setQueryBuilderMustNots(List<QueryBuilder> queryBuilderMustNots) {
        this.queryBuilderMustNots = queryBuilderMustNots;
    }

    public List<QueryBuilder> getQueryBuilderShoulds() {
        return queryBuilderShoulds;
    }

    public void setQueryBuilderShoulds(List<QueryBuilder> queryBuilderShoulds) {
        this.queryBuilderShoulds = queryBuilderShoulds;
    }

    public List<QueryBuilder> getQueryBuilderFilters() {
        return queryBuilderFilters;
    }

    public void setQueryBuilderFilters(List<QueryBuilder> queryBuilderFilters) {
        this.queryBuilderFilters = queryBuilderFilters;
    }

    public List<SortBuilder> getSortBuilders() {
        return sortBuilders;
    }

    public void setSortBuilders(List<SortBuilder> sortBuilders) {
        this.sortBuilders = sortBuilders;
    }

    public List<AggregationBuilder> getAggregationBuilders() {
        return aggregationBuilders;
    }

    public void setAggregationBuilders(List<AggregationBuilder> aggregationBuilders) {
        this.aggregationBuilders = aggregationBuilders;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getFetchSourceIncludes() {
        return fetchSourceIncludes;
    }

    public void setFetchSourceIncludes(List<String> fetchSourceIncludes) {
        this.fetchSourceIncludes = fetchSourceIncludes;
    }

    public List<String> getStoreFields() {
        return storeFields;
    }

    public void setStoreFields(List<String> storeFields) {
        this.storeFields = storeFields;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public Boolean getExplain() {
        return explain;
    }

    public void setExplain(Boolean explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("indices", indices)
                .add("types", types)
                .add("queryBuilderMusts", queryBuilderMusts)
                .add("queryBuilderMustNots", queryBuilderMustNots)
                .add("queryBuilderShoulds", queryBuilderShoulds)
                .add("queryBuilderFilters", queryBuilderFilters)
                .add("sortBuilders", sortBuilders)
                .add("aggregationBuilders", aggregationBuilders)
                .add("from", from)
                .add("size", size)
                .add("fetchSourceIncludes", fetchSourceIncludes)
                .add("storeFields", storeFields)
                .add("searchType", searchType)
                .add("explain", explain)
                .toString();
    }
}
