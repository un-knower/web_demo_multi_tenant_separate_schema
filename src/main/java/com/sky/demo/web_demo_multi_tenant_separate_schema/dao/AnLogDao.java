package com.sky.demo.web_demo_multi_tenant_separate_schema.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.anlog.AnLogForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.AnLog;


/**
 * Created by rg on 2015/6/11.
 */
//@Repository   //for MyBatis
public interface AnLogDao {

    AnLogForm selectById(@Param("id") final Long id);

    List<AnLogForm> selectList(Map<String, Object> condition);  //for MyBatis

    List<AnLogForm> selectList(Map<String, Object> condition, RowBounds rowBounds);  //for MyBatis

    int selectCount(Map<String, Object> condition);

    int deleteById(@Param("id") final Long id);

    int batchDelete(List<Long> ids);

    int insert(AnLog record);

    int batchInsert(List<AnLog> recordList);

    int update(AnLog record);

    int batchUpdate(List<Long> ids, AnLog record);


}
