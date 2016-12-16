package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.common.producer;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 16/11/11.
 */
public class KafkaNotifier {

    private static final Logger logger = LoggerFactory.getLogger(KafkaNotifier.class);

    private static ExecutorService service = Executors.newFixedThreadPool(10);


    /**
     * async send message
     * @param topic
     * @param values
     */
    public static void send(String topic, List<String> values) {

        if (CollectionUtils.isNotEmpty(values)) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    MessageProducer.getInstance().send(topic, values);
                }
            });
        }
    }

}
