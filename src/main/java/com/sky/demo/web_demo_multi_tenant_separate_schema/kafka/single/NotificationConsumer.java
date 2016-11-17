package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.single;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16/11/17.
 * Model #2: Single consumer, multiple worker processing threads
 */
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    private final KafkaConsumer<String, String> consumer;
    private final String topic;
    // ThreadPool of consumers
    private ExecutorService executor;

    public NotificationConsumer(String brokers, String groupId, String topic) {
        Properties prop = createConsumerConfig(brokers, groupId);
        this.consumer = new KafkaConsumer<>(prop);
        this.topic = topic;
        this.consumer.subscribe(Arrays.asList(this.topic));
    }

    public void execute(int numberOfThreads) {

        executor = new ThreadPoolExecutor(numberOfThreads, numberOfThreads, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());

        while (true) {
            //single consumer
            ConsumerRecords<String, String> records = consumer.poll(100);

            //multi worker to process message
            for (final ConsumerRecord record : records) {
                executor.submit(new ConsumerThreadHandler(record));
            }
        }
    }

    private static Properties createConsumerConfig(String brokers, String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executor != null) {
            executor.shutdown();
        }
        try {
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                logger.warn("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            logger.error("Interrupted during shutdown, exiting uncleanly", e);
        }
    }
}
