package com.sky.demo.web_demo_multi_tenant_separate_schema.notification.dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 17/3/18.
 */
public abstract class BaseNotificationDispatcher implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BaseNotificationDispatcher.class);

    private volatile boolean terminate = false;

    /**
     * stop
     */
    public void terminate() {
        this.terminate = true;
    }


    protected abstract boolean preExecute();

    protected abstract void doExecute();

    protected abstract void postExecute();

    @Override
    public void run() {

        do {

            try {
                if (preExecute()) {
                    doExecute();
                }
                postExecute();
            } catch (Exception e) {
                logger.error("dispatch error", e);
            }
        } while (!terminate);

    }
}
