package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.manager;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.sky.demo.web_demo_multi_tenant_separate_schema.notification.dispatcher.EndpointNotificationDispatcher;
import com.sky.demo.web_demo_multi_tenant_separate_schema.notification.dispatcher.NetworkNotificationDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * Created by user on 17/3/18.
 */
@Component
public class NotificationDispatcherManager {

    private static final Logger logger = LoggerFactory.getLogger(NotificationDispatcherManager.class);


    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int MAX_QUEUE_SIZE = 100;
    private static final int KEEP_ALIVE_TIME = 60;




    private ListeningExecutorService networkExecutor;
    private ListeningExecutorService endpointExecutor;


    private NetworkNotificationDispatcher networkNotificationDispatcher;
    private EndpointNotificationDispatcher endpointNotificationDispatcher;

    @Value("${notification.switch}")
    private String notificationSwitch;


    @PostConstruct
    public void init() {
        logger.info("init notification dispatch manager... switch={}", Boolean.valueOf(notificationSwitch));
        if (Boolean.valueOf(notificationSwitch)) {
            //init executor
            networkExecutor = MoreExecutors.listeningDecorator(
                    new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                            new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE),
                            new RejectedExecutionHandler() {
                                @Override
                                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                    logger.error("network notification reject dispatch...");
                                }
                            })
            );

//            endpointExecutor = MoreExecutors.listeningDecorator(
//                    new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
//                            new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE),
//                            new RejectedExecutionHandler() {
//                                @Override
//                                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                                    logger.error("endpoint notification reject dispatch...");
//                                }
//                            })
//            );

            endpointExecutor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(CORE_POOL_SIZE));

            //init dispatcher
            networkNotificationDispatcher = getNetworkNotificationDispatcher();
            endpointNotificationDispatcher = getEndpointNotificationDispatcher();

            //start
            networkExecutor.submit(networkNotificationDispatcher);
            for (int i = 0;i < CORE_POOL_SIZE;i++) {
                endpointExecutor.submit(endpointNotificationDispatcher);
            }

        }

    }


    @PreDestroy
    public void destroy() {

        if (Boolean.valueOf(notificationSwitch)) {
            terminateAllDispatchers();
            shutdownAllExecutors();
        }

    }

    private void terminateAllDispatchers() {


    }

    private void shutdownAllExecutors() {



    }


    @Lookup
    public NetworkNotificationDispatcher getNetworkNotificationDispatcher() {
        return null;
    }

    @Lookup
    public EndpointNotificationDispatcher getEndpointNotificationDispatcher() {
        return null;
    }


}
