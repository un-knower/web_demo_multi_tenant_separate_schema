package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by user on 17/5/17.
 */
//method 1
@Component
public class ParserFactoryBean extends ServiceLocatorFactoryBean {

    @Override
    public void afterPropertiesSet() {

        setServiceLocatorInterface(ParserFactory.class);

        Properties properties = new Properties();
        properties.put(ParserType.JSON_PARSER.getName(), "JsonParserBean");
        properties.put(ParserType.XML_PARSER.getName(), "XmlParserBean");
        setServiceMappings(properties);

        super.afterPropertiesSet();
    }
}
