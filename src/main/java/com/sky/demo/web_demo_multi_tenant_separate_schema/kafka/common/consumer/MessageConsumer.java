package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.common.consumer;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Created by rg on 25/10/2016.
 * 注：此Consumer不支持多线程
 */
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);


    private static Properties properties = null;
    static {
        properties = new Properties();
        properties.put("bootstrap.servers", AppConfig.getItem("kafka.bootstrap.servers"));
        properties.put("group.id", "test8");
        properties.put("enable.auto.commit", AppConfig.getItem("kafka.enable.auto.commit")); //是否自动commit
        properties.put("auto.commit.interval.ms", 1000);     //定时commit的周期
        properties.put("max.poll.records", 10);              //poll max size

        //设置使用最开始的offset偏移量为该group.id的earliest。如果不设置，则会是latest，即该topic最新一个消息的offset
        //如果采用latest，消费者只能得到其启动后，生产者生产的消息
        properties.put("auto.offset.reset", "earliest");     //latest, earliest, none

        properties.put("session.timeout.ms", 30000);         //consumer活性超时时间

        //fetch.message.max.bytes is old consumer config(0.8)
        //properties.put("fetch.message.max.bytes", 10 * 1024 * 1024);       //consumer fetch max size  10MB
        properties.put("max.partition.fetch.bytes", 10 * 1024 * 1024);       //The maximum amount of data per-partition the server will return

        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    }

    private KafkaConsumer<String, String> consumer;


    // singleton
    private static MessageConsumer INSTANCE = new MessageConsumer();

    private MessageConsumer() {
        this.consumer = new KafkaConsumer<String, String>(properties);
    }

    public static MessageConsumer getInstance() {
        return INSTANCE;
    }


    /**
     * properties : enable.auto.commit = true
     * @param topic
     * @return
     */
    public List<ConsumerRecord<String, String>> fetch(String topic) {
        List<ConsumerRecord<String, String>> result = Lists.newArrayList();

        try {
            consumer.subscribe(Lists.newArrayList(topic));      // only one topic

            long timeout = 1000;
            ConsumerRecords<String, String> consumerRecords = consumer.poll(timeout);
            logger.info("   =====> polled size :{}, topic : {}", consumerRecords.count(), topic);

            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                result.add(consumerRecord);
            }

        } catch (Exception e) {
            logger.error("poll message failed", e);
        } finally {
            //consumer.close();
        }

        return result;
    }



    /**
     * properties : enable.auto.commit = true
     * @param topic
     * @param size
     * @return
     */
    public List<ConsumerRecord<String, String>> fetch(String topic, int size) {
        List<ConsumerRecord<String, String>> result = Lists.newArrayList();

        int consumedSize = 0;
        try {
            consumer.subscribe(Lists.newArrayList(topic));      // only one topic

            do {
                long timeout = 0;
                ConsumerRecords<String, String> consumerRecords = consumer.poll(timeout);
                logger.info("   =====> polled size :{}, topic : {}", consumerRecords.count(), topic);

                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    result.add(consumerRecord);
                }

                consumedSize += consumerRecords.count();
                Thread.sleep(1000);

            } while(consumedSize < size);
        } catch (Exception e) {
            logger.error("poll message failed", e);
        } finally {
            //consumer.close();
        }

        return result;
    }


    /**
     * properties : enable.auto.commit = false
     * @param topic
     * @param size
     * @return
     */
    public List<ConsumerRecord<String, String>> fetchByManualCommit(String topic, int size) {
        List<ConsumerRecord<String, String>> result = Lists.newArrayList();

        int consumedSize = 0;
        int minBatchSize = 10;
        try {
            consumer.subscribe(Lists.newArrayList(topic));      // only one topic

            do {
                List<ConsumerRecord<String, String>> buffer = Lists.newArrayList();

                long timeout = 0;
                ConsumerRecords<String, String> consumerRecords = consumer.poll(timeout);
                logger.info("   =====> poll size :{}, topic : {}", consumerRecords.count(), topic);

                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    result.add(consumerRecord);
                    buffer.add(consumerRecord);

                }

                if (buffer.size() >= minBatchSize) {
                    consumer.commitSync();  //手工sync offset
                    buffer.clear();
                }

                consumedSize += consumerRecords.count();

            } while (consumedSize < size);
        } catch (Exception e) {
            logger.error("poll message failed", e);
        } finally {
//            consumer.close();
        }

        return result;
    }

}
