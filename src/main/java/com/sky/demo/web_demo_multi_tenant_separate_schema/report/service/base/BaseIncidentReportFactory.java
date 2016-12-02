/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.skyguard.sps.dlp.common.report.dm.IncidentType
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.service.base;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseIncidentReportFactory {

    private static final Logger logger = LoggerFactory.getLogger(BaseIncidentReportFactory.class);

    @Resource
    private BaseNetworkIncidentReportService baseNetworkIncidentReportService;
    @Resource
    private BaseDiscoveryIncidentReportService baseDiscoveryIncidentReportService;
    @Resource
    private BaseEndpointIncidentReportService baseEndpointIncidentReportService;

    public IBaseIncidentReportService getBaseIncidentReportService(IncidentType incidentType) {
        Preconditions.checkNotNull((Object)incidentType, (Object)"incident type is null!");
        IBaseIncidentReportService baseIncidentReportService = null;
        if (IncidentType.NETWORK.equals((Object)incidentType)) {
            baseIncidentReportService = this.baseNetworkIncidentReportService;
        } else if (IncidentType.DISCOVERY.equals((Object)incidentType)) {
            baseIncidentReportService = this.baseDiscoveryIncidentReportService;
        } else if (IncidentType.ENDPOINT.equals((Object)incidentType)) {
            baseIncidentReportService = this.baseEndpointIncidentReportService;
        }
        return baseIncidentReportService;
    }
}

