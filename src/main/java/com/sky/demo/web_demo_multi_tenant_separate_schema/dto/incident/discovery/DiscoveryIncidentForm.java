package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.discovery;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.BaseIncidentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentBreachContentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentElementForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentPolicyForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.info.IncidentEntryInfoForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.IncidentAttachmentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.IncidentDestinationForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 16/10/20.
 */
public class DiscoveryIncidentForm extends BaseIncidentForm implements Serializable {

    private static final long serialVersionUID = -7153559457884839740L;
    private boolean isLocked;
    private int resourceTypeCode;
    private String filePath;
    private String folderPath;
    private String fileName;
    private String fileExtension;
    private long fileSize;
    private int fileType;
    private String fileTypeName;
    private String fileOwner;
    private String folderOwner;
    private String localeFileCreatedTime;
    private String localeFileModifiedTime;
    private String localeFileAccessedTime;
    private String hostName;
    private String ip;
    private String sourceAddress;
    private String jobUuid;
    private String jobName;
    private int folderTypeCode;
    private int segmentId;
    private long segmentTag;
    private int deviceTypeCode;
    private String operationSystem;

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getResourceTypeCode() {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(int resourceTypeCode) {
        this.resourceTypeCode = resourceTypeCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFolderOwner() {
        return folderOwner;
    }

    public void setFolderOwner(String folderOwner) {
        this.folderOwner = folderOwner;
    }

    public String getLocaleFileCreatedTime() {
        return localeFileCreatedTime;
    }

    public void setLocaleFileCreatedTime(String localeFileCreatedTime) {
        this.localeFileCreatedTime = localeFileCreatedTime;
    }

    public String getLocaleFileModifiedTime() {
        return localeFileModifiedTime;
    }

    public void setLocaleFileModifiedTime(String localeFileModifiedTime) {
        this.localeFileModifiedTime = localeFileModifiedTime;
    }

    public String getLocaleFileAccessedTime() {
        return localeFileAccessedTime;
    }

    public void setLocaleFileAccessedTime(String localeFileAccessedTime) {
        this.localeFileAccessedTime = localeFileAccessedTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getFolderTypeCode() {
        return folderTypeCode;
    }

    public void setFolderTypeCode(int folderTypeCode) {
        this.folderTypeCode = folderTypeCode;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public long getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(long segmentTag) {
        this.segmentTag = segmentTag;
    }

    public int getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(int deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    @Override
    public String toString() {
        return "DiscoveryIncidentForm{" +
                "isLocked=" + isLocked +
                ", resourceTypeCode=" + resourceTypeCode +
                ", filePath='" + filePath + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                ", fileType=" + fileType +
                ", fileTypeName='" + fileTypeName + '\'' +
                ", fileOwner='" + fileOwner + '\'' +
                ", folderOwner='" + folderOwner + '\'' +
                ", localeFileCreatedTime='" + localeFileCreatedTime + '\'' +
                ", localeFileModifiedTime='" + localeFileModifiedTime + '\'' +
                ", localeFileAccessedTime='" + localeFileAccessedTime + '\'' +
                ", hostName='" + hostName + '\'' +
                ", ip='" + ip + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", jobUuid='" + jobUuid + '\'' +
                ", jobName='" + jobName + '\'' +
                ", folderTypeCode=" + folderTypeCode +
                ", segmentId=" + segmentId +
                ", segmentTag=" + segmentTag +
                ", deviceTypeCode=" + deviceTypeCode +
                ", operationSystem='" + operationSystem + '\'' +
                "} " + super.toString();
    }

    public static void main(String[] args) {
        DiscoveryIncidentForm incidentForm = new DiscoveryIncidentForm();

        incidentForm.setId(1234567890);
        incidentForm.setTransactionId(UUID.randomUUID().toString());
        incidentForm.setActionTypeCode(1);
        incidentForm.setSeverityTypeCode(1);
        incidentForm.setStatusTypeCode(1);
        incidentForm.setIgnored(true);
        incidentForm.setPolicyNames("policy1;policy2;policy3");
        incidentForm.setDetectedByName("engine");
        incidentForm.setTagContent("tag");
        incidentForm.setBreachContents("123;xxx;ddd");
        incidentForm.setLocaleDetectTime("2016-05-30T13:20:05.326594");
        incidentForm.setMaxMatches(3);
        incidentForm.setTransactionSize(1024);

        List<IncidentPolicyForm> incidentPolicies = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            IncidentPolicyForm policyForm = new IncidentPolicyForm();
            policyForm.setPolicyUuid(UUID.randomUUID().toString());
            policyForm.setPolicyName("policy" + i);
            policyForm.setRuleUuid(UUID.randomUUID().toString());
            policyForm.setRuleName("rule" + i);
            policyForm.setActionUuid(UUID.randomUUID().toString());
            policyForm.setActionName("action" + i);
            policyForm.setMatches(10);
            policyForm.setSeverityTypeCode(1);
            policyForm.setTrickle(false);
            policyForm.setVisible(true);

            List<IncidentElementForm> incidentElements = Lists.newArrayList();
            for (int j = 0; j < 2; j++) {

                IncidentElementForm incidentElementForm = new IncidentElementForm();
                incidentElementForm.setConditionUuid(UUID.randomUUID().toString());
                incidentElementForm.setElementUuid(UUID.randomUUID().toString());
                incidentElementForm.setElementName("element" + j);
                incidentElementForm.setElementTypeCode(1);
                incidentElementForm.setMatches(3);
                incidentElementForm.setTruncated(true);

                List<IncidentBreachContentForm> incidentBreachContents = Lists.newArrayList();
                for (int k = 0; k < 2; k++) {
                    IncidentBreachContentForm incidentBreachContentForm = new IncidentBreachContentForm();
                    incidentBreachContentForm.setContent("content" + k);
                    incidentBreachContentForm.setLocationTypeCode(1);
                    incidentBreachContentForm.setLocationTypesPath("/root");
                    incidentBreachContentForm.setLocationNamesPath("/root");
                    incidentBreachContentForm.setSimilarity(0.9734);
                    incidentBreachContentForm.setMatches(4);
                    incidentBreachContentForm.setFileType(1);

                    incidentBreachContents.add(incidentBreachContentForm);
                }
                incidentElementForm.setIncidentBreachContents(incidentBreachContents);

                incidentElements.add(incidentElementForm);
            }
            policyForm.setIncidentElements(incidentElements);

            incidentPolicies.add(policyForm);
        }
        incidentForm.setIncidentPolicies(incidentPolicies);


        //discovery
        incidentForm.setLocked(true);
        incidentForm.setResourceTypeCode(1);
        incidentForm.setFilePath("/root/tmp.txt");
        incidentForm.setFolderPath("/root");
        incidentForm.setFileName("tmp.txt");
        incidentForm.setFileExtension("txt");
        incidentForm.setFileSize(1234566);
        incidentForm.setFileType(1);
        incidentForm.setFileTypeName("txt");
        incidentForm.setFileOwner("zhangsan");
        incidentForm.setFolderOwner("root");
        incidentForm.setLocaleFileCreatedTime("2016-05-30T13:20:05.326594");
        incidentForm.setLocaleFileModifiedTime("2016-05-30T13:20:05.326594");
        incidentForm.setLocaleFileAccessedTime("2016-05-30T13:20:05.326594");
        incidentForm.setHostName("hostname");
        incidentForm.setIp("172.22.0.1");
        incidentForm.setSourceAddress("172.22.0.1");
        incidentForm.setJobUuid(UUID.randomUUID().toString());
        incidentForm.setJobName("job1");
        incidentForm.setFolderTypeCode(1);
        incidentForm.setSegmentId(1);
        incidentForm.setSegmentTag(1234);
        incidentForm.setDeviceTypeCode(1);
        incidentForm.setOperationSystem("linux");


        //print
        String json = JsonUtil.writeValueAsString(incidentForm);
        System.out.println(json);
    }

}
