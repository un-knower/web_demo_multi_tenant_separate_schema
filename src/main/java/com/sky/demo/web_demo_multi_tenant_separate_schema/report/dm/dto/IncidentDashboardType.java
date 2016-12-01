/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentDashboardType {
    POLICY(1, "dlp_report_incident_dashboard_type_policy"),
    SOURCE(2, "dlp_report_incident_dashboard_type_source"),
    DESTINATION(3, "dlp_report_incident_dashboard_type_destination"),
    CHANNEL_TYPE(4, "dlp_report_incident_dashboard_type_channel"),
    SEVERITY_TYPE(5, "dlp_report_incident_dashboard_type_severity"),
    ACTION_TYPE(6, "dlp_report_incident_dashboard_type_action"),
    POLICY_GROUP(7, "dlp_report_incident_dashboard_type_policy_group"),
    TREND_SEVERITY_TYPE(8, "dlp_report_incident_dashboard_type_trend_severity"),
    TREND_POLICY_DETECT_TIME(9, "dlp_report_incident_dashboard_type_trend_policy_detect_time"),
    FILE_SENSITIVE(10, "dlp_report_incident_dashboard_type_file_sensitive"),
    DATABASE_SENSITIVE(11, "dlp_report_incident_dashboard_type_database_sensitive"),
    RESOURCE_TYPE(12, "dlp_report_incident_dashboard_type_resource"),
    TASK(13, "dlp_report_incident_dashboard_type_task"),
    DISCOVERY_STATISTICS(14, "dlp_report_incident_dashboard_type_discovery_statistics"),
    DISCOVERY_TASK_WITH_POLICY_STATISTICS(15, "dlp_report_incident_dashboard_type_discovery_task_with_policy_statistics"),
    ENDPOINT_CORPORATE_TYPE(16, "dlp_report_incident_dashboard_type_endpoint_corporate_type"),
    ENDPOINT_DEVICE_TYPE(17, "dlp_report_incident_dashboard_type_endpoint_device_type"),
    ENDPOINT_OPERATION_SYSTEM(18, "dlp_report_incident_dashboard_type_endpoint_operation_system"),
    ENDPOINT_REGISTER_DEVICE_TYPE(19, "dlp_report_incident_dashboard_type_endpoint_register_device_type"),
    STATUS_TYPE(20, "dlp_report_incident_dashboard_type_status"),
    ALL_NETWORK_DASHBOARD(1001, "dlp_report_incident_dashboard_type_all_network_dashboard"),
    ALL_NETWORK_TREND(1002, "dlp_report_incident_dashboard_type_all_network_trend"),
    ALL_DISCOVERY_DASHBOARD(1003, "dlp_report_incident_dashboard_type_all_discovery_dashboard"),
    ALL_ENDPOINT_DASHBOARD(1004, "dlp_report_incident_dashboard_type_all_endpoint_dashboard"),
    ALL_ENDPOINT_TREND(1005, "dlp_report_incident_dashboard_type_all_endpoint_trend");
    
    private int code;
    private String desc;
//    public static final List<IncidentDashboardType> NETWORK_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> NETWORK_TREND_TYPES;
//    public static final List<IncidentDashboardType> DISCOVERY_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> ENDPOINT_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> ENDPOINT_TREND_TYPES;
//    public static final List<IncidentDashboardType> NETWORK_DASHBOARD_LISTS;
//    public static final List<IncidentDashboardType> NETWORK_TREND_LISTS;
//    public static final List<IncidentDashboardType> DISCOVERY_DASHBOARD_LISTS;
//    public static final List<IncidentDashboardType> ENDPOINT_DASHBOARD_LISTS;
//    public static final List<IncidentDashboardType> ENDPOINT_TREND_LISTS;
//    public static final List<IncidentDashboardType> NO_SHOW_LIMIT_TYPES;
//    public static final List<IncidentDashboardType> TOTAL_NETWORK_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> TOTAL_DISCOVERY_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> TOTAL_ENDPOINT_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> SUMMARY_NETWORK_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> SUMMARY_NETWORK_TREND_TYPES;
//    public static final List<IncidentDashboardType> SUMMARY_DISCOVERY_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> SUMMARY_ENDPOINT_DASHBOARD_TYPES;
//    public static final List<IncidentDashboardType> SUMMARY_ENDPOINT_TREND_TYPES;
//    public static final List<IncidentDashboardType> NETWORK_SUMMAY_FIRST_DIM_TYPE_LIST;
//    public static final List<IncidentDashboardType> DISCOVERY_SUMMAY_FIRST_DIM_TYPE_LIST;

    private IncidentDashboardType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDisplayName() {
        return Localizer.getMessage((String) this.getDesc());
    }

    public static IncidentDashboardType getIncidentDashboardTypeByCode(int code) {
        for (IncidentDashboardType type : IncidentDashboardType.values()) {
            if (type.getCode() != code) continue;
            return type;
        }
        return null;
    }

//    static {
//        NETWORK_DASHBOARD_TYPES = ImmutableList.of(POLICY, SOURCE, DESTINATION, CHANNEL_TYPE), SEVERITY_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE));
//        NETWORK_TREND_TYPES = ImmutableList.of(TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME));
//        DISCOVERY_DASHBOARD_TYPES = ImmutableList.of(POLICY), RESOURCE_TYPE), SEVERITY_TYPE), TASK), DISCOVERY_STATISTICS), STATUS_TYPE));
//        ENDPOINT_DASHBOARD_TYPES = ImmutableList.of(POLICY), SOURCE), DESTINATION), CHANNEL_TYPE), SEVERITY_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE), ENDPOINT_CORPORATE_TYPE), ENDPOINT_DEVICE_TYPE), ENDPOINT_OPERATION_SYSTEM), ENDPOINT_REGISTER_DEVICE_TYPE), (Object[])new IncidentDashboardType[0]);
//        ENDPOINT_TREND_TYPES = ImmutableList.of(TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME));
//        NETWORK_DASHBOARD_LISTS = ImmutableList.of(POLICY), SOURCE), DESTINATION), CHANNEL_TYPE), SEVERITY_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE), ALL_NETWORK_DASHBOARD));
//        NETWORK_TREND_LISTS = ImmutableList.of(TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME), ALL_NETWORK_TREND));
//        DISCOVERY_DASHBOARD_LISTS = ImmutableList.of(POLICY), RESOURCE_TYPE), SEVERITY_TYPE), TASK), DISCOVERY_STATISTICS), STATUS_TYPE), DISCOVERY_TASK_WITH_POLICY_STATISTICS), ALL_DISCOVERY_DASHBOARD));
//        ENDPOINT_DASHBOARD_LISTS = ImmutableList.of(POLICY), SOURCE), DESTINATION), CHANNEL_TYPE), SEVERITY_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE), ENDPOINT_CORPORATE_TYPE), ENDPOINT_DEVICE_TYPE), ENDPOINT_OPERATION_SYSTEM), ENDPOINT_REGISTER_DEVICE_TYPE), (Object[])new IncidentDashboardType[]{ALL_ENDPOINT_DASHBOARD});
//        ENDPOINT_TREND_LISTS = ImmutableList.of(TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME), ALL_ENDPOINT_TREND));
//        NO_SHOW_LIMIT_TYPES = ImmutableList.of(SEVERITY_TYPE), ACTION_TYPE), STATUS_TYPE), ENDPOINT_CORPORATE_TYPE), ENDPOINT_DEVICE_TYPE));
//        TOTAL_NETWORK_DASHBOARD_TYPES = Lists.newArrayList();
//        TOTAL_DISCOVERY_DASHBOARD_TYPES = Lists.newArrayList();
//        TOTAL_ENDPOINT_DASHBOARD_TYPES = Lists.newArrayList();
//        SUMMARY_NETWORK_DASHBOARD_TYPES = ImmutableList.of(POLICY), SOURCE), DESTINATION), CHANNEL_TYPE), SEVERITY_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE));
//        SUMMARY_NETWORK_TREND_TYPES = ImmutableList.of(TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME));
//        SUMMARY_DISCOVERY_DASHBOARD_TYPES = ImmutableList.of(POLICY), RESOURCE_TYPE), SEVERITY_TYPE), TASK), STATUS_TYPE));
//        SUMMARY_ENDPOINT_DASHBOARD_TYPES = ImmutableList.of(POLICY), SOURCE), DESTINATION), CHANNEL_TYPE), SEVERITY_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE), ENDPOINT_CORPORATE_TYPE), ENDPOINT_DEVICE_TYPE), ENDPOINT_OPERATION_SYSTEM));
//        SUMMARY_ENDPOINT_TREND_TYPES = ImmutableList.of(TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME));
//        TOTAL_NETWORK_DASHBOARD_TYPES.addAll(SUMMARY_NETWORK_DASHBOARD_TYPES);
//        TOTAL_NETWORK_DASHBOARD_TYPES.addAll(SUMMARY_NETWORK_TREND_TYPES);
//        TOTAL_DISCOVERY_DASHBOARD_TYPES.addAll(SUMMARY_DISCOVERY_DASHBOARD_TYPES);
//        TOTAL_ENDPOINT_DASHBOARD_TYPES.addAll(SUMMARY_ENDPOINT_DASHBOARD_TYPES);
//        TOTAL_ENDPOINT_DASHBOARD_TYPES.addAll(SUMMARY_ENDPOINT_TREND_TYPES);
//        NETWORK_SUMMAY_FIRST_DIM_TYPE_LIST = ImmutableList.of(POLICY), SOURCE), DESTINATION), CHANNEL_TYPE), ACTION_TYPE), POLICY_GROUP), STATUS_TYPE), TREND_SEVERITY_TYPE), TREND_POLICY_DETECT_TIME));
//        DISCOVERY_SUMMAY_FIRST_DIM_TYPE_LIST = ImmutableList.of(POLICY), TASK));
//    }
}

