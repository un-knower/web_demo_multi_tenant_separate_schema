package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.endpoint;

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
public class EndpointIncidentForm extends BaseIncidentForm implements Serializable {

    private static final long serialVersionUID = 6086739996748250034L;
    private String details;
    private int channelTypeCode;
    private String destinations;
    private String sourceEntryName;
    private String attachmentNames;
    private boolean hasAttachment;
    private boolean hasForensics;
    private String directionTypes;      // ;
    private String directionNames;
    private int operationTypeCode;
    private int deviceTypeCode;
    private int corporateTypeCode;
    private String operationSystem;
    private String hostname;
    private boolean isVisible;
    private IncidentEntryInfoForm sourceEntryInfo;
    private List<IncidentDestinationForm> incidentDestinations = Lists.newArrayList();
    private List<IncidentAttachmentForm> incidentAttachments = Lists.newArrayList();

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getChannelTypeCode() {
        return channelTypeCode;
    }

    public void setChannelTypeCode(int channelTypeCode) {
        this.channelTypeCode = channelTypeCode;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getSourceEntryName() {
        return sourceEntryName;
    }

    public void setSourceEntryName(String sourceEntryName) {
        this.sourceEntryName = sourceEntryName;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public boolean isHasForensics() {
        return hasForensics;
    }

    public void setHasForensics(boolean hasForensics) {
        this.hasForensics = hasForensics;
    }

    public String getDirectionTypes() {
        return directionTypes;
    }

    public void setDirectionTypes(String directionTypes) {
        this.directionTypes = directionTypes;
    }

    public String getDirectionNames() {
        return directionNames;
    }

    public void setDirectionNames(String directionNames) {
        this.directionNames = directionNames;
    }

    public int getOperationTypeCode() {
        return operationTypeCode;
    }

    public void setOperationTypeCode(int operationTypeCode) {
        this.operationTypeCode = operationTypeCode;
    }

    public int getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(int deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public int getCorporateTypeCode() {
        return corporateTypeCode;
    }

    public void setCorporateTypeCode(int corporateTypeCode) {
        this.corporateTypeCode = corporateTypeCode;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public IncidentEntryInfoForm getSourceEntryInfo() {
        return sourceEntryInfo;
    }

    public void setSourceEntryInfo(IncidentEntryInfoForm sourceEntryInfo) {
        this.sourceEntryInfo = sourceEntryInfo;
    }

    public List<IncidentDestinationForm> getIncidentDestinations() {
        return incidentDestinations;
    }

    public void setIncidentDestinations(List<IncidentDestinationForm> incidentDestinations) {
        this.incidentDestinations = incidentDestinations;
    }

    public List<IncidentAttachmentForm> getIncidentAttachments() {
        return incidentAttachments;
    }

    public void setIncidentAttachments(List<IncidentAttachmentForm> incidentAttachments) {
        this.incidentAttachments = incidentAttachments;
    }

    @Override
    public String toString() {
        return "EndpointIncidentForm{" +
                "details='" + details + '\'' +
                ", channelTypeCode=" + channelTypeCode +
                ", destinations='" + destinations + '\'' +
                ", sourceEntryName='" + sourceEntryName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", hasForensics=" + hasForensics +
                ", directionTypes='" + directionTypes + '\'' +
                ", directionNames='" + directionNames + '\'' +
                ", operationTypeCode=" + operationTypeCode +
                ", deviceTypeCode=" + deviceTypeCode +
                ", corporateTypeCode=" + corporateTypeCode +
                ", operationSystem='" + operationSystem + '\'' +
                ", hostname='" + hostname + '\'' +
                ", isVisible=" + isVisible +
                ", sourceEntryInfo=" + sourceEntryInfo +
                ", incidentDestinations=" + incidentDestinations +
                ", incidentAttachments=" + incidentAttachments +
                "} " + super.toString();
    }


    public static void main(String[] args) {
        EndpointIncidentForm incidentForm = new EndpointIncidentForm();

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


        //endpoint
        incidentForm.setDetails("details");
        incidentForm.setChannelTypeCode(1);
        incidentForm.setDestinations("destiantions");
        incidentForm.setSourceEntryName("zhangsan");
        incidentForm.setAttachmentNames("attachment");
        incidentForm.setHasAttachment(true);
        incidentForm.setHasForensics(false);
        incidentForm.setDirectionTypes("type1;type2");
        incidentForm.setDirectionNames("name1");
        incidentForm.setOperationTypeCode(1);
        incidentForm.setDeviceTypeCode(1);
        incidentForm.setCorporateTypeCode(1);
        incidentForm.setOperationSystem("linux");
        incidentForm.setHostname("hostname");

        incidentForm.setVisible(true);

        IncidentEntryInfoForm sourceEntryInfo = new IncidentEntryInfoForm();
        sourceEntryInfo.setEntryUuid(UUID.randomUUID().toString());
        sourceEntryInfo.setAppUuid(UUID.randomUUID().toString());
        sourceEntryInfo.setCommonName("common name");
        sourceEntryInfo.setDistinguishedName("dn name");
        sourceEntryInfo.setFullName("full name");
        sourceEntryInfo.setLogonName("logon name");
        sourceEntryInfo.setDepartment("department");
        sourceEntryInfo.setManager("zhangsan");
        sourceEntryInfo.setTitle("title");
        sourceEntryInfo.setMobile("10010");
        sourceEntryInfo.setTelephone("18812345678");
        sourceEntryInfo.setMail("zhangsan@163.com");
        sourceEntryInfo.setUsername("zhangsan");
        sourceEntryInfo.setIp("192.200.11.1");
        sourceEntryInfo.setHostname("windows");
        sourceEntryInfo.setDeviceName("device");
        sourceEntryInfo.setAppName("app name");
        sourceEntryInfo.setEntryType(1);
        sourceEntryInfo.setCountryCode("CN");
        sourceEntryInfo.setRegionCode("PEK");
        sourceEntryInfo.setCityCode("PEK");
        sourceEntryInfo.setUrl("url");
        incidentForm.setSourceEntryInfo(sourceEntryInfo);

        List<IncidentDestinationForm> incidentDestinations = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            IncidentDestinationForm incidentDestinationForm = new IncidentDestinationForm();
            incidentDestinationForm.setDestinationEntryName("entry name");
            incidentDestinationForm.setDestinationTypeCode(1);
            incidentDestinationForm.setActionTypeCode(1);
            incidentDestinationForm.setReleased(true);
            incidentDestinationForm.setReleasedBy("lisi");
            incidentDestinationForm.setLocaleReleaseTime("2016-05-30T13:20:05.326594");
            incidentDestinationForm.setDirectionTypeCode(1);

            IncidentEntryInfoForm destinationEntryInfo = new IncidentEntryInfoForm();
            destinationEntryInfo.setEntryUuid(UUID.randomUUID().toString());
            destinationEntryInfo.setAppUuid(UUID.randomUUID().toString());
            destinationEntryInfo.setCommonName("common name");
            destinationEntryInfo.setDistinguishedName("dn name");
            destinationEntryInfo.setFullName("full name");
            destinationEntryInfo.setLogonName("logon name");
            destinationEntryInfo.setDepartment("department");
            destinationEntryInfo.setManager("zhangsan");
            destinationEntryInfo.setTitle("title");
            destinationEntryInfo.setMobile("10010");
            destinationEntryInfo.setTelephone("18812345678");
            destinationEntryInfo.setMail("zhangsan@163.com");
            destinationEntryInfo.setUsername("zhangsan");
            destinationEntryInfo.setIp("192.200.11.1");
            destinationEntryInfo.setHostname("windows");
            destinationEntryInfo.setDeviceName("device");
            destinationEntryInfo.setAppName("app name");
            destinationEntryInfo.setEntryType(1);
            destinationEntryInfo.setCountryCode("CN");
            destinationEntryInfo.setRegionCode("PEK");
            destinationEntryInfo.setCityCode("PEK");
            destinationEntryInfo.setUrl("url");
            incidentDestinationForm.setDestinationEntryInfo(destinationEntryInfo);

            List<String> incidentDropAttachments = Lists.newArrayList();
            for (int j = 0; j < 2; j++) {
                incidentDropAttachments.add("/root/" + j);
            }
            incidentDestinationForm.setIncidentDropAttachments(incidentDropAttachments);

            incidentDestinations.add(incidentDestinationForm);
        }
        incidentForm.setIncidentDestinations(incidentDestinations);

        List<IncidentAttachmentForm> incidentAttachments = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            IncidentAttachmentForm incidentAttachmentForm = new IncidentAttachmentForm();
            incidentAttachmentForm.setFileName("file" + i);
            incidentAttachmentForm.setFileSize(1024);
            incidentAttachmentForm.setFileType(1);

            incidentAttachments.add(incidentAttachmentForm);
        }
        incidentForm.setIncidentAttachments(incidentAttachments);


        //print
        String json = JsonUtil.writeValueAsString(incidentForm);
        System.out.println(json);
    }
}
