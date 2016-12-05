package com.sky.demo.web_demo_multi_tenant_separate_schema.report.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.*;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SpringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by user on 16/12/2.
 */
public class IncidentReportFilterForEsUtil {

    private static final Logger logger = LoggerFactory.getLogger(IncidentReportFilterForEsUtil.class);


    public static List<QueryBuilder> buildIncidentReportCondition(IncidentReportFilterForm filterForm) {

        List<QueryBuilder> queryBuilders = Lists.newArrayList();
        QueryBuilder queryBuilder = null;
        if (filterForm != null) {
            queryBuilder = buildFilterIncidentId(filterForm.getIncidentId());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterTransactionId(filterForm.getTransactionId());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterTime(filterForm.getTime());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterPolicy(filterForm.getPolicy());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterSeverity(filterForm.getSeverity());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterStatus(filterForm.getStatus());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterMatch(filterForm.getMatch());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterIgnore(filterForm.getIgnore());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterDetectEngine(filterForm.getDetectEngine());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterChannel(filterForm.getChannel());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterSource(filterForm.getSource());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterDestination(filterForm.getDestination());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterRelease(filterForm.getRelease());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterAction(filterForm.getAction());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterTransactionSize(filterForm.getTransactionSize());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
//            queryBuilder = buildFilterFileSize(filterForm.getFileSize());
//            queryBuilder = buildFilterResourceType(filterForm.getResourceType());
//            queryBuilder = buildFilterTask(filterForm.getTask());
//            queryBuilder = buildFilterLock(filterForm.getLock());
            queryBuilder = buildFilterIncidentTime(filterForm.getIncidentTime());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterIncidentTag(filterForm.getIncidentTag());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterFileName(filterForm.getFileName());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterAnalyzeEngine(filterForm.getAnalyzeEngine());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterIrregularContent(filterForm.getIrregularContent());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterDetailInfo(filterForm.getDetailInfo());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
            queryBuilder = buildFilterWorkMode(filterForm.getWorkMode());
            if(queryBuilder != null) {
                queryBuilders.add(queryBuilder);
            }
//            queryBuilder = buildFilterFilePath(filterForm.getFilePath());
//            queryBuilder = buildFilterFolderPath(filterForm.getFolderPath());
//            queryBuilder = buildFilterFileExtension(filterForm.getFileExtension());
//            queryBuilder = buildFilterFileOwner(filterForm.getFileOwner());
//            queryBuilder = buildFilterFolderOwner(filterForm.getFolderOwner());
//            queryBuilder = buildFilterHostName(filterForm.getHostName());
//            queryBuilder = buildFilterIpAddress(filterForm.getIpAddress());
//            queryBuilder = buildFilterCorporateType(filterForm.getCorporate());
//            queryBuilder = buildFilterDeviceType(filterForm.getDevice());
//            queryBuilder = buildFilterOperationSystem(filterForm.getOperationSystem());
//            queryBuilder = buildFilterPolicyGroup(filterForm.getPolicyGroup());  //TODO
        }

        return queryBuilders;
    }


