package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.producer;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by user on 16/11/7.
 */
public class MessageProducerTest {


    @Test
    public void test_send() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");
        boolean result = false;

        List<String> values = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String value = "{\"name\":\"message" + i +"\"}";
            values.add(value);
        }
        MessageProducer producer = MessageProducer.getInstance();

        result = producer.send(topic, values);
        Assert.assertTrue(result);
    }

    @Test
    public void test_sendWithCallBack() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");

        List<String> values = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String value = "{\"name\":\"message" + i +"\"}";
            values.add(value);
        }
        MessageProducer producer = MessageProducer.getInstance();

        producer.sendWithCallBack(topic, values);
    }

}
