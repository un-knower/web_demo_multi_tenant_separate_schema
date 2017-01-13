package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import java.util.List;

/**
 * Created by user on 17/1/13.
 */
public interface ListRedisDao {

    /**
     * 入栈
     * @param key
     * @param value
     */
    public void push(final String key, final Object value);

    /**
     * 出栈
     * @param key
     * @return
     */
    public Object pop(final String key);

    /**
     * 入队
     * @param key
     * @param value
     */
    public void enquene(final String key, final Object value);

    /**
     * 出对
     * @param key
     * @return
     */
    public Object dequene(final String key);


    /**
     * 长度
     * @param key
     * @return
     */
    public long size(final String key);

    /**
     * 范围检索
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> range(final String key, final long start, final int end);

    /**
     * 移除
     * @param key
     * @param i
     * @param value
     */
    public void remove(final String key, final int i, final Object value);

    /**
     * 检索
     * @param key
     * @param index
     * @return
     */
    public Object index(final String key, final long index);

    /**
     * 设置值
     * @param key
     * @param index
     * @param value
     */
    public void set(final String key, final long index, final Object value);

    /**
     * 裁剪
     * @param key
     * @param start
     * @param end
     */
    public void trim(final String key, final long start, final long end);

}
