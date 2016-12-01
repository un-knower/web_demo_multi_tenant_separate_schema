/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.skyguard.sps.base.server.basedb.BaseDao
 *  com.skyguard.sps.base.utils.convertor.sqlcondition.RegexpConvertor
 *  com.skyguard.sps.dlp.common.incident.dm.common.IncidentIgnoreStatus
 *  com.skyguard.sps.dlp.common.incident.dm.discovery.IncidentLockStatus
 *  com.skyguard.sps.dlp.common.incident.dm.network.IncidentReleaseStatus
 *  com.skyguard.sps.dlp.common.report.dm.IncidentType
 *  org.apache.commons.collections.CollectionUtils
 *  org.apache.commons.lang.StringUtils
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao;

import com.google.common.base.Preconditions;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentIgnoreStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery.IncidentLockStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.IncidentReleaseStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RegexpConvertor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseIncidentReportDao {
    public void buildIncidentGroupBy(Map<String, Object> condition, StringBuilder sql) {
        String groupBy = (String)condition.get("INCIDENT_GROUP_BY");
        if (StringUtils.isNotBlank((String)groupBy)) {
            sql.append(" group by ").append(groupBy);
        }
    }

    public void buildIncidentOrderBy(Map<String, Object> condition, StringBuilder sql) {
        String orderBy = (String)condition.get("INCIDENT_ORDER_BY");
        if (StringUtils.isNotBlank((String)orderBy)) {
            sql.append(" order by ").append(orderBy);
        }
    }

    public void buildGroupBy(Map<String, Object> condition, StringBuilder sql) {
        String groupBy = (String)condition.get("GROUP_BY");
        if (StringUtils.isNotBlank((String)groupBy)) {
            sql.append(" group by ").append(groupBy);
        }
    }

    public void buildOrderBy(Map<String, Object> condition, StringBuilder sql) {
        String orderBy = (String)condition.get("ORDER_BY");
        if (StringUtils.isNotBlank((String)orderBy)) {
            sql.append(" order by ").append(orderBy);
        }
    }

    public void buildLimit(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        Integer limit = (Integer)condition.get("LIMIT");
        if (limit != null && limit != -1) {
            sql.append(" limit ? ");
            params.add(limit);
        }
    }

    public void buildOffset(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        Long offset = (Long)condition.get("OFFSET");
        if (offset != null) {
            sql.append(" offset ? ");
            params.add(offset);
        }
    }

    public void buildCommonCondition(Map<String, Object> condition, StringBuilder sql, List<Object> params, IncidentType incidentType) {
        Integer transactionSizeTo;
        String corporateType;
        String severityType;
        String incidentEndDate;
        String incidentBeginDate;
        Integer workMode;
        Integer release;
        String transactionId;
        String incidentId;
        Integer lock;
        String statusType;
        Integer matchTo;
        String detectedBy;
        Integer fileSizeTo;
        String deviceType;
        String analyzedBy;
        Integer matchFrom;
        String actionType;
        Integer fileSizeFrom;
        Integer transactionSizeFrom;
        String task;
        String sourceAddresses;
        String endDate;
        String resourceTypes;
        Integer ignore;
        String channelType;
        Preconditions.checkNotNull(incidentType, "incident type is null");
        String beginDate = (String)condition.get("BEGIN_DATE");
        if (StringUtils.isNotBlank((String)beginDate)) {
            sql.append("and detect_time > ? ");
            params.add(Timestamp.valueOf(beginDate));
        }
        if (StringUtils.isNotBlank((String)(endDate = (String)condition.get("END_DATE")))) {
            sql.append("and detect_time <= ? ");
            params.add(Timestamp.valueOf(endDate));
        }
        if (StringUtils.isNotBlank((String)(incidentBeginDate = (String)condition.get("INCIDENT_TIME_BEGIN_DATE")))) {
            sql.append("and incident_time > ? ");
            params.add(Timestamp.valueOf(incidentBeginDate));
        }
        if (StringUtils.isNotBlank((String)(incidentEndDate = (String)condition.get("INCIDENT_TIME_END_DATE")))) {
            sql.append("and incident_time <= ? ");
            params.add(Timestamp.valueOf(incidentEndDate));
        }
        if (StringUtils.isNotBlank((String)(incidentId = (String)condition.get("INCIDENT_ID")))) {
            sql.append("and id in (").append(incidentId).append(") ");
        }
        if (StringUtils.isNotBlank((String)(transactionId = (String)condition.get("TRANSACTION_ID")))) {
            sql.append("and transaction_id in (").append(transactionId).append(") ");
        }
        if (StringUtils.isNotBlank((String)(channelType = (String)condition.get("CHANNELS")))) {
            sql.append("and channel_type in (").append(channelType).append(") ");
        }
        if (StringUtils.isNotBlank((String)(actionType = (String)condition.get("ACTIONS")))) {
            sql.append("and action_type in (").append(actionType).append(") ");
        }
        if (StringUtils.isNotBlank((String)(statusType = (String)condition.get("STATUSES")))) {
            sql.append("and status_type in (").append(statusType).append(") ");
        }
        if (StringUtils.isNotBlank((String)(severityType = (String)condition.get("SEVERITIES")))) {
            sql.append("and severity_type in (").append(severityType).append(") ");
        }
        if ((matchFrom = (Integer)condition.get("MATCH_FROM")) != null) {
            sql.append("and max_matches >= ? ");
            params.add(matchFrom);
        }
        if ((matchTo = (Integer)condition.get("MATCH_TO")) != null) {
            sql.append("and max_matches <= ? ");
            params.add(matchTo);
        }
        if ((transactionSizeFrom = (Integer)condition.get("TRANSACTION_SIZE_FROM")) != null) {
            sql.append("and transaction_size >= ? ");
            params.add(transactionSizeFrom);
        }
        if ((transactionSizeTo = (Integer)condition.get("TRANSACTION_SIZE_TO")) != null) {
            sql.append("and transaction_size <= ? ");
            params.add(transactionSizeTo);
        }
        if (StringUtils.isNotBlank((String)(detectedBy = (String)condition.get("DETECT_ENGINES")))) {
            sql.append("and detected_by in (").append(detectedBy).append(") ");
        }
        if (StringUtils.isNotBlank((String)(analyzedBy = (String)condition.get("ANALYZE_ENGINE")))) {
            sql.append("and analyzed_by in (").append(analyzedBy).append(") ");
        }
        if ((release = (Integer)condition.get("RELEASE")) != null) {
            sql.append("and is_released = ? ");
            IncidentReleaseStatus releaseStatus = IncidentReleaseStatus.get((int)release);
            params.add(IncidentReleaseStatus.IS_RELEASED.equals(releaseStatus));
        }
        if ((ignore = (Integer)condition.get("IGNORE")) != null) {
            sql.append("and is_ignored = ? ");
            IncidentIgnoreStatus ignoreStatus = IncidentIgnoreStatus.get((int)ignore);
            params.add(IncidentIgnoreStatus.IS_IGNORED.equals(ignoreStatus));
        }
        if ((workMode = (Integer)condition.get("WORK_MODE")) != null) {
            sql.append("and work_mode = ? ");
            params.add(workMode);
        }
        if (StringUtils.isNotBlank((String)(task = (String)condition.get("TASKS")))) {
            sql.append("and job_name in (").append(task).append(") ");
        }
        if ((fileSizeFrom = (Integer)condition.get("FILE_SIZE_FROM")) != null) {
            sql.append("and file_size >= ? ");
            params.add(fileSizeFrom);
        }
        if ((fileSizeTo = (Integer)condition.get("FILE_SIZE_TO")) != null) {
            sql.append("and file_size <= ? ");
            params.add(fileSizeTo);
        }
        if ((lock = (Integer)condition.get("LOCK")) != null) {
            sql.append("and is_locked = ? ");
            IncidentLockStatus lockStatus = IncidentLockStatus.get((int)lock);
            params.add(IncidentLockStatus.IS_LOCKED.equals(lockStatus));
        }
        if (StringUtils.isNotBlank((String)(resourceTypes = (String)condition.get("RESOURCE_TYPES")))) {
            sql.append("and resource_type in (").append(resourceTypes).append(") ");
        }
        if (StringUtils.isNotBlank((String)(sourceAddresses = (String)condition.get("SOURCE_ADDRESSES")))) {
            sql.append("and source_address in (").append(sourceAddresses).append(") ");
        }
        if (StringUtils.isNotBlank((String)(corporateType = (String)condition.get("CORPORATE_TYPE")))) {
            sql.append("and corporate_type in (").append(corporateType).append(") ");
        }
        if (StringUtils.isNotBlank((String)(deviceType = (String)condition.get("DEVICE_TYPE")))) {
            sql.append("and device_type in (").append(deviceType).append(") ");
        }

        Boolean isFuzzyQueryFileName = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_FILE_NAME");
        Object objectFileName = condition.get("FILE_NAME");
        if (isFuzzyQueryFileName != null && objectFileName != null) {
            String fileNameColumn = "";
            if (IncidentType.NETWORK.equals(incidentType) || IncidentType.ENDPOINT.equals(incidentType)) {
                fileNameColumn = "attachment_names";
            } else if (IncidentType.DISCOVERY.equals(incidentType)) {
                fileNameColumn = "file_name";
            }
            if (isFuzzyQueryFileName.booleanValue()) {
                List fileNames = (List)objectFileName;
                if (CollectionUtils.isNotEmpty((Collection)fileNames)) {
                    sql.append("and ").append(RegexpConvertor.match((String) fileNameColumn, (List) fileNames));
                }
            } else {
                String attachment_names = (String)objectFileName;
                if (StringUtils.isNotBlank((String)attachment_names)) {
                    sql.append("and ").append(fileNameColumn).append(" in (").append(attachment_names).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryDetailInfo = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_DETAIL_INFO");
        Object objectDetails = condition.get("DETAIL_INFO");
        if (isFuzzyQueryDetailInfo != null && objectDetails != null) {
            Object details;
            if (isFuzzyQueryDetailInfo.booleanValue()) {
                details = (List)objectDetails;
                if (CollectionUtils.isNotEmpty((Collection)details)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"details", (List)details));
                }
            } else {
                details = (String)objectDetails;
                if (StringUtils.isNotBlank((String)details)) {
                    sql.append("and details in (").append((String)details).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryIncidentTag = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_INCIDENT_TAG");
        Object objectIncidentTag = condition.get("INCIDENT_TAG");
        if (isFuzzyQueryIncidentTag != null && objectIncidentTag != null) {
            Object tagContents;
            if (isFuzzyQueryIncidentTag.booleanValue()) {
                tagContents = (List)objectIncidentTag;
                if (CollectionUtils.isNotEmpty((Collection)tagContents)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"tag_content", (List)tagContents));
                }
            } else {
                tagContents = (String)objectIncidentTag;
                if (StringUtils.isNotBlank((String)tagContents)) {
                    sql.append("and tag_content in (").append((String)tagContents).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryIrregularContent = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_IRREGULAR_CONTENT");
        Object objectIrregularContent = condition.get("IRREGULAR_CONTENT");
        if (isFuzzyQueryIrregularContent != null && objectIrregularContent != null) {
            Object breachContents;
            if (isFuzzyQueryIrregularContent.booleanValue()) {
                breachContents = (List)objectIrregularContent;
                if (CollectionUtils.isNotEmpty((Collection)breachContents)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"breach_contents", (List)breachContents));
                }
            } else {
                breachContents = (String)objectIrregularContent;
                if (StringUtils.isNotBlank((String)breachContents)) {
                    sql.append("and breach_contents in (").append((String)breachContents).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryHostname = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_HOST_NAME");
        Object objectHostname = condition.get("HOST_NAME");
        if (isFuzzyQueryHostname != null && objectHostname != null) {
            Object hostName;
            if (isFuzzyQueryHostname.booleanValue()) {
                hostName = (List)objectHostname;
                if (CollectionUtils.isNotEmpty((Collection)hostName)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"hostname", (List)hostName));
                }
            } else {
                hostName = (String)objectHostname;
                if (StringUtils.isNotBlank((String)hostName)) {
                    sql.append("and hostname in (").append((String)hostName).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryIpAddress = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_IP_ADDRESS");
        Object objectIpAddress = condition.get("IP_ADDRESS");
        if (isFuzzyQueryIpAddress != null && objectIpAddress != null) {
            Object ip;
            if (isFuzzyQueryIpAddress.booleanValue()) {
                ip = (List)objectIpAddress;
                if (CollectionUtils.isNotEmpty((Collection)ip)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"ip", (List)ip));
                }
            } else {
                ip = (String)objectIpAddress;
                if (StringUtils.isNotBlank((String)ip)) {
                    sql.append("and ip in (").append((String)ip).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryFilePath = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_FILE_PATH");
        Object objectFilePath = condition.get("FILE_PATH");
        if (isFuzzyQueryFilePath != null && objectFilePath != null) {
            Object filePath;
            if (isFuzzyQueryFilePath.booleanValue()) {
                filePath = (List)objectFilePath;
                if (CollectionUtils.isNotEmpty((Collection)filePath)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"file_path", (List)filePath));
                }
            } else {
                filePath = (String)objectFilePath;
                if (StringUtils.isNotBlank((String)filePath)) {
                    sql.append("and file_path in (").append((String)filePath).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryFolderPath = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_FOLDER_PATH");
        Object objectFolderPath = condition.get("FOLDER_PATH");
        if (isFuzzyQueryFolderPath != null && objectFolderPath != null) {
            Object folderPath;
            if (isFuzzyQueryFolderPath.booleanValue()) {
                folderPath = (List)objectFolderPath;
                if (CollectionUtils.isNotEmpty((Collection)folderPath)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"folder_path", (List)folderPath));
                }
            } else {
                folderPath = (String)objectFolderPath;
                if (StringUtils.isNotBlank((String)folderPath)) {
                    sql.append("and folder_path in (").append((String)folderPath).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryFileOwner = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_FILE_OWNER");
        Object objectFilerOwner = condition.get("FILE_OWNER");
        if (isFuzzyQueryFileOwner != null && objectFilerOwner != null) {
            Object fileOwner;
            if (isFuzzyQueryFileOwner.booleanValue()) {
                fileOwner = (List)objectFilerOwner;
                if (CollectionUtils.isNotEmpty((Collection)fileOwner)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"file_owner", (List)fileOwner));
                }
            } else {
                fileOwner = (String)objectFilerOwner;
                if (StringUtils.isNotBlank((String)fileOwner)) {
                    sql.append("and file_owner in (").append((String)fileOwner).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryFolderOwner = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_FOLDER_OWNER");
        Object objectFolderOwner = condition.get("FOLDER_OWNER");
        if (isFuzzyQueryFolderOwner != null && objectFolderOwner != null) {
            Object folderOwner;
            if (isFuzzyQueryFolderOwner.booleanValue()) {
                folderOwner = (List)objectFolderOwner;
                if (CollectionUtils.isNotEmpty((Collection)folderOwner)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"folder_owner", (List)folderOwner));
                }
            } else {
                folderOwner = (String)objectFolderOwner;
                if (StringUtils.isNotBlank((String)folderOwner)) {
                    sql.append("and folder_owner in (").append((String)folderOwner).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryFileExtension = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_FILE_EXTENSION");
        Object objectFileExtension = condition.get("FILE_EXTENSION");
        if (isFuzzyQueryFileExtension != null && objectFileExtension != null) {
            Object fileExtension;
            if (isFuzzyQueryFileExtension.booleanValue()) {
                fileExtension = (List)objectFileExtension;
                if (CollectionUtils.isNotEmpty((Collection)fileExtension)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"file_extension", (List)fileExtension));
                }
            } else {
                fileExtension = (String)objectFileExtension;
                if (StringUtils.isNotBlank((String)fileExtension)) {
                    sql.append("and file_extension in (").append((String)fileExtension).append(") ");
                }
            }
        }
        Boolean isFuzzyQueryOperationSystem = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_OPERATION_SYSTEM");
        Object objectOperationSystem = condition.get("OPERATION_SYSTEM");
        if (isFuzzyQueryOperationSystem != null && objectOperationSystem != null) {
            if (isFuzzyQueryOperationSystem.booleanValue()) {
                List operationSystems = (List)objectOperationSystem;
                if (CollectionUtils.isNotEmpty((Collection)operationSystems)) {
                    sql.append("and ").append(RegexpConvertor.match((String)"operation_system", (List)operationSystems));
                }
            } else {
                String operationSystems = (String)objectOperationSystem;
                if (StringUtils.isNotBlank((String)operationSystems)) {
                    sql.append("and operation_system in (").append(operationSystems).append(") ");
                }
            }
        }
    }

    public boolean isPolicyRequired(boolean isPolicy, Map<String, Object> condition) {
        String policyGroup = (String)condition.get("POLICY_GROUPS");
        return isPolicy || StringUtils.isNotBlank((String)policyGroup);
    }

    protected abstract void buildAllCommonCondition(Map<String, Object> var1, StringBuilder var2, List<Object> var3);
}

