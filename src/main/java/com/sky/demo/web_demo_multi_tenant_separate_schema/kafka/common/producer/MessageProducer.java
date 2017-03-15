package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.common.producer;

import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by rg on 25/10/2016.
 */
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);


    private static Properties properties = null;
    private static Producer<String, String> producer = null;
    static {
        try {
            properties = new Properties();
            properties.put("bootstrap.servers", AppConfig.getItem("kafka.bootstrap.servers"));
            properties.put(ProducerConfig.ACKS_CONFIG, "all");       //ack方式，all，会等所有的commit，最慢的方式
            properties.put(ProducerConfig.RETRIES_CONFIG, 0);        //失败是否重试，设置会有可能产生重复数据
            properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); //对于每个partition的batch buffer大小
            properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);      //等多久，如果buffer没满，比如设为1，即消息发送会多1ms的延迟，如果buffer没满
            properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); //整个producer可以用于buffer的内存大小
            properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760); //max request size

            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            producer = new KafkaProducer<>(properties);     // if config error, this constructor will throw exception
        } catch (Exception e) {
            logger.error("init message producer error", e);
        }
    }


    //singleton
    private static MessageProducer INSTANCE = new MessageProducer();

    private MessageProducer() {

    }

    public static MessageProducer getInstance() {
        return INSTANCE;
    }



    public boolean send(String topic, List<String> values) {
        boolean result = false;

        try {
            for (String value : values) {
                Future<RecordMetadata> recordMetadataFuture = producer.send(new ProducerRecord<String, String>(topic, value));
            }

            result = true;
        } catch (Exception e) {
            logger.error("send failed", e);
            result = false;
        } finally {
//            producer.close();
        }

        return result;
    }

    public void sendWithCallBack(String topic, List<String> values) {

        try {
            for (String value : values) {
                Future<RecordMetadata> recordMetadataFuture = producer.send(new ProducerRecord<String, String>(topic, value), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            logger.error("send failed, topic:{}, value:{}, offset:{}", topic, value, metadata.offset());
                        } else {
                            logger.info("send success, topic:{}, offset:{}", topic, metadata.offset());
                        }
                    }
                });
            }
        } catch (Exception e) {
            logger.error("send failed", e);
        } finally {
//            producer.close();
        }

    }

}
