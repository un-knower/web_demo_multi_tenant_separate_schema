package com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/11/29.
 */
public class SearchCondition implements Serializable {

    private List<String> indexes;
    private List<String> types;
    private SearchType searchType;

    private List<QueryBuilder> queryBuilders;
    private List<SortBuilder> sortBuilders;

    private Integer from;
    private Integer size;
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

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public List<QueryBuilder> getQueryBuilders() {
        return queryBuilders;
    }

    public void setQueryBuilders(List<QueryBuilder> queryBuilders) {
        this.queryBuilders = queryBuilders;
    }

    public List<SortBuilder> getSortBuilders() {
        return sortBuilders;
    }

    public void setSortBuilders(List<SortBuilder> sortBuilders) {
        this.sortBuilders = sortBuilders;
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
                ", searchType=" + searchType +
                ", queryBuilders=" + queryBuilders +
                ", sortBuilders=" + sortBuilders +
                ", from=" + from +
                ", size=" + size +
                ", explain=" + explain +
                '}';
    }
}
