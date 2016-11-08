package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.producer;

import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by user on 16/11/7.
 */
public class MessageProducerTest {


    @Test
    public void test_send() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");
        String value = "{\"name\":\"zhangsan\"}";

        MessageProducer producer = MessageProducer.getInstance();

        boolean result = producer.send(topic, value);
        Assert.assertTrue(result);
    }

}
