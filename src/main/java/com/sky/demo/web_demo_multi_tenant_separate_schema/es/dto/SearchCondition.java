package com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto;

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

    private List<String> indexes;
    private List<String> types;

    private QueryBuilder queryBuilder;
    private List<SortBuilder> sortBuilders;
    private List<AggregationBuilder> aggregationBuilders;

    private Integer from;
    private Integer size;

    private SearchType searchType;
    private Boolean explain;

    public List<String> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<String> indexes) {
        this.indexes = indexes;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
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
        return "SearchCondition{" +
                "indexes=" + indexes +
                ", types=" + types +
                ", queryBuilder=" + queryBuilder +
                ", sortBuilders=" + sortBuilders +
                ", aggregationBuilders=" + aggregationBuilders +
                ", from=" + from +
                ", size=" + size +
                ", searchType=" + searchType +
                ", explain=" + explain +
                '}';
    }
}
