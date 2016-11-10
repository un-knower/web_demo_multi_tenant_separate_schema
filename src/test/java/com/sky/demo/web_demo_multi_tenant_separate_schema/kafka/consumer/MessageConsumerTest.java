package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.consumer;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;

import java.util.List;

/**
 * Created by user on 16/11/7.
 */
public class MessageConsumerTest {


    @Test
    public void test_consume() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");
        int size = 100;

        MessageConsumer consumer = MessageConsumer.getInstance();
        for (int i = 0; i < 2; i++) {
            List<ConsumerRecord<String, String>> result = consumer.fetch(topic, size);
            System.out.println(result.size());
        }
    }

    @Test
    public void test_consumeByManualCommit() {
        String topic = AppConfig.getItem("kafka.topic.test", "test");
        int size = 3;

        MessageConsumer consumer = MessageConsumer.getInstance();
        List<ConsumerRecord<String, String>> result = consumer.fetchByManualCommit(topic, size);

        System.out.println(result);
    }



    @Test
    public void test_consumeMultiTopic() {
        String topic1 = AppConfig.getItem("kafka.topic.test", "test");
        String topic2 = "origin_incidents";
        int size = 10;

        MessageConsumer consumer = MessageConsumer.getInstance();
        for (int i = 0; i < 2; i++) {
            List<ConsumerRecord<String, String>> result = consumer.fetch(topic1, size);
            System.out.println(result.size());
        }

        for (int i = 0; i < 2; i++) {
            List<ConsumerRecord<String, String>> result = consumer.fetch(topic2, size);
            System.out.println(result.size());
        }
    }
}
