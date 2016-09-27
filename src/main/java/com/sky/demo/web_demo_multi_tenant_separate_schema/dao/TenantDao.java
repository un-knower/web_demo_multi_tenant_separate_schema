package com.sky.demo.web_demo_multi_tenant_separate_schema.dao;

import java.util.List;
import java.util.Map;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Tenant;

/**
 * Created by user on 16/9/18.
 */
public interface TenantDao {

    Tenant select(Map<String, Object> condition);

    List<Tenant> selectList(Map<String, Object> condition);

    int selectCount(Map<String, Object> condition);

    int insert(Tenant record);

    int batchInsert(List<Tenant> records);

    int update(Tenant record);

    int batchUpdate(List<Tenant> records);

    int delete(final int id);

    int batchDelete(List<Integer> ids);
}
