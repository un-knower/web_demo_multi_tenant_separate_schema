package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dao;

import java.util.List;

/**
 * Created by user on 17/1/13.
 */
public interface ListRedisDao<T> {

    /**
     * 入栈
     * @param key
     * @param value
     */
    public <T> void push(final String key, final T value);

    /**
     * 出栈
     * @param key
     * @return
     */
    public <T> T pop(final String key);

    /**
     * 入队
     * @param key
     * @param value
     */
    public <T> void enquene(final String key, final T value);

    /**
     * 出对
     * @param key
     * @return
     */
    public <T> T dequene(final String key);


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
    public <T> List<T> range(final String key, final long start, final int end);

    /**
     * 移除
     * @param key
     * @param i
     * @param value
     */
    public <T> void remove(final String key, final int i, final T value);

    /**
     * 检索
     * @param key
     * @param index
     * @return
     */
    public <T> T index(final String key, final long index);

    /**
     * 设置值
     * @param key
     * @param index
     * @param value
     */
    public <T> void set(final String key, final long index, final T value);

    /**
     * 裁剪
     * @param key
     * @param start
     * @param end
     */
    public void trim(final String key, final long start, final long end);

    public void delete(final String key);

}
