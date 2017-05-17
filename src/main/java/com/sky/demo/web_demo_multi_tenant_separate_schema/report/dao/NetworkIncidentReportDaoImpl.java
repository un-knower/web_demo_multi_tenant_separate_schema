/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.sky.sps.base.common.echarts.base.EchartsForm
 *  com.sky.sps.base.utils.convertor.sqlcondition.RegexpConvertor
 *  com.sky.sps.dlp.common.incident.dm.network.NetworkIncident
 *  com.sky.sps.dlp.common.report.dm.IncidentType
 *  org.apache.commons.collections.CollectionUtils
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.jdbc.core.BeanPropertyRowMapper
 *  org.springframework.jdbc.core.RowMapper
 *  org.springframework.stereotype.Repository
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao;

import com.google.common.collect.Lists;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network.NetworkIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RegexpConvertor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class NetworkIncidentReportDaoImpl
extends AbstractBaseIncidentReportDao
implements NetworkIncidentReportDao {

    @Override
    public List<EchartsForm> selectListOfPolicy(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.policy_name as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.policy_name as policy_name from ");
        this.buildPolicyTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")policy ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildPolicyTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on policy.policy_name = incident.policy_name ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfSource(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.source_common_name as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.source_entry_id as source_entry_id from ");
        this.buildSourceTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")source ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildSourceTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on source.source_entry_id = incident.source_entry_id ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfDestination(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.destination_common_name as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.destination_common_name as destination_common_name from ");
        this.buildDestinationTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")destination ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildDestinationTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on destination.destination_common_name = incident.destination_common_name ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfChannelType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.channel_type as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.channel_type as channel_type from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")channel ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildCommonTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on channel.channel_type = incident.channel_type ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfSeverityType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as y, tb.severity_type as z ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfActionType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select tb.action_type as x, count(*) as y, tb.severity_type as z ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfPolicyGroup(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.name as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.name as name from ");
        this.buildPolicyGroupTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")groups ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildPolicyGroupTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on groups.name = incident.name ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfStatusType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select tb.status_type as x, count(*) as y, tb.severity_type as z ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectTrendListOfSeverityType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select tb.detect_time as x, count(*) as y, tb.severity_type as z ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectTrendListOfPolicyDetectTime(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.detect_time as x, count(*) as y, incident.policy_name as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.policy_name as policy_name from ");
        this.buildPolicyTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")policy ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildPolicyTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on policy.policy_name = incident.policy_name ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<NetworkIncident> selectListOfNetworkIncident(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select tb.*, ").append("ent.common_name AS source_entry_name, acc.logincode AS assigned_to_name, ent.entry_uuid, ").append("ent.app_uuid, ent.common_name, ent.distinguished_name, ent.full_name, ent.logon_name, ent.department, ").append("ent.manager, ent.title, ent.mobile, ent.telephone, ent.mail, ent.username, ent.ip, ent.hostname, ").append("ent.domain, ent.device_name, ent.app_name, ent.entry_type, ent.country_code,  ent.region_code, ent.city_code ").append("from ");
        this.buildCommonTb(condition, params, sql);
        sql.append("inner join dlp.idt_incident_entries_info as ent ");
        sql.append("on tb.source_entry_id = ent.id ");
        sql.append("left join sys_account as acc ");
        sql.append("on tb.assigned_to = acc.id ");
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        this.buildOffset(condition, params, sql);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)new NetworkIncidentRowMapper());
        return null;
    }

    @Override
    public int selectCountOfNetworkIncident(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from ");
        this.buildCommonTb(condition, params, sql);
        sql.append("inner join dlp.idt_incident_entries_info as ent ");
        sql.append("on tb.source_entry_id = ent.id ");
        sql.append("left join sys_account as acc ");
        sql.append("on tb.assigned_to = acc.id ");
//        int count = this.queryForInt(sql.toString(), params.toArray());
        return 0;
    }

    @Override
    public NetworkIncident selectNetworkIncident(Map<String, Object> var1) {
        return null;
    }



    private void buildPolicyTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_network_incidents.*, idt_network_policies.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        boolean policyRequired = this.isPolicyRequired(true, condition);
        this.buildPolicyCondition(condition, sql, policyRequired);
        this.buildPolicyGroupCondition(condition, sql, false);
        this.buildSourceCondition(condition, sql, false);
        this.buildDestinationCondition(condition, sql, false);
        sql.append(")tb ");
    }

    private void buildSourceTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_network_incidents.*, idt_incident_entries_info_source.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        boolean policyRequired = this.isPolicyRequired(false, condition);
        this.buildPolicyCondition(condition, sql, policyRequired);
        this.buildPolicyGroupCondition(condition, sql, false);
        this.buildSourceCondition(condition, sql, true);
        this.buildDestinationCondition(condition, sql, false);
        sql.append(")tb ");
    }

    private void buildDestinationTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_network_incidents.*, idt_incident_entries_info_destination.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        boolean policyRequired = this.isPolicyRequired(false, condition);
        this.buildPolicyCondition(condition, sql, policyRequired);
        this.buildPolicyGroupCondition(condition, sql, false);
        this.buildSourceCondition(condition, sql, false);
        this.buildDestinationCondition(condition, sql, true);
        sql.append(")tb ");
    }

    private void buildPolicyGroupTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_network_incidents.*, plc_groups.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        boolean policyRequired = this.isPolicyRequired(true, condition);
        this.buildPolicyCondition(condition, sql, policyRequired);
        this.buildPolicyGroupCondition(condition, sql, true);
        this.buildSourceCondition(condition, sql, false);
        this.buildDestinationCondition(condition, sql, false);
        sql.append(")tb ");
    }

    private void buildCommonTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_network_incidents.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        boolean policyRequired = this.isPolicyRequired(false, condition);
        this.buildPolicyCondition(condition, sql, policyRequired);
        this.buildPolicyGroupCondition(condition, sql, false);
        this.buildSourceCondition(condition, sql, false);
        this.buildDestinationCondition(condition, sql, false);
        sql.append(")tb ");
    }

    private void buildPolicyCondition(Map<String, Object> condition, StringBuilder sql, boolean isColumnContain) {
        String policyName = (String)condition.get("POLICIES");
        if (isColumnContain || StringUtils.isNotBlank((String)policyName)) {
            sql.append("inner join ");
            sql.append("(select incident_id, policy_uuid, policy_name ").append("from dlp.idt_network_policies ").append("where 1 = 1 ");
            sql.append("and is_visible = true ");
            if (StringUtils.isNotBlank((String)policyName)) {
                sql.append("and policy_name in(").append(policyName).append(") ");
            }
            sql.append(")idt_network_policies ");
            sql.append("on idt_network_incidents.id = idt_network_policies.incident_id ");
        }
    }

    private void buildSourceCondition(Map<String, Object> condition, StringBuilder sql, boolean isColumnContain) {
        Boolean isFuzzyQueryCustumSource = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_CUSTOM_SOURCES");
        Object objectCustomSources = condition.get("CUSTOM_SOURCES");
        String sources = (String)condition.get("SOURCES");
        if (isColumnContain || objectCustomSources != null || StringUtils.isNotBlank((String)sources)) {
            sql.append(" inner join ");
            sql.append("(select id as source_id, common_name as source_common_name ").append("from dlp.idt_incident_entries_info ").append("where 1 = 1 ");
            if (StringUtils.isNotBlank((String)sources)) {
                sql.append("and entry_uuid in (").append(sources).append(") ");
            }
            if (isFuzzyQueryCustumSource != null && isFuzzyQueryCustumSource.booleanValue()) {
                List customSources = (List)objectCustomSources;
                if (CollectionUtils.isNotEmpty((Collection)customSources)) {
                    sql.append(" and (").append(RegexpConvertor.match((String) "full_name", (List) customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"logon_name", (List)customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"mail", (List)customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"username", (List)customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"ip", (List)customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"hostname", (List)customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"device_name", (List)customSources));
                    sql.append(" or ").append(RegexpConvertor.match((String)"domain", (List)customSources));
                    sql.append(") ");
                }
            } else {
                String customSources = (String)objectCustomSources;
                if (StringUtils.isNotBlank((String)customSources)) {
                    sql.append("and common_name in (").append(customSources).append(") ");
                }
            }
            sql.append(")idt_incident_entries_info_source ");
            sql.append("on idt_network_incidents.source_entry_id = idt_incident_entries_info_source.source_id ");
        }
    }

    private void buildDestinationCondition(Map<String, Object> condition, StringBuilder sql, boolean isColumnContain) {
        Boolean isFuzzyQueryDestination = (Boolean)condition.get("IS_FUZZY_QUERY_FILTER_KEY_DESTINATIONS");
        Object objectDestination = condition.get("DESTINATIONS");
        if (isColumnContain || objectDestination != null) {
            sql.append("inner join ");
            sql.append("(select incident_id, destination_entry_id ").append("from dlp.idt_network_destinations )idt_network_destinations ");
            sql.append("on idt_network_incidents.id = idt_network_destinations.incident_id ");
            sql.append("inner join ");
            sql.append("(select id as destination_id, common_name as destination_common_name ").append("from dlp.idt_incident_entries_info ").append("where 1 = 1 ");
            if (isFuzzyQueryDestination != null && isFuzzyQueryDestination.booleanValue()) {
                List destinations = (List)objectDestination;
                if (CollectionUtils.isNotEmpty((Collection)destinations)) {
                    sql.append(" and (").append(RegexpConvertor.match((String)"full_name", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"logon_name", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"mail", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"username", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"ip", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"hostname", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"device_name", (List)destinations));
                    sql.append(" or ").append(RegexpConvertor.match((String)"domain", (List)destinations));
                    sql.append(") ");
                }
            } else {
                String destinations = (String)objectDestination;
                if (StringUtils.isNotBlank((String)destinations)) {
                    sql.append("and common_name in (").append(destinations).append(") ");
                }
            }
            sql.append(")idt_incident_entries_info_destination ");
            sql.append("on idt_network_destinations.destination_entry_id = idt_incident_entries_info_destination.destination_id ");
        }
    }

    private void buildPolicyGroupCondition(Map<String, Object> condition, StringBuilder sql, boolean isColumnContain) {
        String policyGroup = (String)condition.get("POLICY_GROUPS");
        if (isColumnContain || StringUtils.isNotBlank((String)policyGroup)) {
            sql.append("inner join ");
            sql.append("(select uuid, policy_group from dlp.plc_policies )plc_policies ");
            sql.append("on idt_network_policies.policy_uuid = plc_policies.uuid ");
            sql.append("inner join ");
            sql.append("(select id, name ").append("from dlp.plc_groups ").append("where 1 = 1 ");
            policyGroup = (String)condition.get("POLICY_GROUPS");
            if (StringUtils.isNotBlank((String)policyGroup)) {
                sql.append("and name in (").append(policyGroup).append(") ");
            }
            sql.append(")plc_groups ");
            sql.append("on plc_policies.policy_group = plc_groups.id ");
        }
    }

    @Override
    protected void buildAllCommonCondition(Map<String, Object> condition, StringBuilder sql, List<Object> params) {
        sql.append("(select ");
        String columnSelected = (String)condition.get("COLUMN_SELECTED");
        if (StringUtils.isNotBlank((String)columnSelected)) {
            sql.append(columnSelected);
        } else {
            sql.append("id, severity_type, source_entry_id, channel_type, action_type, status_type, detect_time::date as detect_time ");
        }
        sql.append("from dlp.idt_network_incidents ").append("where 1 = 1 ");
        sql.append("and is_visible = true ");
        this.buildCommonCondition(condition, sql, params, IncidentType.NETWORK);
        sql.append(")idt_network_incidents ");
    }
}

