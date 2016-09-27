package com.sky.demo.web_demo_multi_tenant_separate_schema.dao;

import java.util.List;
import java.util.Map;

import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.AnLogDto;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.AnLog;

/**
 * Created by user on 16/9/18.
 */
public interface JdbcAnLogDao {

    AnLogDto select(Map<String, Object> condition);

    List<AnLogDto> selectList(Map<String, Object> condition);

    long selectCount(Map<String, Object> condition);

    int insert(AnLog record);

    int batchInsert(List<AnLog> records);

    int update(AnLog record);

    int batchUpdate(List<AnLog> records);

    int delete(final Long id);

    int batchDelete(List<Long> ids);

}
