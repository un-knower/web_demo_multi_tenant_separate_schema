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
        for (int j = 0; j < 9; j++) {
            String value = "{\"name\":\"message" + j +"\"}";
            values.add(value);
        }
        MessageProducer producer = MessageProducer.getInstance();
        result = producer.send(topic, values);

        Assert.assertTrue(result);
    }

    @Test
    public void test_send_multi() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");
        boolean result = false;

        for (int i = 0 ; i < 5; i++) {
            List<String> values = Lists.newArrayList();
            for (int j = 0; j < 10; j++) {
                String value = "{\"name\":\"message" + i + j +"\"}";
                values.add(value);
            }
            MessageProducer producer = MessageProducer.getInstance();
            result &= producer.send(topic, values);
        }

        Assert.assertTrue(result);
    }

    @Test
    public void test_sendWithCallBack() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");

        List<String> values = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String value = "{\"name\":\"message" + i + "\"}";
            values.add(value);
        }
        MessageProducer producer = MessageProducer.getInstance();

        producer.sendWithCallBack(topic, values);
    }

    @Test
    public void test_sendMultiTopic() {
        String topic1 = AppConfig.getItem("kafka.topic.test");
        String topic2 = "origin_incidents";


        List<String> values = Lists.newArrayList();
        for (int j = 0; j < 10; j++) {
            String value = "{\"name\":\"message" + j +"\"}";
            values.add(value);
        }
        MessageProducer producer = MessageProducer.getInstance();
        producer.send(topic1, values);
        producer.send(topic2, values);
    }

    @Test
    public void test_sendWithCallBackMultiTopic() {
        String topic1 = AppConfig.getItem("kafka.topic.test");
        String topic2 = "origin_incidents";

        List<String> values = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String value = "{\"name\":\"message" + i +"\"}";
            values.add(value);
        }
        MessageProducer producer = MessageProducer.getInstance();

        producer.sendWithCallBack(topic1, values);
        producer.sendWithCallBack(topic2, values);
    }

}
