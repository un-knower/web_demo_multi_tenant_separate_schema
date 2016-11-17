package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.multiple;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by user on 16/11/17.
 */
public class NotificationProducerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NotificationProducerThread.class);

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public NotificationProducerThread(String brokers, String topic) {
        Properties prop = createProducerConfig(brokers);
        this.producer = new KafkaProducer<String, String>(prop);
        this.topic = topic;
    }

    private static Properties createProducerConfig(String brokers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    @Override
    public void run() {
        logger.info("Produces  messages");
        try {
            for (int i = 0; i < 5; i++) {
                String msg = "Message " + i;
                producer.send(new ProducerRecord<String, String>(topic, msg), new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e != null) {
                            logger.error("send message error", e);
                        }
                        logger.info("Sent:" + msg + ", Partition: " + metadata.partition() + ", Offset: "
                                + metadata.offset());
                    }
                });

            }
        } catch (Exception e) {
            logger.error("send message error", e);
        } finally {
            // closes producer
            producer.close();
        }

    }
}
