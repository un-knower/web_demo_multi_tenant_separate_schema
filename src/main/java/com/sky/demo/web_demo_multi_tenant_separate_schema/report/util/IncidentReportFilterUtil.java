/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.sky.sps.base.common.directory.dm.entry.BaseDirectoryEntry
 *  com.sky.sps.base.common.directory.dm.entry.DirectoryGroup
 *  com.sky.sps.base.common.directory.dm.entry.DirectoryUser
 *  com.sky.sps.base.server.directory.service.DirectoryLocalEntryService
 *  com.sky.sps.base.server.util.SpringUtil
 *  com.sky.sps.base.utils.common.SqlUtil
 *  com.sky.sps.base.utils.context.UserContext
 *  org.apache.commons.collections.CollectionUtils
 *  org.apache.commons.lang.StringUtils
 *  org.joda.time.DateTime
 *  org.joda.time.DateTime$Property
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.*;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SpringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class IncidentReportFilterUtil {
    private static final Logger logger = LoggerFactory.getLogger(IncidentReportFilterUtil.class);
    public static final String FILTER_LIMIT = "LIMIT";
    public static final String FILTER_OFFSET = "OFFSET";
    public static final String FILTER_ORDER_BY = "ORDER_BY";
    public static final String FILTER_GROUP_BY = "GROUP_BY";
    private static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FILTER_INCIDENT_ORDER_BY = "INCIDENT_ORDER_BY";
    public static final String FILTER_INCIDENT_GROUP_BY = "INCIDENT_GROUP_BY";
    public static final String COLUMN_SELECTED = "COLUMN_SELECTED";
    public static final String FILTER_KEY_INCIDENT_ID = "INCIDENT_ID";
    public static final String FILTER_KEY_TRANSACTION_ID = "TRANSACTION_ID";
    public static final String FILTER_KEY_TIME_BEGIN_DATE = "BEGIN_DATE";
    public static final String FILTER_KEY_TIME_END_DATE = "END_DATE";
    public static final String FILTER_KEY_TIME_POINT_FROM = "TIME_POINT_FROM";
    public static final String FILTER_KEY_TIME_POINT_TO = "TIME_POINT_TO";
    public static final String FILTER_KEY_POLICIES = "POLICIES";
    public static final String FILTER_KEY_SEVERITIES = "SEVERITIES";
    public static final String FILTER_KEY_STATUSES = "STATUSES";
    public static final String FILTER_KEY_MATCH_FROM = "MATCH_FROM";
    public static final String FILTER_KEY_MATCH_TO = "MATCH_TO";
    public static final String FILTER_KEY_IGNORE = "IGNORE";
    public static final String FILTER_KEY_DETECT_ENGINES = "DETECT_ENGINES";
    public static final String FILTER_KEY_CHANNELS = "CHANNELS";
    public static final String FILTER_KEY_SOURCES = "SOURCES";
    public static final String FILTER_KEY_CUSTOM_SOURCES = "CUSTOM_SOURCES";
    public static final String FILTER_KEY_DESTINATIONS = "DESTINATIONS";
    public static final String FILTER_KEY_RELEASE = "RELEASE";
    public static final String FILTER_KEY_ACTIONS = "ACTIONS";
    public static final String FILTER_KEY_TRANSACTION_SIZE_FROM = "TRANSACTION_SIZE_FROM";
    public static final String FILTER_KEY_TRANSACTION_SIZE_TO = "TRANSACTION_SIZE_TO";
    public static final String FILTER_KEY_FILE_SIZE_FROM = "FILE_SIZE_FROM";
    public static final String FILTER_KEY_FILE_SIZE_TO = "FILE_SIZE_TO";
    public static final String FILTER_KEY_RESOURCE_TYPES = "RESOURCE_TYPES";
    public static final String FILTER_KEY_TASKS = "TASKS";
    public static final String FILTER_KEY_LOCK = "LOCK";
    public static final String FILTER_KEY_INCIDENT_TIME_BEGIN_DATE = "INCIDENT_TIME_BEGIN_DATE";
    public static final String FILTER_KEY_INCIDENT_TIME_END_DATE = "INCIDENT_TIME_END_DATE";
    public static final String FILTER_KEY_INCIDENT_TIME_POINT_FROM = "INCIDENT_TIME_POINT_FROM";
    public static final String FILTER_KEY_INCIDENT_TIME_POINT_TO = "INCIDENT_TIME_POINT_TO";
    public static final String FILTER_KEY_INCIDENT_TAG = "INCIDENT_TAG";
    public static final String FILTER_KEY_FILE_NAME = "FILE_NAME";
    public static final String FILTER_KEY_ANALYZE_ENGINE = "ANALYZE_ENGINE";
    public static final String FILTER_KEY_IRREGULAR_CONTENT = "IRREGULAR_CONTENT";
    public static final String FILTER_KEY_DETAIL_INFO = "DETAIL_INFO";
    public static final String FILTER_KEY_WORK_MODE = "WORK_MODE";
    public static final String FILTER_KEY_FILE_PATH = "FILE_PATH";
    public static final String FILTER_KEY_FOLDER_PATH = "FOLDER_PATH";
    public static final String FILTER_KEY_FILE_EXTENSION = "FILE_EXTENSION";
    public static final String FILTER_KEY_FILE_OWNER = "FILE_OWNER";
    public static final String FILTER_KEY_FOLDER_OWNER = "FOLDER_OWNER";
    public static final String FILTER_KEY_HOST_NAME = "HOST_NAME";
    public static final String FILTER_KEY_IP_ADDRESS = "IP_ADDRESS";
    public static final String FILTER_KEY_CORPORATE_TYPE = "CORPORATE_TYPE";
    public static final String FILTER_KEY_DEVICE_TYPE = "DEVICE_TYPE";
    public static final String FILTER_KEY_OPERATION_SYSTEM = "OPERATION_SYSTEM";
    public static final String FILTER_KEY_TERMINAL_TYPE = "TERMINAL_TYPE";
    public static final String FILTER_INCIDENT_PRE_BEGIN_DATE = "PRE_BEGIN_DATE";
    public static final String FILTER_INCIDENT_PRE_END_DATE = "PRE_END_DATE";
    public static final String FILTER_KEY_POLICY_GROUP = "POLICY_GROUPS";
    public static final String FILTER_KEY_SOURCE_ADDRESSES = "SOURCE_ADDRESSES";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_CUSTOM_SOURCES = "IS_FUZZY_QUERY_FILTER_KEY_CUSTOM_SOURCES";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_DESTINATIONS = "IS_FUZZY_QUERY_FILTER_KEY_DESTINATIONS";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_INCIDENT_TAG = "IS_FUZZY_QUERY_FILTER_KEY_INCIDENT_TAG";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_FILE_NAME = "IS_FUZZY_QUERY_FILTER_KEY_FILE_NAME";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_IRREGULAR_CONTENT = "IS_FUZZY_QUERY_FILTER_KEY_IRREGULAR_CONTENT";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_DETAIL_INFO = "IS_FUZZY_QUERY_FILTER_KEY_DETAIL_INFO";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_FILE_PATH = "IS_FUZZY_QUERY_FILTER_KEY_FILE_PATH";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_FOLDER_PATH = "IS_FUZZY_QUERY_FILTER_KEY_FOLDER_PATH";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_FILE_EXTENSION = "IS_FUZZY_QUERY_FILTER_KEY_FILE_EXTENSION";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_FILE_OWNER = "IS_FUZZY_QUERY_FILTER_KEY_FILE_OWNER";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_FOLDER_OWNER = "IS_FUZZY_QUERY_FILTER_KEY_FOLDER_OWNER";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_HOST_NAME = "IS_FUZZY_QUERY_FILTER_KEY_HOST_NAME";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_IP_ADDRESS = "IS_FUZZY_QUERY_FILTER_KEY_IP_ADDRESS";
    public static final String IS_FUZZY_QUERY_FILTER_KEY_OPERATION_SYSTEM = "IS_FUZZY_QUERY_FILTER_KEY_OPERATION_SYSTEM";

    public static Map<String, Object> buildIncidentReportCondition(IncidentReportFilterForm filterForm) {
        HashMap condition = Maps.newHashMap();
        if (filterForm != null) {
            IncidentReportFilterUtil.buildFilterIncidentId(filterForm.getIncidentId(), condition);
            IncidentReportFilterUtil.buildFilterTransactionId(filterForm.getTransactionId(), condition);
            IncidentReportFilterUtil.buildFilterTime(filterForm.getTime(), condition);
            IncidentReportFilterUtil.buildFilterPolicy(filterForm.getPolicy(), condition);
            IncidentReportFilterUtil.buildFilterSeverity(filterForm.getSeverity(), condition);
            IncidentReportFilterUtil.buildFilterStatus(filterForm.getStatus(), condition);
            IncidentReportFilterUtil.buildFilterMatch(filterForm.getMatch(), condition);
            IncidentReportFilterUtil.buildFilterIgnore(filterForm.getIgnore(), condition);
            IncidentReportFilterUtil.buildFilterDetectEngine(filterForm.getDetectEngine(), condition);
            IncidentReportFilterUtil.buildFilterChannel(filterForm.getChannel(), condition);
            IncidentReportFilterUtil.buildFilterSource(filterForm.getSource(), condition);
            IncidentReportFilterUtil.buildFilterDestination(filterForm.getDestination(), condition);
            IncidentReportFilterUtil.buildFilterRelease(filterForm.getRelease(), condition);
            IncidentReportFilterUtil.buildFilterAction(filterForm.getAction(), condition);
            IncidentReportFilterUtil.buildFilterTransactionSize(filterForm.getTransactionSize(), condition);
            IncidentReportFilterUtil.buildFilterFileSize(filterForm.getFileSize(), condition);
            IncidentReportFilterUtil.buildFilterResourceType(filterForm.getResourceType(), condition);
            IncidentReportFilterUtil.buildFilterTask(filterForm.getTask(), condition);
            IncidentReportFilterUtil.buildFilterLock(filterForm.getLock(), condition);
            IncidentReportFilterUtil.buildFilterIncidentTime(filterForm.getIncidentTime(), condition);
            IncidentReportFilterUtil.buildFilterIncidentTag(filterForm.getIncidentTag(), condition);
            IncidentReportFilterUtil.buildFilterFileName(filterForm.getFileName(), condition);
            IncidentReportFilterUtil.buildFilterAnalyzeEngine(filterForm.getAnalyzeEngine(), condition);
            IncidentReportFilterUtil.buildFilterIrregularContent(filterForm.getIrregularContent(), condition);
            IncidentReportFilterUtil.buildFilterDetailInfo(filterForm.getDetailInfo(), condition);
            IncidentReportFilterUtil.buildFilterWorkMode(filterForm.getWorkMode(), condition);
            IncidentReportFilterUtil.buildFilterFilePath(filterForm.getFilePath(), condition);
            IncidentReportFilterUtil.buildFilterFolderPath(filterForm.getFolderPath(), condition);
            IncidentReportFilterUtil.buildFilterFileExtension(filterForm.getFileExtension(), condition);
            IncidentReportFilterUtil.buildFilterFileOwner(filterForm.getFileOwner(), condition);
            IncidentReportFilterUtil.buildFilterFolderOwner(filterForm.getFolderOwner(), condition);
            IncidentReportFilterUtil.buildFilterHostName(filterForm.getHostName(), condition);
            IncidentReportFilterUtil.buildFilterIpAddress(filterForm.getIpAddress(), condition);
            IncidentReportFilterUtil.buildFilterCorporateType(filterForm.getCorporate(), condition);
            IncidentReportFilterUtil.buildFilterDeviceType(filterForm.getDevice(), condition);
            IncidentReportFilterUtil.buildFilterOperationSystem(filterForm.getOperationSystem(), condition);
            IncidentReportFilterUtil.buildFilterPolicyGroup(filterForm.getPolicyGroup(), condition);
        }
        return condition;
    }

    public static Map<String, Object> buildIncidentReportCondition4DiscoveryStatistic(IncidentReportFilterForm filterForm) {
        HashMap condition = Maps.newHashMap();
        if (filterForm != null) {
            IncidentReportFilterUtil.buildFilterTime(filterForm.getTime(), condition);
        }
        return condition;
    }

    private static void buildFilterIncidentId(FilterIncidentIdForm incidentIdForm, Map<String, Object> condition) {
        if (incidentIdForm != null && incidentIdForm.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)incidentIdForm.getIds());
            String str = SqlUtil.buildColumnsWithoutQuote((List)list);
            condition.put("INCIDENT_ID", str);
        }
    }

    private static void buildFilterTransactionId(FilterTransactionIdForm transactionIdForm, Map<String, Object> condition) {
        if (transactionIdForm != null && transactionIdForm.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)transactionIdForm.getIds());
            String str = SqlUtil.buildColumnsWithQuote((List)list);
            condition.put("TRANSACTION_ID", str);
        }
    }

    private static void buildFilterTime(FilterTimeForm timeForm, Map<String, Object> condition) {
        if (timeForm != null && timeForm.isEnableFilter() && timeForm.getTimeType() != null) {
            String beginDate = "";
            String endDate = "";
            DateTime now = new DateTime();
            PeriodTimeType periodTimeType = timeForm.getTimeType();
            switch (periodTimeType) {
                case CUSTOM_TIME: {
                    beginDate = new DateTime((Object)timeForm.getPreciseFrom()).toString("yyyy-MM-dd HH:mm:ss");
                    endDate = new DateTime((Object)timeForm.getPreciseTo()).toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_PAST_N_DAY: {
                    DateTime pre = now.minusDays(timeForm.getLastPastDay() - 1).secondOfDay().withMinimumValue();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_24_HOURS: {
                    DateTime pre = now.minusHours(24);
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case YESTERDAY: {
                    DateTime pre = now.minusDays(1).secondOfDay().withMinimumValue();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = now.minusDays(1).secondOfDay().withMaximumValue();
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_WEEK: {
                    DateTime pre = now.weekOfWeekyear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_WEEK: {
                    DateTime pre = now.minusWeeks(1).weekOfWeekyear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = now.minusWeeks(1).weekOfWeekyear().roundCeilingCopy();
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_MONTH: {
                    DateTime pre = now.monthOfYear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_MONTH: {
                    DateTime pre = now.minusMonths(1).monthOfYear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = now.minusMonths(1).monthOfYear().roundCeilingCopy();
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_QUARTER: {
                    DateTime pre = IncidentReportFilterUtil.getFirstDayOfQuarter(now);
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_QUARTER: {
                    DateTime pre = IncidentReportFilterUtil.getFirstDayOfQuarter(now.minusMonths(3));
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = IncidentReportFilterUtil.getLastDayOfQuarter(now.minusMonths(3));
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_YEAR: {
                    DateTime pre = now.year().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
            }
            condition.put("BEGIN_DATE", beginDate);
            condition.put("END_DATE", endDate);
            if (timeForm.isTimePointOn()) {
                condition.put("TIME_POINT_FROM", timeForm.getTimePointFrom());
                condition.put("TIME_POINT_TO", timeForm.getTimePointTo());
            }
        }
    }

    private static void buildFilterPolicy(FilterPolicyForm policyForm, Map<String, Object> condition) {
        if (policyForm != null && policyForm.isEnableFilter() && CollectionUtils.isNotEmpty(policyForm.getPolicies())) {
            String str = SqlUtil.buildColumnsWithQuote(policyForm.getPolicies());
            condition.put("POLICIES", str);
        }
    }

    private static void buildFilterSeverity(FilterSeverityForm severityForm, Map<String, Object> condition) {
        if (severityForm != null && severityForm.isEnableFilter() && CollectionUtils.isNotEmpty(severityForm.getSeverities())) {
            String str = Joiner.on((String)",").skipNulls().join(severityForm.getSeverities());
            condition.put("SEVERITIES", str);
        }
    }

    private static void buildFilterStatus(FilterStatusForm statusForm, Map<String, Object> condition) {
        if (statusForm != null && statusForm.isEnableFilter() && CollectionUtils.isNotEmpty(statusForm.getStatuses())) {
            String str = Joiner.on((String)",").skipNulls().join(statusForm.getStatuses());
            condition.put("STATUSES", str);
        }
    }

    private static void buildFilterMatch(FilterMatchForm matchForm, Map<String, Object> condition) {
        if (matchForm != null && matchForm.isEnableFilter()) {
            if (matchForm.getLower() != null && matchForm.getLower() >= 0) {
                condition.put("MATCH_FROM", matchForm.getLower());
            }
            if (matchForm.getUpper() != null && matchForm.getUpper() >= 0) {
                condition.put("MATCH_TO", matchForm.getUpper());
            }
        }
    }

    private static void buildFilterIgnore(FilterIgnoreForm ignoreForm, Map<String, Object> condition) {
        if (ignoreForm != null && ignoreForm.isEnableFilter()) {
            condition.put("IGNORE", ignoreForm.getIgnoredType());
        }
    }

    private static void buildFilterDetectEngine(FilterDetectEngineForm detectEngineForm, Map<String, Object> condition) {
        if (detectEngineForm != null && detectEngineForm.isEnableFilter() && CollectionUtils.isNotEmpty(detectEngineForm.getEngines())) {
            String str = SqlUtil.buildColumnsWithQuote(detectEngineForm.getEngines());
            condition.put("DETECT_ENGINES", str);
        }
    }

    private static void buildFilterChannel(FilterChannelForm channelForm, Map<String, Object> condition) {
        if (channelForm != null && channelForm.isEnableFilter() && CollectionUtils.isNotEmpty(channelForm.getChannels())) {
            String str = Joiner.on((String)",").skipNulls().join(channelForm.getChannels());
            condition.put("CHANNELS", str);
        }
    }

    private static void buildFilterSource(FilterSourceForm sourceForm, Map<String, Object> condition) {
//        if (sourceForm != null && sourceForm.isEnableFilter()) {
//            DirectoryLocalEntryService directoryLocalEntryService = (DirectoryLocalEntryService) SpringUtil.getCtx().getBean(DirectoryLocalEntryService.class);
//            List list = Lists.newArrayList();
//            if (StringUtils.isNotBlank((String)sourceForm.getCustomSources())) {
//                list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)sourceForm.getCustomSources());
//                if (sourceForm.isFuzzyQuery()) {
//                    condition.put("CUSTOM_SOURCES", (ArrayList)list);
//                } else {
//                    String str = SqlUtil.buildColumnsWithQuote((List)list);
//                    condition.put("CUSTOM_SOURCES", str);
//                }
//                condition.put("IS_FUZZY_QUERY_FILTER_KEY_CUSTOM_SOURCES", sourceForm.isFuzzyQuery());
//            } else {
//                List baseDirectoryEntries = directoryLocalEntryService.getDirectoryEntriesByUuids(sourceForm.getSources(), null);
//                if (CollectionUtils.isNotEmpty((Collection)baseDirectoryEntries)) {
//                    for (BaseDirectoryEntry base : baseDirectoryEntries) {
//                        if (base instanceof DirectoryGroup) {
//                            List directoryUsers = directoryLocalEntryService.getDirectoryUsersByGroupUuid(base.getUuid(), null);
//                            for (DirectoryUser user : directoryUsers) {
//                                list.add(user.getUuid());
//                            }
//                            continue;
//                        }
//                        list.add(base.getUuid());
//                    }
//                }
//                String sourceSql = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("SOURCES", sourceSql);
//            }
//        }
    }

    private static void buildFilterDestination(FilterDestinationForm destinationForm, Map<String, Object> condition) {
        if (destinationForm != null && destinationForm.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)destinationForm.getDestinations());
            if (destinationForm.isFuzzyQuery()) {
                condition.put("DESTINATIONS", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("DESTINATIONS", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_DESTINATIONS", destinationForm.isFuzzyQuery());
        }
    }

    private static void buildFilterRelease(FilterReleaseForm releaseForm, Map<String, Object> condition) {
        if (releaseForm != null && releaseForm.isEnableFilter() && releaseForm.getReleaseType() != null) {
            condition.put("RELEASE", releaseForm.getReleaseType());
        }
    }

    private static void buildFilterAction(FilterActionForm actionForm, Map<String, Object> condition) {
        if (actionForm != null && actionForm.isEnableFilter() && CollectionUtils.isNotEmpty(actionForm.getActions())) {
            String str = Joiner.on((String)",").skipNulls().join(actionForm.getActions());
            condition.put("ACTIONS", str);
        }
    }

    private static void buildFilterTransactionSize(FilterTransactionSizeForm transactionSizeForm, Map<String, Object> condition) {
        if (transactionSizeForm != null && transactionSizeForm.isEnableFilter()) {
            if (transactionSizeForm.getLower() != null && transactionSizeForm.getLower() >= 0) {
                condition.put("TRANSACTION_SIZE_FROM", transactionSizeForm.getLower());
            }
            if (transactionSizeForm.getUpper() != null && transactionSizeForm.getUpper() >= 0) {
                condition.put("TRANSACTION_SIZE_TO", transactionSizeForm.getUpper());
            }
        }
    }

    private static void buildFilterFileSize(FilterFileSizeForm fileSizeForm, Map<String, Object> condition) {
        if (fileSizeForm != null && fileSizeForm.isEnableFilter()) {
            if (fileSizeForm.getLower() != null && fileSizeForm.getLower() >= 0) {
                condition.put("FILE_SIZE_FROM", fileSizeForm.getLower());
            }
            if (fileSizeForm.getUpper() != null && fileSizeForm.getUpper() >= 0) {
                condition.put("FILE_SIZE_TO", fileSizeForm.getUpper());
            }
        }
    }

    private static void buildFilterResourceType(FilterResourceTypeForm resourceTypeForm, Map<String, Object> condition) {
        if (resourceTypeForm != null && resourceTypeForm.isEnableFilter() && CollectionUtils.isNotEmpty(resourceTypeForm.getResources())) {
            String str = Joiner.on((String)",").skipNulls().join(resourceTypeForm.getResources());
            condition.put("RESOURCE_TYPES", str);
        }
    }

    private static void buildFilterTask(FilterTaskForm taskForm, Map<String, Object> condition) {
        if (taskForm != null && taskForm.isEnableFilter() && CollectionUtils.isNotEmpty(taskForm.getTasks())) {
            String str = SqlUtil.buildColumnsWithQuote(taskForm.getTasks());
            condition.put("TASKS", str);
        }
    }

    private static void buildFilterLock(FilterLockForm lockForm, Map<String, Object> condition) {
        if (lockForm != null && lockForm.isEnableFilter()) {
            condition.put("LOCK", lockForm.getLockType());
        }
    }

    private static void buildFilterIncidentTime(FilterIncidentTimeForm incidentTime, Map<String, Object> condition) {
        if (incidentTime != null && incidentTime.isEnableFilter() && incidentTime.getTimeType() != null) {
            String beginDate = "";
            String endDate = "";
            DateTime now = new DateTime();
            PeriodTimeType periodTimeType = incidentTime.getTimeType();
            switch (periodTimeType) {
                case CUSTOM_TIME: {
                    beginDate = new DateTime((Object)incidentTime.getPreciseFrom()).toString("yyyy-MM-dd HH:mm:ss");
                    endDate = new DateTime((Object)incidentTime.getPreciseTo()).toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_PAST_N_DAY: {
                    DateTime pre = now.minusDays(incidentTime.getLastPastDay() - 1).secondOfDay().withMinimumValue();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_24_HOURS: {
                    DateTime pre = now.minusHours(24);
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case YESTERDAY: {
                    DateTime pre = now.minusDays(1).secondOfDay().withMinimumValue();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = now.minusDays(1).secondOfDay().withMaximumValue();
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_WEEK: {
                    DateTime pre = now.weekOfWeekyear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_WEEK: {
                    DateTime pre = now.minusWeeks(1).weekOfWeekyear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = now.minusWeeks(1).weekOfWeekyear().roundCeilingCopy();
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_MONTH: {
                    DateTime pre = now.monthOfYear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_MONTH: {
                    DateTime pre = now.minusMonths(1).monthOfYear().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = now.minusMonths(1).monthOfYear().roundCeilingCopy();
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_QUARTER: {
                    DateTime pre = IncidentReportFilterUtil.getFirstDayOfQuarter(now);
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case LAST_QUARTER: {
                    DateTime pre = IncidentReportFilterUtil.getFirstDayOfQuarter(now.minusMonths(3));
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    pre = IncidentReportFilterUtil.getLastDayOfQuarter(now.minusMonths(3));
                    endDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
                case THIS_YEAR: {
                    DateTime pre = now.year().roundFloorCopy();
                    beginDate = pre.toString("yyyy-MM-dd HH:mm:ss");
                    endDate = now.toString("yyyy-MM-dd HH:mm:ss");
                    break;
                }
            }
            condition.put("INCIDENT_TIME_BEGIN_DATE", beginDate);
            condition.put("INCIDENT_TIME_END_DATE", endDate);
            if (incidentTime.isTimePointOn()) {
                condition.put("INCIDENT_TIME_POINT_FROM", incidentTime.getTimePointFrom());
                condition.put("INCIDENT_TIME_POINT_TO", incidentTime.getTimePointTo());
            }
        }
    }

    private static void buildFilterIncidentTag(FilterIncidentTagForm incidentTag, Map<String, Object> condition) {
        if (incidentTag != null && incidentTag.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)incidentTag.getTags());
            if (incidentTag.isFuzzyQuery()) {
                condition.put("INCIDENT_TAG", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_INCIDENT_TAG", incidentTag.isFuzzyQuery());
        }
    }

    private static void buildFilterFileName(FilterFileNameForm fileName, Map<String, Object> condition) {
        if (fileName != null && fileName.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)fileName.getNames());
            if (fileName.isFuzzyQuery()) {
                condition.put("FILE_NAME", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_NAME", fileName.isFuzzyQuery());
        }
    }

    private static void buildFilterAnalyzeEngine(FilterAnalyzeEngineForm analyzeEngine, Map<String, Object> condition) {
        if (analyzeEngine != null && analyzeEngine.isEnableFilter() && CollectionUtils.isNotEmpty(analyzeEngine.getEngines())) {
            String str = SqlUtil.buildColumnsWithQuote(analyzeEngine.getEngines());
            condition.put("ANALYZE_ENGINE", str);
        }
    }

    private static void buildFilterIrregularContent(FilterIrregularContentForm irregularContent, Map<String, Object> condition) {
        if (irregularContent != null && irregularContent.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)irregularContent.getContents());
            if (irregularContent.isFuzzyQuery()) {
                condition.put("IRREGULAR_CONTENT", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_IRREGULAR_CONTENT", irregularContent.isFuzzyQuery());
        }
    }

    private static void buildFilterDetailInfo(FilterDetailInfoForm detailInfo, Map<String, Object> condition) {
        if (detailInfo != null && detailInfo.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)detailInfo.getDetails());
            if (detailInfo.isFuzzyQuery()) {
                condition.put("DETAIL_INFO", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_DETAIL_INFO", detailInfo.isFuzzyQuery());
        }
    }

    private static void buildFilterWorkMode(FilterWorkModeForm workMode, Map<String, Object> condition) {
        if (workMode != null && workMode.isEnableFilter() && workMode.getWorkModeType() != null) {
            condition.put("WORK_MODE", workMode.getWorkModeType());
        }
    }

    private static void buildFilterFilePath(FilterFilePathForm filePath, Map<String, Object> condition) {
        if (filePath != null && filePath.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)filePath.getPaths());
            if (filePath.isFuzzyQuery()) {
                condition.put("FILE_PATH", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_PATH", filePath.isFuzzyQuery());
        }
    }

    private static void buildFilterFolderPath(FilterFolderPathForm folderPath, Map<String, Object> condition) {
        if (folderPath != null && folderPath.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)folderPath.getPaths());
            if (folderPath.isFuzzyQuery()) {
                condition.put("FOLDER_PATH", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FOLDER_PATH", folderPath.isFuzzyQuery());
        }
    }

    private static void buildFilterFileExtension(FilterFileExtensionForm filterFileExtension, Map<String, Object> condition) {
        if (filterFileExtension != null && filterFileExtension.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)filterFileExtension.getExtensions());
            if (filterFileExtension.isFuzzyQuery()) {
                condition.put("FILE_EXTENSION", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_EXTENSION", filterFileExtension.isFuzzyQuery());
        }
    }

    private static void buildFilterFileOwner(FilterFileOwnerForm fileOwner, Map<String, Object> condition) {
        if (fileOwner != null && fileOwner.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)fileOwner.getOwners());
            if (fileOwner.isFuzzyQuery()) {
                condition.put("FILE_OWNER", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_OWNER", fileOwner.isFuzzyQuery());
        }
    }

    private static void buildFilterFolderOwner(FilterFolderOwnerForm folderOwner, Map<String, Object> condition) {
        if (folderOwner != null && folderOwner.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)folderOwner.getOwners());
            if (folderOwner.isFuzzyQuery()) {
                condition.put("FOLDER_OWNER", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FOLDER_OWNER", folderOwner.isFuzzyQuery());
        }
    }

    private static void buildFilterHostName(FilterHostNameForm hostName, Map<String, Object> condition) {
        if (hostName != null && hostName.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)hostName.getNames());
            if (hostName.isFuzzyQuery()) {
                condition.put("HOST_NAME", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_HOST_NAME", hostName.isFuzzyQuery());
        }
    }

    private static void buildFilterIpAddress(FilterIpAddressForm ipAddress, Map<String, Object> condition) {
        if (ipAddress != null && ipAddress.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)ipAddress.getIps());
            if (ipAddress.isFuzzyQuery()) {
                condition.put("IP_ADDRESS", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_IP_ADDRESS", ipAddress.isFuzzyQuery());
        }
    }

    private static void buildFilterCorporateType(FilterCorporateForm corporate, Map<String, Object> condition) {
        if (corporate != null && corporate.isEnableFilter()) {
            String str = Joiner.on((String)",").skipNulls().join(corporate.getCorporates());
            condition.put("CORPORATE_TYPE", str);
        }
    }

    private static void buildFilterDeviceType(FilterDeviceForm device, Map<String, Object> condition) {
        if (device != null && device.isEnableFilter()) {
            String str = Joiner.on((String)",").skipNulls().join(device.getDevices());
            condition.put("DEVICE_TYPE", str);
        }
    }

    private static void buildFilterOperationSystem(FilterOperationSystemForm operationSystem, Map<String, Object> condition) {
        if (operationSystem != null && operationSystem.isEnableFilter()) {
            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)operationSystem.getSystems());
            if (operationSystem.isFuzzyQuery()) {
                condition.put("OPERATION_SYSTEM", list);
            } else {
                String str = SqlUtil.buildColumnsWithQuote((List)list);
                condition.put("INCIDENT_TAG", str);
            }
            condition.put("IS_FUZZY_QUERY_FILTER_KEY_OPERATION_SYSTEM", operationSystem.isFuzzyQuery());
        }
    }

    private static void buildFilterPolicyGroup(FilterPolicyGroupForm policyGroupForm, Map<String, Object> condition) {
        if (policyGroupForm != null && policyGroupForm.isEnableFilter() && CollectionUtils.isNotEmpty(policyGroupForm.getPolicyGroups())) {
            String str = SqlUtil.buildColumnsWithQuote(policyGroupForm.getPolicyGroups());
            condition.put("POLICY_GROUPS", str);
        }
    }

    private static DateTime getFirstDayOfQuarter(DateTime now) {
        DateTime result = null;
        int month = now.getMonthOfYear();
        if (month >= 1 && month <= 3) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(1).withDayOfMonth(1);
        } else if (month >= 4 && month <= 6) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(4).withDayOfMonth(1);
        } else if (month >= 7 && month <= 9) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(7).withDayOfMonth(1);
        } else if (month >= 10 && month <= 12) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(10).withDayOfMonth(1);
        }
        return result.dayOfMonth().roundFloorCopy();
    }

    private static DateTime getLastDayOfQuarter(DateTime now) {
        DateTime result = null;
        int month = now.getMonthOfYear();
        if (month >= 1 && month <= 3) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(3).withDayOfMonth(31);
        } else if (month >= 4 && month <= 6) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(6).withDayOfMonth(30);
        } else if (month >= 7 && month <= 9) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(9).withDayOfMonth(30);
        } else if (month >= 10 && month <= 12) {
            result = new DateTime().withYear(now.getYear()).withMonthOfYear(12).withDayOfMonth(31);
        }
        return result.dayOfMonth().roundCeilingCopy();
    }

    public static void main(String[] args) {
        HashMap condition = Maps.newHashMap();
        FilterTimeForm timeForm = new FilterTimeForm();
        timeForm.setEnableFilter(true);
        timeForm.setTimeType(PeriodTimeType.THIS_WEEK);
        timeForm.setTimePointOn(true);
        timeForm.setTimePointFrom("12:30");
        timeForm.setTimePointTo("14:30");
        IncidentReportFilterUtil.buildFilterTime(timeForm, condition);
        System.out.println(condition);
    }

}

