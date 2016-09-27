package com.sky.demo.web_demo_multi_tenant_separate_schema.dao;

import java.util.List;
import java.util.Map;

import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Account;

/**
 * Created by user on 16/9/18.
 */
public interface AccountDao {

    Account select(Map<String, Object> condition);

    List<Account> selectList(Map<String, Object> condition);

    int selectCount(Map<String, Object> condition);

    int insert(Account record);

    int batchInsert(List<Account> records);

    int update(Account record);

    int batchUpdate(List<Account> records);

    int delete(final int id);

    int batchDelete(List<Integer> ids);
}
