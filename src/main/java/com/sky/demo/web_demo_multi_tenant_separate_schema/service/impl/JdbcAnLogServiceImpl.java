package com.sky.demo.web_demo_multi_tenant_separate_schema.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.Pager;
import com.sky.demo.web_demo_multi_tenant_separate_schema.basedb.BaseDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dao.JdbcAnLogDao;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.*;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.ActionType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.AnLog;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.FeatureType;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AnLogService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Constants;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.HttpUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.json.JsonUtil;

/**
 * Created by user on 16/9/18.
 */
@Service
public class JdbcAnLogServiceImpl implements AnLogService {

    private static final Logger logger = LoggerFactory.getLogger(JdbcAnLogServiceImpl.class);

    @Resource
    private JdbcAnLogDao anLogDao;


    private static final Function<Map<String, Object>, AnLogForm> transferMap2Form = new Function<Map<String, Object>, AnLogForm>() {
        @Override
        public AnLogForm apply(Map<String, Object> map) {
            AnLogForm anLogForm = new AnLogForm();
            anLogForm.setId((long) map.get("id"));
            anLogForm.setCreateTime((String) map.get("createTime"));
            anLogForm.setUserName((String) map.get("userName"));
            anLogForm.setRoleName((String) map.get("roleName"));
            anLogForm.setServerIp((String) map.get("serverIp"));
            anLogForm.setClientIp((String) map.get("clientIp"));
            anLogForm.setActionName(ActionType.getActionTypeByCode((int) map.get("actionType")).getDesc());
            anLogForm.setFeatureName(FeatureType.getFeatureTypeByCode((int) map.get("featureType")).getDesc());

            String info = (String) map.get("actionInfo");
            List<BaseAnActionInfo> actionInfo = JsonUtil.readValue(info, List.class, List.class, BaseAnActionInfo.class);
            anLogForm.setActionInfo(actionInfo);
            return anLogForm;
        }
    };

    private static final Function<AnLogDto, AnLogForm> transfer2Form = new Function<AnLogDto, AnLogForm>() {
        @Override
        public AnLogForm apply(AnLogDto input) {
            AnLogForm anLogForm = new AnLogForm();
            anLogForm.setId(input.getId());
            anLogForm.setCreateTime(DateFormatUtils.format(input.getCreateTime(), Constants.DATETIME_PATTERN));
            anLogForm.setUserName(input.getUserName());
            anLogForm.setRoleName(input.getRoleName());
            anLogForm.setServerIp(input.getServerIp());
            anLogForm.setClientIp(input.getClientIp());
            anLogForm.setActionName(ActionType.getActionTypeByCode(input.getActionType()).getDesc());
            anLogForm.setFeatureName(FeatureType.getFeatureTypeByCode(input.getFeatureType()).getDesc());

            String info = input.getActionInfo();
            List<BaseAnActionInfo> actionInfo = JsonUtil.readValue(info, List.class, List.class, BaseAnActionInfo.class);
            anLogForm.setActionInfo(actionInfo);
            return anLogForm;
        }
    };

    private static final Function<AnLogInsertRequest, AnLog> transferInsertReq2AnLog = new Function<AnLogInsertRequest, AnLog>() {
        @Override
        public AnLog apply(AnLogInsertRequest input) {
            AnLog log = new AnLog();
            log.setCreateTime(new Date());
            log.setUserId(input.getUserId());
            log.setRoleId(input.getRoleId());
            log.setServerIp(HttpUtil.getLocalIp());
            log.setClientIp(input.getClientIp());
            log.setActionType(input.getActionType().getCode());
            log.setFeatureType(input.getFeatureType().getCode());

            String actionInfo = JsonUtil.writeValueAsString(input.getActionInfo());
            log.setActionInfo(actionInfo);
            return log;
        }
    };

    private static final Function<AnLogUpdateRequest,AnLog> transferUpdateReq2AnLog = new Function<AnLogUpdateRequest, AnLog>() {
        @Override
        public AnLog apply(AnLogUpdateRequest request) {
            AnLog log = new AnLog();
            log.setId(request.getId());
            log.setActionType(request.getActionType().getCode());
            log.setFeatureType(request.getFeatureType().getCode());

            String actionInfo = JsonUtil.writeValueAsString(request.getActionInfo());
            log.setActionInfo(actionInfo);
            return log;
        }
    };



    @Override
    public AnLogForm query(long id) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("id", id);

