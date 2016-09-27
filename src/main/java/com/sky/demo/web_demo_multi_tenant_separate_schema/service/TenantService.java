package com.sky.demo.web_demo_multi_tenant_separate_schema.service;

import java.util.List;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantQueryRequest;

/**
 * Created by user on 16/9/18.
 */
public interface TenantService {

    TenantForm query(int id);

    TenantForm queryByName(String name);

    TenantForm queryByToken(String token);

    List<TenantForm> queryList(List<Integer> ids);

    Pager<TenantForm> queryList(TenantQueryRequest queryRequest);

    boolean add(TenantForm record);

    boolean addList(List<TenantForm> records);

    boolean update(TenantForm record);

    boolean updateList(List<TenantForm> records);

    boolean delete(int id);

    boolean deleteList(List<Integer> ids);
}
