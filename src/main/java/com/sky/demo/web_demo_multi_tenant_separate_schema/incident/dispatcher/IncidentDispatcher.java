package com.sky.demo.web_demo_multi_tenant_separate_schema.incident.dispatcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.incident.parser.IncidentDataParser;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.BaseIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 17/3/18.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)     //scope=prototype
public class IncidentDispatcher extends BaseDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(IncidentDispatcher.class);

    List<BaseIncident> data = null;



    @Override
    protected boolean preExecuteDispatcher() throws Exception {
        //prepare data
        data = Lists.newArrayList();

        //get from kafka or redis
        String origin = "";

        //
        data = JsonUtil.readValue(origin, new TypeReference<List<BaseIncident>>() {});

        return true;
    }

    @Override
    protected void doExecuteDispatcher() throws Exception {
        //process data
        List<String> result = getIncidentDataParser().handleData(data);

        //insert to db and so on
    }

    @Override
    protected void postExecuteDispatcher() throws Exception {
        //
        data = null;
    }


    @Lookup
    protected IncidentDataParser getIncidentDataParser() {
        return null;
    }

}
