package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.consumer;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;

import java.util.List;

/**
 * Created by user on 16/11/7.
 */
public class MessageConsumerTest {


    @Test
    public void test_consume() {
        List<String> topics = Lists.newArrayList("test");
        int size = 3;

        MessageConsumer consumer = MessageConsumer.getInstance();
        List<ConsumerRecord<String, String>> result = consumer.consume(topics, size);

        System.out.println(result);
    }

}
