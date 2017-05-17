package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17/5/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser"})
public class ParserServiceConfig {

    //method 2
//    @Bean
//    public FactoryBean serviceLocatorFactoryBean() {
//        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
//        factoryBean.setServiceLocatorInterface(ParserFactory.class);
//        return factoryBean;
//    }
//
//    @Bean(name = "JSON_PARSER")
//    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public JsonParser jsonParser() {
//        return new JsonParser();
//    }
//
//    @Bean(name = "XML_PARSER")
//    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public XmlParser xmlParser() {
//        return new XmlParser();
//    }
}
