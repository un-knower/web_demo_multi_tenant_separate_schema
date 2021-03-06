package com.sky.demo.web_demo_multi_tenant_separate_schema.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.basedb.BaseDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dao.AccountDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.account.AccountQueryRequest;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Account;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AccountService;

/**
 * Created by user on 16/9/19.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;


    @Override
    public Account query(int id) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("id", id);

        Account result = null;
        try {
            result = accountDao.select(condition);
        } catch (Exception e) {
            logger.error("query error", e);
        }
        return result;
    }

    @Override
    public Account query(String userName, String password) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("userName", userName);
        condition.put("password", password);

        Account result = null;
        try {
            result = accountDao.select(condition);
        } catch (Exception e) {
            logger.error("query error", e);
        }
        return result;
    }

    @Override
    public List<Account> queryList(List<Integer> ids) {
        Map<String, Object> condition = Maps.newHashMap();

        String strIds = Joiner.on(",").skipNulls().join(ids);
        condition.put("ids", strIds);

        List<Account> result = null;
        try {
            result = accountDao.selectList(condition);
        } catch (Exception e) {
            logger.error("query list error", e);
        }
        return result;
    }

    @Override
    public Pager<Account> queryList(AccountQueryRequest queryRequest) {
        Map<String, Object> condition = Maps.newHashMap();

        Pager<Account> ret = null;
        List<Account> accounts = null;
        try {
            long totalRecord = accountDao.selectCount(condition);
            ret = new Pager<Account>(totalRecord, queryRequest.getPageNumber(), queryRequest.getPageSize());

            int limit = ret.getPageSize();
            long offset = (ret.getPageNumber() - 1) * ret.getPageSize();
            condition.put(BaseDao.LIMIT, limit);
            condition.put(BaseDao.OFFSET, offset);

            accounts = accountDao.selectList(condition);
        } catch (Exception e) {
            logger.error("query list error", e);
        }

        ret.setRows(accounts);
        return ret;
    }

    @Override
    public boolean add(Account record) {
        int row = 0;
        try {
            row = accountDao.insert(record);
        } catch (Exception e) {
            logger.error("add tenant error", e);
        }
        return row > 0;
    }

    @Override
    public boolean addList(List<Account> records) {
        return false;
    }

    @Override
    public boolean update(Account record) {
        int row = 0;
        try {
            row = accountDao.update(record);
        } catch (Exception e) {
            logger.error("update error", e);
        }
        return row > 0;
    }

    @Override
    public boolean updateList(List<Account> records) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        int row = 0;
        try {
            row = accountDao.delete(id);
        } catch (Exception e) {
            logger.error("delete error", e);
        }
        return row > 0;
    }

    @Override
    public boolean deleteList(List<Integer> ids) {
        return false;
    }
}
