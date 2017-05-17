package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by user on 17/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/spring-service.xml"})
@ContextConfiguration(classes = ParserServiceConfig.class)
public class ParserServiceTest {

    @Resource
    private ParserService parserService;

    @Test
    public void test_parse() {

        String input = "{\"name\":\"zhangsan\" }";
        ParserType parserType = ParserType.JSON_PARSER;
        parserService.doParse(input, parserType);
    }



}
