package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.msg;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentBreachContentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentElementForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.common.IncidentPolicyForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.discovery.DiscoveryIncidentForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 16/10/20.
 */
public class DiscoveryIncidentMsg extends BaseMsg implements Serializable {

    private static final long serialVersionUID = -8950044609734877547L;
    private DiscoveryIncidentForm incident;

    public DiscoveryIncidentForm getIncident() {
        return incident;
    }

    public void setIncident(DiscoveryIncidentForm incident) {
        this.incident = incident;
    }

    @Override
    public String toString() {
        return "DiscoveryIncidentMsg{" +
                "incident=" + incident +
                "} " + super.toString();
    }

    public static void main(String[] args) {
        DiscoveryIncidentMsg msg = new DiscoveryIncidentMsg();
        msg.setTenantCode("tenant1");
        msg.setTenantType("vip");
        msg.setIncidentType("discovery");

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

        msg.setIncident(incidentForm);


        //print
        String json = JsonUtil.writeValueAsString(msg);
        System.out.println(json);

    }
}

