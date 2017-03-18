package com.sky.demo.web_demo_multi_tenant_separate_schema.incident.dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 17/3/18.
 * 将任务封装到单个线程中执行
 */
public abstract class BaseDispatcher implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BaseDispatcher.class);


    private volatile boolean terminateSingnal = false;  //中断标志  volatile


    /**
     * 中断
     */
    public void termiante() {
        this.terminateSingnal = true;
    }


    /**
     * 预处理
     * @return
     */
    protected abstract boolean preExecuteDispatcher() throws Exception;

    /**
     * 执行
     */
    protected abstract void doExecuteDispatcher() throws Exception;

    /**
     * 收尾
     */
    protected abstract void postExecuteDispatcher() throws Exception;


    @Override
    public void run() {

        do {

            try {
                if (preExecuteDispatcher()) {

                    doExecuteDispatcher();
                }

                postExecuteDispatcher();
            } catch (Exception e) {
                logger.error("execute dispatcher error", e);
            }

        } while (!terminateSingnal);
    }
}
