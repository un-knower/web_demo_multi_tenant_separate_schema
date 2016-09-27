package com.sky.demo.web_demo_multi_tenant_separate_schema.service;


import java.util.List;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.account.AccountQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Account;

/**
 * Created by user on 16/9/19.
 */
public interface AccountService {

    Account query(int id);

    Account query(String userName, String password);

    List<Account> queryList(List<Integer> ids);

    Pager<Account> queryList(AccountQueryRequest queryRequest);

    boolean add(Account record);

    boolean addList(List<Account> records);

    boolean update(Account record);

    boolean updateList(List<Account> records);

    boolean delete(int id);

    boolean deleteList(List<Integer> ids);
}
