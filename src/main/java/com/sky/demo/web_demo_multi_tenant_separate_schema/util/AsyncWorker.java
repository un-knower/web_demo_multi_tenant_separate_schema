package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 8/2/15.
 */
public class AsyncWorker {

    private static final Logger logger = LoggerFactory.getLogger(AsyncWorker.class);

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int MAX_QUEUE_SIZE = 100;
    private static final int KEEP_ALIVE_TIME = 60;

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE),
            new ThreadFactoryBuilder().setNameFormat("AsyncWorkerThread-%d").build(),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    logger.error("async work thread reject");
                }
            });

    public static void execute(final Runnable runnable) {
        executor.execute(runnable);
    }

    public static<T> Future<T> submit(final Callable<T> callable) {
        return executor.submit(callable);
    }

    public static void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }


}
