package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/12/2.
 * for es
 */
public class QueryCondition implements Serializable {

    private String index;
    private String type;

    private List<QueryBuilder> boolQueryMusts;      //for bool query must
    private List<SortBuilder> sortBuilders;
    private List<AggregationBuilder> aggregationBuilders;

    private Integer from;
    private Integer size;
    private List<String> fetchSourceIncludes;       //for includes;
    private List<String> storeFields;               //for store;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QueryBuilder> getBoolQueryMusts() {
        return boolQueryMusts;
    }

    public void setBoolQueryMusts(List<QueryBuilder> boolQueryMusts) {
        this.boolQueryMusts = boolQueryMusts;
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

    @Override
    public String toString() {
        return "QueryCondition{" +
                "index='" + index + '\'' +
                ", type='" + type + '\'' +
                ", boolQueryMusts=" + boolQueryMusts +
                ", sortBuilders=" + sortBuilders +
                ", aggregationBuilders=" + aggregationBuilders +
                ", from=" + from +
                ", size=" + size +
                ", fetchSourceIncludes=" + fetchSourceIncludes +
                ", storeFields=" + storeFields +
                '}';
    }
}
