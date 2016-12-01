/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentReportFilterType {
    INCIDENT_ID(1, "dlp_incident_report_filter_type_incident_id"),
    TRANSACTION_ID(2, "dlp_incident_report_filter_type_transaction_id"),
    TIME(3, "dlp_incident_report_filter_type_time"),
    POLICY(4, "dlp_incident_report_filter_type_policy"),
    SEVERITY(5, "dlp_incident_report_filter_type_severity"),
    STATUS(6, "dlp_incident_report_filter_type_status"),
    MATCH(7, "dlp_incident_report_filter_type_match"),
    IGNORE(8, "dlp_incident_report_filter_type_ignore"),
    DETECT_ENGINE(9, "dlp_incident_report_filter_type_detect_engine"),
    CHANNEL(10, "dlp_incident_report_filter_type_channel"),
    SOURCE(11, "dlp_incident_report_filter_type_source"),
    DESTINATION(12, "dlp_incident_report_filter_type_destination"),
    RELEASE(13, "dlp_incident_report_filter_type_release"),
    ACTION(14, "dlp_incident_report_filter_type_action"),
    TRANSACTION_SIZE(15, "dlp_incident_report_filter_type_transaction_size"),
    FILE_SIZE(16, "dlp_incident_report_filter_type_file_size"),
    RESOURCE_TYPE(17, "dlp_incident_report_filter_type_resource_type"),
    TASK(18, "dlp_incident_report_filter_type_task"),
    LOCK(19, "dlp_incident_report_filter_type_lock"),
    INCIDENT_TIME(20, "dlp_incident_report_filter_type_incident_time"),
    INCIDENT_TAG(21, "dlp_incident_report_filter_type_incident_tag"),
    FILE_NAME(22, "dlp_incident_report_filter_type_file_name"),
    ANALYZE_ENGINE(23, "dlp_incident_report_filter_type_analyze_engine"),
    IRREGULAR_CONTENT(24, "dlp_incident_report_filter_type_irregular_content"),
    DETAIL_INFO(25, "dlp_incident_report_filter_type_detail_info"),
    WORK_MODE(26, "dlp_incident_report_filter_type_work_mode"),
    FILE_PATH(27, "dlp_incident_report_filter_type_file_path"),
    FOLDER_PATH(28, "dlp_incident_report_filter_type_folder_path"),
    FILE_EXTENSION(29, "dlp_incident_report_filter_type_file_extension"),
    FILE_OWNER(30, "dlp_incident_report_filter_type_file_owner"),
    FOLDER_OWNER(31, "dlp_incident_report_filter_type_folder_owner"),
    HOST_NAME(32, "dlp_incident_report_filter_type_host_name"),
    IP_ADDRESS(33, "dlp_incident_report_filter_type_ip_address"),
    CORPORATE_TYPE(34, "dlp_incident_report_filter_type_corporate_type"),
    DEVICE_TYPE(35, "dlp_incident_report_filter_type_device_type"),
    OPERATION_SYSTEM(36, "dlp_incident_report_filter_type_operation_system");
    
    private int code;
    private String desc;
    private String name;
//    public static final List<IncidentReportFilterType> NETWORK_INCIDENT_REPORT_FILTERS;
//    public static final List<IncidentReportFilterType> NETWORK_INCIDENT_REPORT_FILTERS_TREND;
//    public static final List<IncidentReportFilterType> DISCOVERY_INCIDENT_REPORT_FILTERS;
//    public static final List<IncidentReportFilterType> ENDPOINT_INCIDENT_REPORT_FILTERS;
//    public static final List<IncidentReportFilterType> ENDPOINT_INCIDENT_REPORT_FILTERS_TREND;

    private IncidentReportFilterType(int code, String desc) {
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

    public String getName() {
        return Localizer.getMessage((String) this.getDesc());
    }

    public void setName(String name) {
        this.name = name;
    }

    public static IncidentReportFilterType getIncidentReportFilterTypeByCode(int code) {
        for (IncidentReportFilterType filterType : IncidentReportFilterType.values()) {
            if (filterType.getCode() != code) continue;
            return filterType;
        }
        return null;
    }

//    static {
//        NETWORK_INCIDENT_REPORT_FILTERS = ImmutableList.of((Object)((Object)INCIDENT_ID), (Object)((Object)TRANSACTION_ID), (Object)((Object)INCIDENT_TIME), (Object)((Object)TIME), (Object)((Object)SOURCE), (Object)((Object)DESTINATION), (Object)((Object)POLICY), (Object)((Object)CHANNEL), (Object)((Object)ACTION), (Object)((Object)STATUS), (Object)((Object)SEVERITY), (Object)((Object)MATCH), (Object[])new IncidentReportFilterType[]{FILE_NAME, TRANSACTION_SIZE, DETECT_ENGINE, ANALYZE_ENGINE, RELEASE, DETAIL_INFO, INCIDENT_TAG, IGNORE, IRREGULAR_CONTENT, WORK_MODE});
//        NETWORK_INCIDENT_REPORT_FILTERS_TREND = ImmutableList.of((Object)((Object)TIME));
//        DISCOVERY_INCIDENT_REPORT_FILTERS = ImmutableList.of((Object)((Object)INCIDENT_ID), (Object)((Object)TRANSACTION_ID), (Object)((Object)INCIDENT_TIME), (Object)((Object)TIME), (Object)((Object)TASK), (Object)((Object)POLICY), (Object)((Object)STATUS), (Object)((Object)SEVERITY), (Object)((Object)MATCH), (Object)((Object)HOST_NAME), (Object)((Object)IP_ADDRESS), (Object)((Object)FILE_PATH), (Object[])new IncidentReportFilterType[]{FILE_NAME, FOLDER_PATH, FILE_SIZE, FILE_OWNER, FOLDER_OWNER, FILE_EXTENSION, TRANSACTION_SIZE, DETECT_ENGINE, ANALYZE_ENGINE, INCIDENT_TAG, IGNORE, IRREGULAR_CONTENT, LOCK, RESOURCE_TYPE});
//        ENDPOINT_INCIDENT_REPORT_FILTERS = ImmutableList.of((Object)((Object)INCIDENT_ID), (Object)((Object)TRANSACTION_ID), (Object)((Object)INCIDENT_TIME), (Object)((Object)TIME), (Object)((Object)SOURCE), (Object)((Object)DESTINATION), (Object)((Object)POLICY), (Object)((Object)CHANNEL), (Object)((Object)ACTION), (Object)((Object)STATUS), (Object)((Object)SEVERITY), (Object)((Object)MATCH), (Object[])new IncidentReportFilterType[]{FILE_NAME, TRANSACTION_SIZE, DETECT_ENGINE, ANALYZE_ENGINE, DETAIL_INFO, INCIDENT_TAG, IGNORE, IRREGULAR_CONTENT, HOST_NAME, CORPORATE_TYPE, DEVICE_TYPE, OPERATION_SYSTEM});
//        ENDPOINT_INCIDENT_REPORT_FILTERS_TREND = ImmutableList.of((Object)((Object)TIME));
//    }
}

