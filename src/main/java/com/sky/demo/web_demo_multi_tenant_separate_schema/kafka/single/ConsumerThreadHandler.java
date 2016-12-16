package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.single;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 16/11/17.
 */
public class ConsumerThreadHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerThreadHandler.class);

    private ConsumerRecord consumerRecord;

    public ConsumerThreadHandler(ConsumerRecord consumerRecord) {
        this.consumerRecord = consumerRecord;
    }

    public void run() {
        logger.info("Process: " + consumerRecord.value()
                + ", Offset: " + consumerRecord.offset()
                + ", By ThreadID: " + Thread.currentThread().getId());
    }
}
