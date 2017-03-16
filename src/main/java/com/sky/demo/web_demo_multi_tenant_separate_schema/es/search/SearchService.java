package com.sky.demo.web_demo_multi_tenant_separate_schema.es.search;

import com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto.SearchCondition;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.List;

/**
 * Created by user on 16/11/29.
 */
public interface SearchService {

    public SearchResponse search(String index, String type, QueryBuilder queryBuilder, SortBuilder sortBuilder, int from, int size);

    public SearchResponse search(SearchCondition searchCondition);
    public long searchCount(SearchCondition searchCondition);

    /**
     * 深度分页
     * @param searchCondition
     * @return
     */
    public SearchResponse searchByScroll(SearchCondition searchCondition);
    public long searchCountByScroll(SearchCondition searchCondition);

    public List<SearchHit> searchByScrollAllHits(SearchCondition searchCondition);

    public MultiSearchResponse multiSearch(List<SearchRequestBuilder> searchRequestBuilders);

    public ExplainResponse explain(String index, String type, String id, QueryBuilder queryBuilder);

    public SearchResponse aggregation(SearchCondition searchCondition);
}
