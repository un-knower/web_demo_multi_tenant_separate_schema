package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.msg;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentBreachContentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentElementForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentPolicyForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.info.IncidentEntryInfoForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.IncidentAttachmentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.IncidentDestinationForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.network.NetworkIncidentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 16/10/20.
 */
public class NetworkIncidentMsg extends BaseMsg implements Serializable {

    private static final long serialVersionUID = 3747145342823851494L;
    private NetworkIncidentForm incident;

    public NetworkIncidentForm getIncident() {
        return incident;
    }

    public void setIncident(NetworkIncidentForm incident) {
        this.incident = incident;
    }

    @Override
    public String toString() {
        return "NetworkIncidentMsg{" +
                "incident=" + incident +
                "} " + super.toString();
    }

    public static void main(String[] args) {

        NetworkIncidentMsg msg = new NetworkIncidentMsg();
        msg.setTenantCode("tenant1");
        msg.setTenantType("vip");
        msg.setIncidentType("network");

        NetworkIncidentForm incidentForm = new NetworkIncidentForm();
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


        //network
        incidentForm.setDetails("details");
        incidentForm.setChannelTypeCode(1);
        incidentForm.setDestinations("destiantions");
        incidentForm.setSourceEntryName("zhangsan");
        incidentForm.setAttachmentNames("attachment");
        incidentForm.setHasAttachment(true);
        incidentForm.setReleased(true);
        incidentForm.setHasForensics(false);
        incidentForm.setWorkModeTypeCode(1);
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

        msg.setIncident(incidentForm);


        //print
        String json = JsonUtil.writeValueAsString(msg);
        System.out.println(json);
    }
}
