package com.sky.demo.web_demo_multi_tenant_separate_schema.es.index;

import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by user on 16/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml"})
public class IndexServiceTest {

    @Resource
    private IndexService indexService;

    @Test
    public void test_createIndexWithDocument() {
        String index = "test-index";
        String type = "animal";
        String document = "{\n" +
                "    \"name\": \"Deck the halls\",\n" +
                "    \"year\": 1885\n" +
                "}";

        IndexResponse indexResponse = indexService.createIndexWithDocument(index, type, document);

        System.out.println(indexResponse);
    }


}
