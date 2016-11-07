package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.producer;

import org.junit.Test;

/**
 * Created by user on 16/11/7.
 */
public class MessageProducerTest {


    @Test
    public void test_send() {
        String topic = "test";
        String value = "{xxx}";

        MessageProducer producer = MessageProducer.getInstance();

        boolean result = producer.send(topic, value);

    }

}
