package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 25/10/2016.
 */
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);


    // singleton
    private static MessageConsumer INSTANCE = new MessageConsumer();

    private MessageConsumer() {}

    public static MessageConsumer getInstance() {
        return INSTANCE;
    }

}
