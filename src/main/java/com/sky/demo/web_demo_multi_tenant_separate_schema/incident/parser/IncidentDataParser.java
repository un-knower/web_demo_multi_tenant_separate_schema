package com.sky.demo.web_demo_multi_tenant_separate_schema.incident.parser;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.BaseIncident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 17/3/18.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IncidentDataParser {

    private static final Logger logger = LoggerFactory.getLogger(IncidentDataParser.class);


    public List<String> handleData(List<BaseIncident> input) {
        List<String> result = Lists.newArrayList();
        //handle

        return result;

    }
}
