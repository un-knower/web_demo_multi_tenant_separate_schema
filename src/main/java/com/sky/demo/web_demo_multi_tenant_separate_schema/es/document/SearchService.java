package com.sky.demo.web_demo_multi_tenant_separate_schema.es.document;

import com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto.SearchCondition;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import java.util.List;

/**
 * Created by user on 16/11/29.
 */
public interface SearchService {

    public SearchResponse search(SearchCondition searchCondition);

    public SearchResponse searchUsingScroll(SearchCondition searchCondition);

    public List<SearchHit> searchUsingScrollAllHits(SearchCondition searchCondition);
}
