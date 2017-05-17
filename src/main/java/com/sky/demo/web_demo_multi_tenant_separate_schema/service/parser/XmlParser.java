package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

import org.springframework.stereotype.Component;

/**
 * Created by user on 17/5/17.
 */
@Component("XmlParserBean")
public class XmlParser implements Parser {

    @Override
    public void parse(String input) {
        System.out.println("xml parse " + input);
    }
}
