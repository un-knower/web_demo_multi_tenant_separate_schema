package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by rg on 25/10/2016.
 */
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private static KafkaProducer INSTANCE = new KafkaProducer();


    static {
        Properties properties = new Properties();



    }

    //private Producer<>


    private KafkaProducer() {

    }

    public KafkaProducer getInstance() {
        return INSTANCE;
    }




}
