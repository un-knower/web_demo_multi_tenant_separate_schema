package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.producer;

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


    private static Properties props;
    static {
        props = new Properties();
        props.put("bootstrap.servers", AppConfig.getItem("kafka.bootstrap.servers"));
        props.put(ProducerConfig.ACKS_CONFIG, "all");       //ack方式，all，会等所有的commit，最慢的方式
        props.put(ProducerConfig.RETRIES_CONFIG, 0);        //失败是否重试，设置会有可能产生重复数据
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); //对于每个partition的batch buffer大小
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);      //等多久，如果buffer没满，比如设为1，即消息发送会多1ms的延迟，如果buffer没满
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); //整个producer可以用于buffer的内存大小
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

    }

    private static Producer<String, String> producer = new KafkaProducer<>(props);



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
                producer.send(new ProducerRecord<String, String>(topic, value));
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
                            logger.info("send success");
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
