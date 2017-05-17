package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by user on 17/5/17.
 */
@Service
public class ParserServiceImpl implements ParserService {

    private static final Logger logger = LoggerFactory.getLogger(ParserServiceImpl.class);

    @Resource
    private ParserFactory parserFactory;

    @Override
    public String doParse(String input, ParserType parserType) {

        parserFactory.createParser(parserType).parse(input);

        return null;
    }
}
