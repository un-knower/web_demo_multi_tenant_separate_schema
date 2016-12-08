package com.sky.demo.web_demo_multi_tenant_separate_schema.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 16/12/8.
 */
public class AnnotationApp {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationApp.class);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void suppress() throws Exception {

        logger.info("access suppress");

        throw new RuntimeException("fuck suppress");
    }
}
