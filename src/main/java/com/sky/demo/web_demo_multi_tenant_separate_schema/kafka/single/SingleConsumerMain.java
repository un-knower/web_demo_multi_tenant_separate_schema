package com.sky.demo.web_demo_multi_tenant_separate_schema.kafka.single;

/**
 * Created by user on 16/11/17.
 */
public class SingleConsumerMain {


    public static void main(String[] args) {

        String brokers = "localhost:9092";
        String groupId = "group01";
        String topic = "HelloKafkaTopic";
        int numberOfThread = 3;

        if (args != null && args.length > 4) {
            brokers = args[0];
            groupId = args[1];
            topic = args[2];
            numberOfThread = Integer.parseInt(args[3]);
        }

        // Start Notification Producer Thread
        ProducerThread producerThread = new ProducerThread(brokers, topic);
        Thread t1 = new Thread(producerThread);
        t1.start();

        // Start group of Notification Consumer Thread
        NotificationConsumer consumers = new NotificationConsumer(brokers, groupId, topic);

        consumers.execute(numberOfThread);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException ie) {

        }
        consumers.shutdown();
    }
}