        AnLogForm result = null;
        AnLogDto anLogDto = null;
        try {
            anLogDto = anLogDao.select(condition);
        } catch (Exception e) {
            logger.error("query error", e);
        }

        if (anLogDto != null) {
            result = transfer2Form.apply(anLogDto);
        }
        return result;
    }

    @Override
    public List<AnLogForm> queryList(List<Long> ids) {
        Map<String, Object> condition = Maps.newHashMap();

        String strIds = Joiner.on(",").skipNulls().join(ids);
        condition.put("ids", strIds);

        List<AnLogForm> result = Lists.newArrayList();
        List<AnLogDto> anLogDtos = null;
        try {
            anLogDtos = anLogDao.selectList(condition);
        } catch (Exception e) {
            logger.error("query list error", e);
        }

        if (CollectionUtils.isNotEmpty(anLogDtos)) {
            for (AnLogDto anLogDto : anLogDtos) {
                AnLogForm anLogForm = transfer2Form.apply(anLogDto);
                result.add(anLogForm);
            }
        }

        return result;
    }

    @Override
    public Pager<AnLogForm> queryList(AnLogQueryRequest queryRequest) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("beginTime", queryRequest.getBeginDate() + " 00:00:00");
        condition.put("endTime", queryRequest.getEndDate() + " 23:59:59");

        Pager<AnLogForm> ret = null;
        List<AnLogForm> anLogForms = null;
        List<AnLogDto> anLogDtos = null;
        try {
            long totalRecord = anLogDao.selectCount(condition);
            ret = new Pager<AnLogForm>(totalRecord, queryRequest.getPageNumber(), queryRequest.getPageSize());

            int limit = ret.getPageSize();
            long offset = (ret.getPageNumber() - 1) * ret.getPageSize();
            condition.put(BaseDao.LIMIT, limit);
            condition.put(BaseDao.OFFSET, offset);

            anLogForms = Lists.newArrayList();
            anLogDtos = anLogDao.selectList(condition);
        } catch (Exception e) {
            logger.error("query list error", e);
        }

        if (CollectionUtils.isNotEmpty(anLogDtos)) {
            for (AnLogDto anLogDto : anLogDtos) {
                AnLogForm anLogForm = transfer2Form.apply(anLogDto);
                anLogForms.add(anLogForm);
            }
        }

        ret.setRows(anLogForms);
        return ret;
    }

    @Override
    public boolean add(AnLogInsertRequest insertRequest) {
        int row = 0;
        try {
            AnLog log = transferInsertReq2AnLog.apply(insertRequest);
            row = anLogDao.insert(log);
        } catch (Exception e) {
            logger.error(insertRequest.toString(), e);
        }
        return row > 0;
    }

    @Override
    public boolean addList(List<AnLogInsertRequest> insertRequests) {
        int row = 0;
        List<AnLog> anLogs = Lists.newArrayList();
        try {
            AnLog log = null;
            for (AnLogInsertRequest request : insertRequests) {
                log = transferInsertReq2AnLog.apply(request);
                anLogs.add(log);
            }
            row = anLogDao.batchInsert(anLogs);
        } catch (Exception e) {
            logger.error(insertRequests.toString(), e);
        }
        return row > 0;
    }

    @Override
    public boolean update(AnLogUpdateRequest updateRequest) {
        AnLog log = transferUpdateReq2AnLog.apply(updateRequest);
        int row = 0;
        try {
            row = anLogDao.update(log);
        } catch (Exception e) {
            logger.error("update error", e);
        }
        return row > 0;
    }

    @Async
    @Override
    public boolean asyncUpdate(AnLogUpdateRequest updateRequest) {
        AnLog log = transferUpdateReq2AnLog.apply(updateRequest);
        int row = 0;
        try {
            row = anLogDao.update(log);
        } catch (Exception e) {
            logger.error("update error", e);
        }
        return row > 0;
    }

    @Override
    public boolean updateList(List<AnLogUpdateRequest> updateRequests) {
//        Map<String,Object> params = Maps.newHashMap();
//        AnLog log = transferUpdateReq2AnLog.apply(queryRequest);
//        int row = anLogDao.batchUpdate(ids, log);
        return false; //row > 0;
    }


    @Override
    public boolean delete(long id) {
        int row = 0;
        try {
            row = anLogDao.delete(id);
        } catch (Exception e) {
            logger.error("delete error", e);
        }
        return row > 0;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        int row = 0;
        try {
            row = anLogDao.batchDelete(ids);
        } catch (Exception e) {
            logger.error("delete list error", e);
        }
        return row > 0;
    }
}

