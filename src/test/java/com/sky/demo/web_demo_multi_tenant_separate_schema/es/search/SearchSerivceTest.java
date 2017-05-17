package com.sky.demo.web_demo_multi_tenant_separate_schema.es.search;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.dto.SearchCondition;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.util.QueryBuilderUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 17/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring/spring-service.xml"})
@ContextConfiguration(classes={SearchServiceConfig.class})
public class SearchSerivceTest {

    @Resource
    private SearchService searchService;


    private static SearchCondition condition = new SearchCondition();

    @Before
    public void setUp() {

        condition.setIndices(Lists.newArrayList("tenant1"));
        condition.setTypes(Lists.newArrayList("network"));

        QueryBuilder queryBuilder = QueryBuilderUtil.buildMatchAllQuery();

//        List<QueryBuilder> musts = Lists.newArrayList();
//        QueryBuilder queryBuilder = QueryBuilderUtil.buildBoolQuery(musts, null, null ,null);
        condition.setQueryBuilderMusts(Lists.newArrayList(queryBuilder));

        //sort
        List<SortBuilder> sortBuilderList = Lists.newArrayList();
        SortBuilder sortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        sortBuilderList.add(sortBuilder);

        condition.setSortBuilders(sortBuilderList);

        //aggregation

        //from size
        condition.setFrom(10);
        condition.setSize(6);

        //searchType
        //explain
    }


    @Test
    public void test_search() {
        String index = "tenant1";
        String type = "network";
        QueryBuilder queryBuilder = QueryBuilderUtil.buildMatchAllQuery();
        SortBuilder sortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        SearchResponse searchResponse = searchService.search(index, type, queryBuilder, sortBuilder,
                5, 2);

        System.out.println("Hit size:" + searchResponse.getHits().getHits().length);
        System.out.println("Hit total:" + searchResponse.getHits().totalHits());
        Lists.newArrayList(searchResponse.getHits().getHits()).forEach(
                hit -> {
                    System.out.println(hit.getSourceAsString());
                }
        );
    }


    @Test
    public void test_search_with_condition() {
        SearchResponse searchResponse = searchService.search(condition);

        System.out.println("Hit size:" + searchResponse.getHits().getHits().length);
        System.out.println("Hit total:" + searchResponse.getHits().totalHits());
        Lists.newArrayList(searchResponse.getHits().getHits()).forEach(
                hit -> {
                    System.out.println(hit.getSourceAsString());
                }
        );
    }

    @Test
    public void test_searchCount() {
        long count = searchService.searchCount(condition);
        System.out.println("count: " + count);
    }

    @Test
    public void test_searchHis() {
        List<SearchHit> searchHits = searchService.searchHits(condition);

        System.out.println("Hit size:" + searchHits.size());
        searchHits.forEach(
                hit -> {
                    System.out.println(hit.getSourceAsString());
                }
        );
    }


    //=====================scroll api=========================
    @Test
    public void test_searchByScroll() {
        SearchResponse searchResponse = searchService.searchByScroll(condition);

        System.out.println("Hit size:" + searchResponse.getHits().getHits().length);
        System.out.println("Hit total:" + searchResponse.getHits().totalHits());
        Lists.newArrayList(searchResponse.getHits().getHits()).forEach(
                hit -> {
                    System.out.println(hit.getSourceAsString());
                }
        );
    }

    @Test
    public void test_searchCountByScroll() {
        long count = searchService.searchCountByScroll(condition);
        System.out.println("count: " + count);
    }

    @Test
    public void test_searchHitsByScroll() {
        List<SearchHit> searchHits = searchService.searchHitsByScroll(condition);

        System.out.println("Hit size:" + searchHits.size());
        searchHits.forEach(
                hit -> {
                    System.out.println(hit.getSourceAsString());
                }
        );
    }









}
