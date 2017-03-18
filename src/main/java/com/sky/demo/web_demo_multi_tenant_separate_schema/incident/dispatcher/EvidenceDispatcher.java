package com.sky.demo.web_demo_multi_tenant_separate_schema.incident.dispatcher;

import com.sky.demo.web_demo_multi_tenant_separate_schema.incident.parser.EvidenceDataParser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17/3/18.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EvidenceDispatcher extends BaseDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(EvidenceDispatcher.class);

    private String fileName;  //if file name is not unique, need process multi tenant

    @Override
    protected boolean preExecuteDispatcher() throws Exception {
        if (StringUtils.isBlank(fileName)) {
            //get from redis
            fileName = "";

            if (StringUtils.isBlank(fileName)) {
                logger.warn("filename is blank");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void doExecuteDispatcher() throws Exception {
        logger.info("process file name={}", fileName);
        //process file

        getEvidenceDataParser().handleData();

        //insert to redis or db

    }

    @Override
    protected void postExecuteDispatcher() throws Exception {
        fileName = null;
    }


    @Lookup
    protected EvidenceDataParser getEvidenceDataParser() {
        return null;
    }
}
