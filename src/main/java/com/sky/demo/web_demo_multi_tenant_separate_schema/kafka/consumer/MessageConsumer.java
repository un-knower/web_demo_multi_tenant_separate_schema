package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.consumer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Created by rg on 25/10/2016.
 */
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);


    private static Properties props;
    static {
        props = new Properties();
        props.put("bootstrap.servers", AppConfig.getItem("kafka.bootstrap.servers"));
        props.put("group.id", "test");
        props.put("enable.auto.commit", AppConfig.getItem("kafka.enable.auto.commit")); //是否自动commit
        props.put("auto.commit.interval.ms", "1000");   //定时commit的周期
        props.put("session.timeout.ms", "30000");       //consumer活性超时时间
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    }

    private KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

    private int CONSUMER_SIZE = 100;

    // singleton
    private static MessageConsumer INSTANCE = new MessageConsumer();

    private MessageConsumer() {}

    public static MessageConsumer getInstance() {
        return INSTANCE;
    }




    public List<ConsumerRecord<String, String>> consume(String topic, int size) {
        List<ConsumerRecord<String, String>> result = Lists.newArrayList();

        int consumedSize = 0;
        try {
            consumer.subscribe(Lists.newArrayList(topic));      // only one topic

            do {
                int offset = (size - consumedSize) > CONSUMER_SIZE ? CONSUMER_SIZE : (size - consumedSize);

                ConsumerRecords<String, String> consumerRecords = consumer.poll(offset);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    result.add(consumerRecord);
                }

                consumedSize += offset;

            } while (consumedSize < size);
        } catch (Exception e) {
            logger.error("poll message failed", e);
        } finally {
            consumer.close();
        }

        return result;
    }


    /**
     * properties : enable.auto.commit = false
     * @param topic
     * @param size
     * @return
     */
    public List<String> consumeByManulCommit(String topic, int size) {


        List<String> result = null;
        consumer.subscribe(Lists.newArrayList(topic));


        return result;
    }

}
