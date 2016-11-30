package com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto;

import org.elasticsearch.action.search.SearchType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/11/29.
 */
public class SearchCondition implements Serializable {

    private List<String> indexes;
    private List<String> types;
    private SearchType searchType;
    private Map<String, Object> term;

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

    public Map<String, Object> getTerm() {
        return term;
    }

    public void setTerm(Map<String, Object> term) {
        this.term = term;
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
        return "QueryCondition{" +
                "indexes=" + indexes +
                ", types=" + types +
                ", searchType=" + searchType +
                ", term=" + term +
                ", from=" + from +
                ", size=" + size +
                ", explain=" + explain +
                '}';
    }
}
