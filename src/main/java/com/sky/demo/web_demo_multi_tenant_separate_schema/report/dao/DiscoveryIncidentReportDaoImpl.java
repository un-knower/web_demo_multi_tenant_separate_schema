/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.sky.sps.base.common.echarts.base.EchartsForm
 *  com.sky.sps.dlp.common.incident.dm.discovery.DiscoveryIncident
 *  com.sky.sps.dlp.common.report.dm.IncidentType
 *  com.sky.sps.dlp.common.report.dm.dto.DiscoveryIncidentOption
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.jdbc.core.BeanPropertyRowMapper
 *  org.springframework.jdbc.core.RowMapper
 *  org.springframework.stereotype.Repository
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.dao;

import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.common.IncidentType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.discovery.DiscoveryIncident;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.EchartsForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.report.dm.dto.DiscoveryIncidentOption;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DiscoveryIncidentReportDaoImpl
extends AbstractBaseIncidentReportDao
implements DiscoveryIncidentReportDao {
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
    public List<EchartsForm> selectListOfResourceType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.resource_type as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.resource_type as resource_type from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")resource ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildCommonTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on resource.resource_type = incident.resource_type ");
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
    public List<EchartsForm> selectListOfTask(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select incident.job_name as x, count(*) as y, incident.severity_type as z ").append("from ");
        sql.append("(select count(*) as cnt, tb.job_name as job_name from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        sql.append(")task ");
        sql.append("inner join ");
        sql.append("(select * from ");
        this.buildCommonTb(condition, params, sql);
        sql.append(")incident ");
        sql.append("on task.job_name = incident.job_name ");
        this.buildIncidentGroupBy(condition, sql);
        this.buildIncidentOrderBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(EchartsForm.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<EchartsForm> selectListOfDiscoveryStatistics(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select name as x, cnt as y, type as z ").append("from ( ");
        sql.append("select sum(hit_amount) as cnt, name, 'sum_hit_amount' as type from dlp.discovery_scan_result where 1 = 1 ");
        this.buildStatisticCondition(condition, sql, params);
        this.buildGroupBy(condition, sql);
        sql.append(" union all ");
        sql.append("select sum(scanned_amount) as cnt, name, 'sum_scanned_amount' as type from dlp.discovery_scan_result where 1 = 1 ");
        this.buildStatisticCondition(condition, sql, params);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        sql.append(")tb ");
        sql.append("order by type asc ");
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
    public List<DiscoveryIncidentOption> selectListOfTaskName(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as cnt, tb.job_name as job_name ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildGroupBy(condition, sql);
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(DiscoveryIncidentOption.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<DiscoveryIncidentOption> selectListOfTaskNameAndResourceType(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select tb.job_name as jobName, tb.resource_type as resourceType ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildIncidentGroupBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(DiscoveryIncidentOption.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<DiscoveryIncidentOption> selectListOfSourceAddress(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as cnt, tb.source_address as sourceAddress ").append("from ");
        this.buildCommonTb(condition, params, sql);
        this.buildIncidentGroupBy(condition, sql);
        BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(DiscoveryIncidentOption.class);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)rowMapper);
        return null;
    }

    @Override
    public List<DiscoveryIncident> selectListOfDiscoveryIncident(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select tb.*, acc.logincode AS assigned_to_name ").append("from ");
        this.buildCommonTb(condition, params, sql);
        sql.append("left join sys_account as acc ");
        sql.append("on tb.assigned_to = acc.id ");
        this.buildOrderBy(condition, sql);
        this.buildLimit(condition, params, sql);
        this.buildOffset(condition, params, sql);
//        List result = this.queryObjectList(sql.toString(), params.toArray(), (RowMapper)new DiscoveryIncidentRowMapper());
        return null;
    }

    @Override
    public int selectCountOfDiscoveryIncident(Map<String, Object> condition) {
        ArrayList params = Lists.newArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from ");
        this.buildCommonTb(condition, params, sql);
        sql.append("left join sys_account as acc ");
        sql.append("on tb.assigned_to = acc.id ");
//        int count = this.queryForInt(sql.toString(), params.toArray());
        return 0;
    }

    private void buildPolicyTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_discovery_incidents.*, idt_discovery_policies.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        this.buildPolicyCondition(condition, sql, true);
        sql.append(")tb ");
    }

    private void buildCommonTb(Map<String, Object> condition, List<Object> params, StringBuilder sql) {
        sql.append("(select distinct idt_discovery_incidents.* from ");
        this.buildAllCommonCondition(condition, sql, params);
        this.buildPolicyCondition(condition, sql, false);
        sql.append(")tb ");
    }

    private void buildPolicyCondition(Map<String, Object> condition, StringBuilder sql, boolean isColumnContain) {
        String policyName = (String)condition.get("POLICIES");
        if (isColumnContain || StringUtils.isNotBlank((String)policyName)) {
            sql.append("inner join ");
            sql.append("(select incident_id, policy_uuid, policy_name ").append("from dlp.idt_discovery_policies ").append("where 1 = 1 ");
            policyName = (String)condition.get("POLICIES");
            if (StringUtils.isNotBlank((String)policyName)) {
                sql.append("and policy_name in(").append(policyName).append(") ");
            }
            sql.append(")idt_discovery_policies ");
            sql.append("on idt_discovery_incidents.id = idt_discovery_policies.incident_id ");
        }
    }

    private void buildStatisticCondition(Map<String, Object> condition, StringBuilder sql, List<Object> params) {
        String endDate;
        String task;
        String beginDate = (String)condition.get("BEGIN_DATE");
        if (StringUtils.isNotBlank((String)beginDate)) {
            sql.append("and stop_time > ? ");
            params.add(Timestamp.valueOf(beginDate));
        }
        if (StringUtils.isNotBlank((String)(endDate = (String)condition.get("END_DATE")))) {
            sql.append("and stop_time <= ? ");
            params.add(Timestamp.valueOf(endDate));
        }
        if (StringUtils.isNotBlank((String)(task = (String)condition.get("TASKS")))) {
            sql.append("and name in (").append(task).append(") ");
        }
    }

    @Override
    protected void buildAllCommonCondition(Map<String, Object> condition, StringBuilder sql, List<Object> params) {
        sql.append("(select ");
        String columnSelected = (String)condition.get("COLUMN_SELECTED");
        if (StringUtils.isNotBlank((String)columnSelected)) {
            sql.append(columnSelected);
        } else {
            sql.append("id, severity_type, resource_type, job_name, status_type, source_address ");
        }
        sql.append("from dlp.idt_discovery_incidents ").append("where 1 = 1 ");
        this.buildCommonCondition(condition, sql, params, IncidentType.DISCOVERY);
        sql.append(")idt_discovery_incidents ");
    }
}

