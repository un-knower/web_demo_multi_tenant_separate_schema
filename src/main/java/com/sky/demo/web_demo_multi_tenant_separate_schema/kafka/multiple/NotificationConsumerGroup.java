package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.multiple;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by user on 16/11/17.
 */
public class NotificationConsumerGroup {

    private final String groupId;       //All consumers on this group will have the same groupId
    private final String topic;         //The topic to which the consumers group will fetch the data
    private final String brokers;       //The Kafka brokers to which consumers group will connect
    private final int numberOfConsumers;
    private List<NotificationConsumerThread> consumers;

    public NotificationConsumerGroup(String brokers, String groupId, String topic, int numberOfConsumers) {
        this.brokers = brokers;
        this.topic = topic;
        this.groupId = groupId;
        this.numberOfConsumers = numberOfConsumers;

        consumers = Lists.newArrayList();
        for (int i = 0; i < this.numberOfConsumers; i++) {
            NotificationConsumerThread ncThread = new NotificationConsumerThread(this.brokers, this.groupId, this.topic);
            consumers.add(ncThread);
        }
    }

    public void execute() {
        consumers.forEach(consumer -> {
            Thread thread = new Thread(consumer);
            thread.start();
        });
    }

    /**
     * @return the numberOfConsumers
     */
    public int getNumberOfConsumers() {
        return numberOfConsumers;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }
}
