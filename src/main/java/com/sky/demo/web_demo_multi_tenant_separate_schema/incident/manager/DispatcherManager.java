package com.sky.demo.web_demo_multi_tenant_separate_schema.incident.manager;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.incident.dispatcher.EvidenceDispatcher;
import com.sky.demo.web_demo_multi_tenant_separate_schema.incident.dispatcher.IncidentDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.nio.ch.sctp.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 17/3/18.
 */
@Component
public class DispatcherManager {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherManager.class);

    private static final int INCIDENT_DISPATCH_EXECUTOR_SIZE = 3;

    private ExecutorService incidentDispatchExecutor = null;
    private ExecutorService evidenceDispatchExecutor = null;
    private ExecutorService multiDispatchExecutor = null;


    private IncidentDispatcher incidentDispatcher;
    private EvidenceDispatcher evidenceDispatcher;

    private ImmutableList<IncidentDispatcher> multiDispatchers; //if has multi dispatcher


    @Value("${dispatch.switch}")
    private boolean dispatchSwitch;



    @PostConstruct
    public void init() {
        logger.info("init dispatcher manager....  dispatchSwitch={}", dispatchSwitch);
        if (dispatchSwitch) {
            //init Executors
            incidentDispatchExecutor = Executors.newSingleThreadExecutor();
            evidenceDispatchExecutor = Executors.newSingleThreadExecutor();
            multiDispatchExecutor = Executors.newFixedThreadPool(INCIDENT_DISPATCH_EXECUTOR_SIZE);


            //init dispatcher
            incidentDispatcher = getIncidentDispatcher();
            evidenceDispatcher = getEvidenceDispatcher();
            multiDispatchers = ImmutableList.of(getIncidentDispatcher(), getIncidentDispatcher(), getIncidentDispatcher());


            //start all
            incidentDispatchExecutor.submit(incidentDispatcher);
            evidenceDispatchExecutor.submit(evidenceDispatcher);
            multiDispatchers.forEach( dispatcher -> multiDispatchExecutor.submit(dispatcher));

        }
    }


    @PreDestroy
    public void destroy() {
        logger.info("destroy dispatcher manager....");
        if (dispatchSwitch) {
            terminateAllDispatchers();
            shutdownAllExecutors();
        }
    }

    private void terminateAllDispatchers() {
        incidentDispatcher.termiante();
        evidenceDispatcher.termiante();
        multiDispatchers.forEach(dispatcher -> dispatcher.termiante());
    }

    private void shutdownAllExecutors() {
        incidentDispatchExecutor.shutdown();
        evidenceDispatchExecutor.shutdown();
        multiDispatchExecutor.shutdown();
    }


    @Lookup("incidentDispatcher")
    protected IncidentDispatcher getIncidentDispatcher() {return null;}

    @Lookup("evidenceDispatcher")
    protected EvidenceDispatcher getEvidenceDispatcher() {return null;}
}