    private static QueryBuilder buildFilterIncidentId(FilterIncidentIdForm incidentIdForm) {
        if (incidentIdForm != null && incidentIdForm.isEnableFilter()) {
            List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(incidentIdForm.getIds());
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(id -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("id", id);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterTransactionId(FilterTransactionIdForm transactionIdForm) {
        if (transactionIdForm != null && transactionIdForm.isEnableFilter()) {
            List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(transactionIdForm.getIds());
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(transactionId -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("transactionId", transactionId);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterTime(FilterTimeForm timeForm) {
        RangeQueryBuilder queryBuilder = null;
        if (timeForm != null && timeForm.isEnableFilter() && timeForm.getTimeType() != null) {
            DateTime beginDate = null;
            DateTime endDate = null;
            DateTime now = new DateTime();
            PeriodTimeType periodTimeType = timeForm.getTimeType();
            switch (periodTimeType) {
                case CUSTOM_TIME: {
                    beginDate = new DateTime(timeForm.getPreciseFrom());
                    endDate = new DateTime(timeForm.getPreciseTo());
                    break;
                }
                case LAST_PAST_N_DAY: {
                    DateTime pre = now.minusDays(timeForm.getLastPastDay() - 1).secondOfDay().withMinimumValue();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_24_HOURS: {
                    DateTime pre = now.minusHours(24);
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case YESTERDAY: {
                    DateTime pre = now.minusDays(1).secondOfDay().withMinimumValue();
                    beginDate = pre;
                    pre = now.minusDays(1).secondOfDay().withMaximumValue();
                    endDate = pre;
                    break;
                }
                case THIS_WEEK: {
                    DateTime pre = now.weekOfWeekyear().roundFloorCopy();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_WEEK: {
                    DateTime pre = now.minusWeeks(1).weekOfWeekyear().roundFloorCopy();
                    beginDate = pre;
                    pre = now.minusWeeks(1).weekOfWeekyear().roundCeilingCopy();
                    endDate = pre;
                    break;
                }
                case THIS_MONTH: {
                    DateTime pre = now.monthOfYear().roundFloorCopy();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_MONTH: {
                    DateTime pre = now.minusMonths(1).monthOfYear().roundFloorCopy();
                    beginDate = pre;
                    pre = now.minusMonths(1).monthOfYear().roundCeilingCopy();
                    endDate = pre;
                    break;
                }
                case THIS_QUARTER: {
                    DateTime pre = getFirstDayOfQuarter(now);
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_QUARTER: {
                    DateTime pre = getFirstDayOfQuarter(now.minusMonths(3));
                    beginDate = pre;
                    pre = getLastDayOfQuarter(now.minusMonths(3));
                    endDate = pre;
                    break;
                }
                case THIS_YEAR: {
                    DateTime pre = now.year().roundFloorCopy();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
            }
            queryBuilder = QueryBuilders.rangeQuery("incidentTime")
                    .from(beginDate.getMillis())
                    .to(endDate.getMillis());

        }
        return queryBuilder;
    }

    private static QueryBuilder buildFilterPolicy(FilterPolicyForm policyForm) {
        if (policyForm != null && policyForm.isEnableFilter() && CollectionUtils.isNotEmpty(policyForm.getPolicies())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            policyForm.getPolicies().forEach(policyName -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("incidentPolicies.policyName", policyName);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterSeverity(FilterSeverityForm severityForm) {
        if (severityForm != null && severityForm.isEnableFilter() && CollectionUtils.isNotEmpty(severityForm.getSeverities())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            severityForm.getSeverities().forEach(severity -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("severityTypeCode", severity);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterStatus(FilterStatusForm statusForm) {
        if (statusForm != null && statusForm.isEnableFilter() && CollectionUtils.isNotEmpty(statusForm.getStatuses())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            statusForm.getStatuses().forEach(status -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("statusTypeCode", status);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterMatch(FilterMatchForm matchForm) {
        RangeQueryBuilder queryBuilder = null;
        if (matchForm != null && matchForm.isEnableFilter()) {
            queryBuilder = QueryBuilders.rangeQuery("maxMatches");
            if (matchForm.getLower() != null && matchForm.getLower() >= 0) {
                queryBuilder.from(matchForm.getLower());
            }
            if (matchForm.getUpper() != null && matchForm.getUpper() >= 0) {
                queryBuilder.to(matchForm.getUpper());
            }
        }
        return queryBuilder;
    }

    private static QueryBuilder buildFilterIgnore(FilterIgnoreForm ignoreForm) {
        QueryBuilder queryBuilder = null;
        if (ignoreForm != null && ignoreForm.isEnableFilter()) {
            boolean isIgnored = ignoreForm.getIgnoredType() == null ? false : (ignoreForm.getIgnoredType() == 1 ? true : false);
            queryBuilder = QueryBuilders.matchPhraseQuery("isIgnored", isIgnored);
        }
        return queryBuilder;
    }

    private static QueryBuilder buildFilterDetectEngine(FilterDetectEngineForm detectEngine) {
        if (detectEngine != null && detectEngine.isEnableFilter() && CollectionUtils.isNotEmpty(detectEngine.getEngines())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            detectEngine.getEngines().forEach(engine -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("detectedByName", engine);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterChannel(FilterChannelForm channelForm) {
        if (channelForm != null && channelForm.isEnableFilter() && CollectionUtils.isNotEmpty(channelForm.getChannels())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            channelForm.getChannels().forEach(channel -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("channelTypeCode", channel);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterSource(FilterSourceForm sourceForm) {
        if (sourceForm != null && sourceForm.isEnableFilter()) {
            List<String> list = Lists.newArrayList();
            if (StringUtils.isNotBlank(sourceForm.getCustomSources())) {
                list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(sourceForm.getCustomSources());
                if (sourceForm.isFuzzyQuery()) {
                    //TODO
                } else {

                }
                final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
                list.forEach(name -> {
                    QueryBuilder builder = QueryBuilders.matchPhraseQuery("sourceEntryInfo.commonName", name);
                    finalQueryBuilder.should(builder);

                });
                return finalQueryBuilder;
            } else {
                //TODO
//                DirectoryLocalEntryService directoryLocalEntryService = (DirectoryLocalEntryService) SpringUtil.getCtx().getBean(DirectoryLocalEntryService.class);
//                list = directoryLocalEntryService.getDirectoryEntriesByUuids(sourceForm.getSources(), null);
//                if (CollectionUtils.isNotEmpty(baseDirectoryEntries)) {
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
                final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
                list.forEach(uuid -> {
                    QueryBuilder builder = QueryBuilders.matchPhraseQuery("sourceEntryInfo.entryUuid", uuid);
                    finalQueryBuilder.should(builder);

                });
                return finalQueryBuilder;
            }
        }
        return null;
    }

    private static QueryBuilder buildFilterDestination(FilterDestinationForm destinationForm) {
        if (destinationForm != null && destinationForm.isEnableFilter()) {
            List list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(destinationForm.getDestinations());
            if (destinationForm.isFuzzyQuery()) {
                //TODO
            } else {

            }
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(name -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("incidentDestinations.destinationEntryInfo.commonName", name);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterRelease(FilterReleaseForm releaseForm) {
        QueryBuilder queryBuilder = null;
        if (releaseForm != null && releaseForm.isEnableFilter() && releaseForm.getReleaseType() != null) {
            boolean isReleased = releaseForm.getReleaseType() == null ? false : (releaseForm.getReleaseType() == 1 ? true : false);
            queryBuilder = QueryBuilders.matchPhraseQuery("isReleased", isReleased);
        }
        return queryBuilder;
    }

    private static QueryBuilder buildFilterAction(FilterActionForm actionForm) {
        if (actionForm != null && actionForm.isEnableFilter() && CollectionUtils.isNotEmpty(actionForm.getActions())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            actionForm.getActions().forEach(action -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("actionTypeCode", action);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterTransactionSize(FilterTransactionSizeForm transactionSizeForm) {
        RangeQueryBuilder queryBuilder = null;
        if (transactionSizeForm != null && transactionSizeForm.isEnableFilter()) {
            queryBuilder = QueryBuilders.rangeQuery("transactionSize");
            if (transactionSizeForm.getLower() != null && transactionSizeForm.getLower() >= 0) {
                queryBuilder.from(transactionSizeForm.getLower());
            }
            if (transactionSizeForm.getUpper() != null && transactionSizeForm.getUpper() >= 0) {
                queryBuilder.to(transactionSizeForm.getUpper());
            }
        }
        return queryBuilder;
    }

//    private static QueryBuilder buildFilterFileSize(FilterFileSizeForm fileSizeForm) {
//        RangeQueryBuilder queryBuilder = null;
//        if (fileSizeForm != null && fileSizeForm.isEnableFilter()) {
//            queryBuilder = QueryBuilders.rangeQuery("fileSize");
//            if (fileSizeForm.getLower() != null && fileSizeForm.getLower() >= 0) {
//                queryBuilder.from(fileSizeForm.getLower());
//            }
//            if (fileSizeForm.getUpper() != null && fileSizeForm.getUpper() >= 0) {
//                queryBuilder.to(fileSizeForm.getUpper());
//            }
//        }
//        return queryBuilder;
//    }

//    private static QueryBuilder buildFilterResourceType(FilterResourceTypeForm resourceTypeForm) {
//        QueryBuilder queryBuilder = null;
//        if (resourceTypeForm != null && resourceTypeForm.isEnableFilter() && CollectionUtils.isNotEmpty(resourceTypeForm.getResources())) {
//            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
//            resourceTypeForm.getResources().forEach(action -> {
//                QueryBuilder builder = QueryBuilders.matchPhraseQuery("actionTypeCode", action);
//                finalQueryBuilder.should(builder);
//
//            });
//            return finalQueryBuilder;
//        }
//        return null;
//    }
//
//    private static QueryBuilder buildFilterTask(FilterTaskForm taskForm) {
//        QueryBuilder queryBuilder = null;
//        if (taskForm != null && taskForm.isEnableFilter() && CollectionUtils.isNotEmpty(taskForm.getTasks())) {
//            String str = SqlUtil.buildColumnsWithQuote(taskForm.getTasks());
//            condition.put("TASKS", str);
//        }
//        return queryBuilder;
//    }
//
//    private static QueryBuilder buildFilterLock(FilterLockForm lockForm) {
//        QueryBuilder queryBuilder = null;
//        if (lockForm != null && lockForm.isEnableFilter()) {
//            condition.put("LOCK", lockForm.getLockType());
//        }
//        return queryBuilder;
//    }

    private static QueryBuilder buildFilterIncidentTime(FilterIncidentTimeForm incidentTime) {
        RangeQueryBuilder queryBuilder = null;
        if (incidentTime != null && incidentTime.isEnableFilter() && incidentTime.getTimeType() != null) {
            DateTime beginDate = null;
            DateTime endDate = null;
            DateTime now = new DateTime();
            PeriodTimeType periodTimeType = incidentTime.getTimeType();
            switch (periodTimeType) {
                case CUSTOM_TIME: {
                    beginDate = new DateTime(incidentTime.getPreciseFrom());
                    endDate = new DateTime(incidentTime.getPreciseTo());
                    break;
                }
                case LAST_PAST_N_DAY: {
                    DateTime pre = now.minusDays(incidentTime.getLastPastDay() - 1).secondOfDay().withMinimumValue();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_24_HOURS: {
                    DateTime pre = now.minusHours(24);
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case YESTERDAY: {
                    DateTime pre = now.minusDays(1).secondOfDay().withMinimumValue();
                    beginDate = pre;
                    pre = now.minusDays(1).secondOfDay().withMaximumValue();
                    endDate = pre;
                    break;
                }
                case THIS_WEEK: {
                    DateTime pre = now.weekOfWeekyear().roundFloorCopy();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_WEEK: {
                    DateTime pre = now.minusWeeks(1).weekOfWeekyear().roundFloorCopy();
                    beginDate = pre;
                    pre = now.minusWeeks(1).weekOfWeekyear().roundCeilingCopy();
                    endDate = pre;
                    break;
                }
                case THIS_MONTH: {
                    DateTime pre = now.monthOfYear().roundFloorCopy();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_MONTH: {
                    DateTime pre = now.minusMonths(1).monthOfYear().roundFloorCopy();
                    beginDate = pre;
                    pre = now.minusMonths(1).monthOfYear().roundCeilingCopy();
                    endDate = pre;
                    break;
                }
                case THIS_QUARTER: {
                    DateTime pre = getFirstDayOfQuarter(now);
                    beginDate = pre;
                    endDate = now;
                    break;
                }
                case LAST_QUARTER: {
                    DateTime pre = getFirstDayOfQuarter(now.minusMonths(3));
                    beginDate = pre;
                    pre = getLastDayOfQuarter(now.minusMonths(3));
                    endDate = pre;
                    break;
                }
                case THIS_YEAR: {
                    DateTime pre = now.year().roundFloorCopy();
                    beginDate = pre;
                    endDate = now;
                    break;
                }
            }
            queryBuilder = QueryBuilders.rangeQuery("incidentTime")
                    .from(beginDate.getMillis())
                    .to(endDate.getMillis());
        }
        return queryBuilder;
    }

    private static QueryBuilder buildFilterIncidentTag(FilterIncidentTagForm incidentTag) {
        if (incidentTag != null && incidentTag.isEnableFilter()) {
            List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(incidentTag.getTags());
            if (incidentTag.isFuzzyQuery()) {
                //TODO
            } else {

            }
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(tag -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("tagContent", tag);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterFileName(FilterFileNameForm fileName) {
        if (fileName != null && fileName.isEnableFilter()) {
            List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(fileName.getNames());
            if (fileName.isFuzzyQuery()) {
                //TODO
            } else {

            }
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(tag -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("attachmentNames", tag);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterAnalyzeEngine(FilterAnalyzeEngineForm analyzeEngine) {
        if (analyzeEngine != null && analyzeEngine.isEnableFilter() && CollectionUtils.isNotEmpty(analyzeEngine.getEngines())) {
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            analyzeEngine.getEngines().forEach(engine -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("analyzedByName", engine);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterIrregularContent(FilterIrregularContentForm irregularContent) {
        if (irregularContent != null && irregularContent.isEnableFilter()) {
            List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(irregularContent.getContents());
            if (irregularContent.isFuzzyQuery()) {
                //TODO
            } else {

            }
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(content -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("breachContents", content);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterDetailInfo(FilterDetailInfoForm detailInfo) {
        if (detailInfo != null && detailInfo.isEnableFilter()) {
            List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(detailInfo.getDetails());
            if (detailInfo.isFuzzyQuery()) {
                //TODO
            } else {

            }
            final BoolQueryBuilder finalQueryBuilder = QueryBuilders.boolQuery();
            list.forEach(detail -> {
                QueryBuilder builder = QueryBuilders.matchPhraseQuery("details", detail);
                finalQueryBuilder.should(builder);

            });
            return finalQueryBuilder;
        }
        return null;
    }

    private static QueryBuilder buildFilterWorkMode(FilterWorkModeForm workMode) {
        QueryBuilder queryBuilder = null;
        if (workMode != null && workMode.isEnableFilter() && workMode.getWorkModeType() != null) {
            queryBuilder = QueryBuilders.matchPhraseQuery("workModeTypeCode", workMode.getWorkModeType());
        }
        return queryBuilder;
    }

//    private static QueryBuilder buildFilterFilePath(FilterFilePathForm filePath) {
//        if (filePath != null && filePath.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)filePath.getPaths());
//            if (filePath.isFuzzyQuery()) {
//                condition.put("FILE_PATH", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_PATH", filePath.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterFolderPath(FilterFolderPathForm folderPath) {
//        if (folderPath != null && folderPath.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)folderPath.getPaths());
//            if (folderPath.isFuzzyQuery()) {
//                condition.put("FOLDER_PATH", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FOLDER_PATH", folderPath.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterFileExtension(FilterFileExtensionForm filterFileExtension) {
//        if (filterFileExtension != null && filterFileExtension.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)filterFileExtension.getExtensions());
//            if (filterFileExtension.isFuzzyQuery()) {
//                condition.put("FILE_EXTENSION", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_EXTENSION", filterFileExtension.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterFileOwner(FilterFileOwnerForm fileOwner) {
//        if (fileOwner != null && fileOwner.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)fileOwner.getOwners());
//            if (fileOwner.isFuzzyQuery()) {
//                condition.put("FILE_OWNER", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FILE_OWNER", fileOwner.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterFolderOwner(FilterFolderOwnerForm folderOwner) {
//        if (folderOwner != null && folderOwner.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)folderOwner.getOwners());
//            if (folderOwner.isFuzzyQuery()) {
//                condition.put("FOLDER_OWNER", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_FOLDER_OWNER", folderOwner.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterHostName(FilterHostNameForm hostName) {
//        if (hostName != null && hostName.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)hostName.getNames());
//            if (hostName.isFuzzyQuery()) {
//                condition.put("HOST_NAME", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_HOST_NAME", hostName.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterIpAddress(FilterIpAddressForm ipAddress) {
//        if (ipAddress != null && ipAddress.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)ipAddress.getIps());
//            if (ipAddress.isFuzzyQuery()) {
//                condition.put("IP_ADDRESS", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_IP_ADDRESS", ipAddress.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterCorporateType(FilterCorporateForm corporate) {
//        if (corporate != null && corporate.isEnableFilter()) {
//            String str = Joiner.on((String)",").skipNulls().join(corporate.getCorporates());
//            condition.put("CORPORATE_TYPE", str);
//        }
//    }
//
//    private static QueryBuilder buildFilterDeviceType(FilterDeviceForm device) {
//        if (device != null && device.isEnableFilter()) {
//            String str = Joiner.on((String)",").skipNulls().join(device.getDevices());
//            condition.put("DEVICE_TYPE", str);
//        }
//    }
//
//    private static QueryBuilder buildFilterOperationSystem(FilterOperationSystemForm operationSystem) {
//        if (operationSystem != null && operationSystem.isEnableFilter()) {
//            List list = Splitter.on((String)",").omitEmptyStrings().trimResults().splitToList((CharSequence)operationSystem.getSystems());
//            if (operationSystem.isFuzzyQuery()) {
//                condition.put("OPERATION_SYSTEM", list);
//            } else {
//                String str = SqlUtil.buildColumnsWithQuote((List)list);
//                condition.put("INCIDENT_TAG", str);
//            }
//            condition.put("IS_FUZZY_QUERY_FILTER_KEY_OPERATION_SYSTEM", operationSystem.isFuzzyQuery());
//        }
//    }
//
//    private static QueryBuilder buildFilterPolicyGroup(FilterPolicyGroupForm policyGroupForm) {
//        if (policyGroupForm != null && policyGroupForm.isEnableFilter() && CollectionUtils.isNotEmpty(policyGroupForm.getPolicyGroups())) {
//            String str = SqlUtil.buildColumnsWithQuote(policyGroupForm.getPolicyGroups());
//            condition.put("POLICY_GROUPS", str);
//        }
//    }


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

        DateTime now = new DateTime();
        System.out.println(now);

        Timestamp timestamp = new Timestamp(now.getMillis());
        System.out.println(timestamp);
    }


}
