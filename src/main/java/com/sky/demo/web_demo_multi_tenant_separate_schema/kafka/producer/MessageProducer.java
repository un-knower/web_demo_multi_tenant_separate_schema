package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.producer;

import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by rg on 25/10/2016.
 */
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);


    private static Properties props;
    static {
        props = new Properties();
        props.put("bootstrap.servers", AppConfig.getItem("kafka.bootstrap.servers"));
        props.put("acks", "all"); //ack方式，all，会等所有的commit最慢的方式
        props.put("retries", 0); //失败是否重试，设置会有可能产生重复数据
        props.put("batch.size", 16384); //对于每个partition的batch buffer大小
        props.put("linger.ms", 1);  //等多久，如果buffer没满，比如设为1，即消息发送会多1ms的延迟，如果buffer没满
        props.put("buffer.memory", 33554432); //整个producer可以用于buffer的内存大小
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    }

    private static Producer<String, String> producer = new KafkaProducer<>(props);



    //singleton
    private static MessageProducer INSTANCE = new MessageProducer();

    private MessageProducer() {

    }

    public static MessageProducer getInstance() {
        return INSTANCE;
    }



    public boolean send(String topic, String value) {
        boolean result = false;

        try {

            producer.send(new ProducerRecord<String, String>(topic, value));

            result = true;
        } catch (Exception e) {
            logger.error("send failed", e);
            result = false;
        } finally {
        }

        return result;
    }

}
