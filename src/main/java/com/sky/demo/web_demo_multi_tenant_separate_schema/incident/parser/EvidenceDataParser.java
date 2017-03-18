package com.sky.demo.web_demo_multi_tenant_separate_schema.incident.parser;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17/3/18.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EvidenceDataParser {


    public void handleData() {

    }
}
