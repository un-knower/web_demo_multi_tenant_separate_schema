package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

/**
 * Created by user on 17/5/17.
 */
public interface ParserFactory {

    public Parser createParser(ParserType parserType);
}
