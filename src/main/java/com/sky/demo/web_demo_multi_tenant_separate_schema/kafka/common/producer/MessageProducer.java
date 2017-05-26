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

            //acks=0 意味着producer永远不会等待任何一个来自broker的ack，意味着不需要任何确认，发送即认为成功。
            //acks=1 意味着在leader replica已经接收到数据后，producer会得到一个ack，这个选项对速度与安全性做一个平衡，但是不需要等其他副本确认，如果发生leader挂了，其他副本还没来得及同步，这时就会发生数据丢失的情况。
            //acks=all 意味着在所有的ISR都接收到数据后，producer才得到一个ack。这个选项提供了最好的持久性，只要还有一个replica存活，那么数据就不会丢失，但是相应的吞吐量会受到影响。
            properties.put("acks", "all");           //ack方式，all，会等所有replica的commit，最慢的方式

            properties.put("retries", 0);            //设置的就是重试间隔时间。设置此项会有可能产生重复数据
            properties.put("batch.size", 16 * 1024); //对于每个partition的batch buffer大小
            properties.put("linger.ms", 1);          //等多久，如果buffer没满，比如设为1，即消息发送会多1ms的延迟，如果buffer没满
            properties.put("buffer.memory", 32 * 1024 * 1024);      //整个producer可以用于buffer的内存大小
            properties.put("max.request.size", 20 * 1024 * 1024);   //max request size

            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

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

//                recordMetadataFuture.get();   //获取执行结果，如果有异常，会有日志记录
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
