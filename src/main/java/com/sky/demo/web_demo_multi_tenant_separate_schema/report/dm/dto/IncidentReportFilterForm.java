/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 *  com.sky.sps.base.common.base.Relation
 *  com.sky.sps.base.utils.json.JsonUtils
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto;

import com.google.common.base.Objects;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;

import java.io.Serializable;

public class IncidentReportFilterForm
implements Serializable {

    private FilterIncidentIdForm incidentId;
    private FilterTransactionIdForm transactionId;
    private FilterTimeForm time;
    private FilterPolicyForm policy;
    private FilterSeverityForm severity;
    private FilterStatusForm status;
    private FilterMatchForm match;
    private FilterIgnoreForm ignore;
    private FilterDetectEngineForm detectEngine;
    private FilterChannelForm channel;
    private FilterSourceForm source;
    private FilterDestinationForm destination;
    private FilterReleaseForm release;
    private FilterActionForm action;
    private FilterTransactionSizeForm transactionSize;
    private FilterFileSizeForm fileSize;
    private FilterResourceTypeForm resourceType;
    private FilterTaskForm task;
    private FilterLockForm lock;
    private FilterIncidentTimeForm incidentTime;
    private FilterIncidentTagForm incidentTag;
    private FilterFileNameForm fileName;
    private FilterAnalyzeEngineForm analyzeEngine;
    private FilterIrregularContentForm irregularContent;
    private FilterDetailInfoForm detailInfo;
    private FilterWorkModeForm workMode;
    private FilterFilePathForm filePath;
    private FilterFolderPathForm folderPath;
    private FilterFileExtensionForm fileExtension;
    private FilterFileOwnerForm fileOwner;
    private FilterFolderOwnerForm folderOwner;
    private FilterHostNameForm hostName;
    private FilterIpAddressForm ipAddress;
    private FilterCorporateForm corporate;
    private FilterDeviceForm device;
    private FilterOperationSystemForm operationSystem;
    private FilterPolicyGroupForm policyGroup;

    public FilterIncidentIdForm getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(FilterIncidentIdForm incidentId) {
        this.incidentId = incidentId;
    }

    public FilterTransactionIdForm getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(FilterTransactionIdForm transactionId) {
        this.transactionId = transactionId;
    }

    public FilterTimeForm getTime() {
        return this.time;
    }

    public void setTime(FilterTimeForm time) {
        this.time = time;
    }

    public FilterPolicyForm getPolicy() {
        return this.policy;
    }

    public void setPolicy(FilterPolicyForm policy) {
        this.policy = policy;
    }

    public FilterSeverityForm getSeverity() {
        return this.severity;
    }

    public void setSeverity(FilterSeverityForm severity) {
        this.severity = severity;
    }

    public FilterStatusForm getStatus() {
        return this.status;
    }

    public void setStatus(FilterStatusForm status) {
        this.status = status;
    }

    public FilterMatchForm getMatch() {
        return this.match;
    }

    public void setMatch(FilterMatchForm match) {
        this.match = match;
    }

    public FilterIgnoreForm getIgnore() {
        return this.ignore;
    }

    public void setIgnore(FilterIgnoreForm ignore) {
        this.ignore = ignore;
    }

    public FilterDetectEngineForm getDetectEngine() {
        return this.detectEngine;
    }

    public void setDetectEngine(FilterDetectEngineForm detectEngine) {
        this.detectEngine = detectEngine;
    }

    public FilterChannelForm getChannel() {
        return this.channel;
    }

    public void setChannel(FilterChannelForm channel) {
        this.channel = channel;
    }

    public FilterSourceForm getSource() {
        return this.source;
    }

    public void setSource(FilterSourceForm source) {
        this.source = source;
    }

    public FilterDestinationForm getDestination() {
        return this.destination;
    }

    public void setDestination(FilterDestinationForm destination) {
        this.destination = destination;
    }

    public FilterReleaseForm getRelease() {
        return this.release;
    }

    public void setRelease(FilterReleaseForm release) {
        this.release = release;
    }

    public FilterActionForm getAction() {
        return this.action;
    }

    public void setAction(FilterActionForm action) {
        this.action = action;
    }

    public FilterTransactionSizeForm getTransactionSize() {
        return this.transactionSize;
    }

    public void setTransactionSize(FilterTransactionSizeForm transactionSize) {
        this.transactionSize = transactionSize;
    }

    public FilterFileSizeForm getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(FilterFileSizeForm fileSize) {
        this.fileSize = fileSize;
    }

    public FilterResourceTypeForm getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(FilterResourceTypeForm resourceType) {
        this.resourceType = resourceType;
    }

    public FilterTaskForm getTask() {
        return this.task;
    }

    public void setTask(FilterTaskForm task) {
        this.task = task;
    }

    public FilterLockForm getLock() {
        return this.lock;
    }

    public void setLock(FilterLockForm lock) {
        this.lock = lock;
    }

    public FilterIncidentTimeForm getIncidentTime() {
        return this.incidentTime;
    }

    public void setIncidentTime(FilterIncidentTimeForm incidentTime) {
        this.incidentTime = incidentTime;
    }

    public FilterIncidentTagForm getIncidentTag() {
        return this.incidentTag;
    }

    public void setIncidentTag(FilterIncidentTagForm incidentTag) {
        this.incidentTag = incidentTag;
    }

    public FilterFileNameForm getFileName() {
        return this.fileName;
    }

    public void setFileName(FilterFileNameForm fileName) {
        this.fileName = fileName;
    }

    public FilterAnalyzeEngineForm getAnalyzeEngine() {
        return this.analyzeEngine;
    }

    public void setAnalyzeEngine(FilterAnalyzeEngineForm analyzeEngine) {
        this.analyzeEngine = analyzeEngine;
    }

    public FilterIrregularContentForm getIrregularContent() {
        return this.irregularContent;
    }

    public void setIrregularContent(FilterIrregularContentForm irregularContent) {
        this.irregularContent = irregularContent;
    }

    public FilterDetailInfoForm getDetailInfo() {
        return this.detailInfo;
    }

    public void setDetailInfo(FilterDetailInfoForm detailInfo) {
        this.detailInfo = detailInfo;
    }

    public FilterWorkModeForm getWorkMode() {
        return this.workMode;
    }

    public void setWorkMode(FilterWorkModeForm workMode) {
        this.workMode = workMode;
    }

    public FilterFilePathForm getFilePath() {
        return this.filePath;
    }

    public void setFilePath(FilterFilePathForm filePath) {
        this.filePath = filePath;
    }

    public FilterFolderPathForm getFolderPath() {
        return this.folderPath;
    }

    public void setFolderPath(FilterFolderPathForm folderPath) {
        this.folderPath = folderPath;
    }

    public FilterFileExtensionForm getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(FilterFileExtensionForm fileExtension) {
        this.fileExtension = fileExtension;
    }

    public FilterFileOwnerForm getFileOwner() {
        return this.fileOwner;
    }

    public void setFileOwner(FilterFileOwnerForm fileOwner) {
        this.fileOwner = fileOwner;
    }

    public FilterFolderOwnerForm getFolderOwner() {
        return this.folderOwner;
    }

    public void setFolderOwner(FilterFolderOwnerForm folderOwner) {
        this.folderOwner = folderOwner;
    }

    public FilterHostNameForm getHostName() {
        return this.hostName;
    }

    public void setHostName(FilterHostNameForm hostName) {
        this.hostName = hostName;
    }

    public FilterIpAddressForm getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(FilterIpAddressForm ipAddress) {
        this.ipAddress = ipAddress;
    }

    public FilterCorporateForm getCorporate() {
        return this.corporate;
    }

    public void setCorporate(FilterCorporateForm corporate) {
        this.corporate = corporate;
    }

    public FilterDeviceForm getDevice() {
        return this.device;
    }

    public void setDevice(FilterDeviceForm device) {
        this.device = device;
    }

    public FilterOperationSystemForm getOperationSystem() {
        return this.operationSystem;
    }

    public void setOperationSystem(FilterOperationSystemForm operationSystem) {
        this.operationSystem = operationSystem;
    }

    public FilterPolicyGroupForm getPolicyGroup() {
        return this.policyGroup;
    }

    public void setPolicyGroup(FilterPolicyGroupForm policyGroup) {
        this.policyGroup = policyGroup;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("incidentId", (Object)this.incidentId).add("transactionId", (Object)this.transactionId).add("time", (Object)this.time).add("policy", (Object)this.policy).add("severity", (Object)this.severity).add("status", (Object)this.status).add("match", (Object)this.match).add("ignore", (Object)this.ignore).add("detectEngine", (Object)this.detectEngine).add("channel", (Object)this.channel).add("source", (Object)this.source).add("destination", (Object)this.destination).add("release", (Object)this.release).add("action", (Object)this.action).add("transactionSize", (Object)this.transactionSize).add("fileSize", (Object)this.fileSize).add("resourceType", (Object)this.resourceType).add("task", (Object)this.task).add("lock", (Object)this.lock).add("incidentTime", (Object)this.incidentTime).add("incidentTag", (Object)this.incidentTag).add("fileName", (Object)this.fileName).add("analyzeEngine", (Object)this.analyzeEngine).add("irregularContent", (Object)this.irregularContent).add("detailInfo", (Object)this.detailInfo).add("workMode", (Object)this.workMode).add("filePath", (Object)this.filePath).add("folderPath", (Object)this.folderPath).add("fileExtension", (Object)this.fileExtension).add("fileOwner", (Object)this.fileOwner).add("folderOwner", (Object)this.folderOwner).add("hostName", (Object)this.hostName).add("ipAddress", (Object)this.ipAddress).add("corporate", (Object)this.corporate).add("device", (Object)this.device).add("operationSystem", (Object)this.operationSystem).add("policyGroup", (Object)this.policyGroup).toString();
    }

    public static void main(String[] args) {
        String origin = "{\n\t\"incidentId\": {\n        \"enableFilter\": false,\n        \"ids\": \"\"\n    },\n\t\"transactionId\": {\n        \"enableFilter\": false,\n        \"ids\": \"\"\n    },\n    \"time\": {\n        \"enableFilter\": true,\n        \"timeType\": \"LAST_PAST_N_DAY\",\n        \"lastPastDay\": 30\n    },\n    \"policy\": {\n        \"enableFilter\": false,\n        \"policies\": [\n        ]\n    },\n    \"severity\": {\n        \"enableFilter\": false,\n        \"severities\": [\n        ]\n    },\n    \"status\": {\n        \"enableFilter\": false,\n        \"statuses\": [\n        ]\n    },\n    \"match\": {\n        \"enableFilter\": false,\n        \"lower\": 0,\n        \"upper\": 0\n    },\n    \"ignore\": {\n        \"enableFilter\": false,\n        \"ignoredType\": 0\n    },\n    \"detectEngine\": {\n        \"enableFilter\": false,\n        \"engines\": [\n        ]\n    },\n    \"fileSize\": {\n        \"enableFilter\": false,\n        \"lower\": 0,\n        \"upper\": 0\n    },\n    \"resourceType\": {\n        \"enableFilter\": false,\n        \"resources\": [\n        ]\n    },\n    \"task\": {\n        \"enableFilter\": false,\n        \"tasks\": [\n        ]\n    },\n    \"lock\": {\n        \"enableFilter\": false,\n        \"lockType\": 0\n    }\n}";
        IncidentReportFilterForm form = (IncidentReportFilterForm) JsonUtil.readValue((String) origin, IncidentReportFilterForm.class);
        String str = JsonUtil.writeValueAsString(form);
        System.out.println(str);
    }
}

