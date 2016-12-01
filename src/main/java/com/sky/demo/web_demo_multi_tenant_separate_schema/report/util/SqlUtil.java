/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Lists
 *  org.apache.commons.collections.CollectionUtils
 *  org.apache.commons.lang.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.report.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class SqlUtil {
    private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

    public static String buildColumnsWithQuote(List<String> columns) {
        StringBuilder result = new StringBuilder();
        if (CollectionUtils.isNotEmpty(columns)) {
            for (int i = 0; i < columns.size(); ++i) {
                result.append("'").append(columns.get(i)).append("'");
                if (i >= columns.size() - 1) continue;
                result.append(",");
            }
        }
        return result.toString();
    }

    public static String buildColumnsWithoutQuote(List<String> columns) {
        String columnsStr = null;
        columnsStr = CollectionUtils.isNotEmpty(columns) ? Joiner.on((String)",").skipNulls().join(columns) : "*";
        return columnsStr;
    }



    public static List<String> removeAs(List<String> columns) {
        ArrayList fixedColumns = Lists.newArrayList();
        String as = " AS ";
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()) {
            String column = iterator.next();
            if (column.contains(as)) {
                column = column.substring(column.indexOf(as) + 4);
            }
            fixedColumns.add(column);
        }
        return fixedColumns;
    }

    public static List<String> sortItems(List<String> items, List<String> initSystemItems) {
        ArrayList systemItems = Lists.newArrayList();
        ArrayList userItems = Lists.newArrayList();
        for (String item : items) {
            if (SqlUtil.isSystemItem(item, initSystemItems)) {
                systemItems.add(item);
                continue;
            }
            userItems.add(item);
        }
        Collections.sort(userItems);
        Collections.sort(systemItems);
        return userItems;
    }

    private static boolean isSystemItem(String table, List<String> initSystemItems) {
        boolean result = false;
        for (String str : initSystemItems) {
            if (!table.startsWith(str)) continue;
            result = true;
            break;
        }
        return result;
    }

    public static Time StringToTime(String time) {
        Time t = null;
        try {
            String[] tmp = time.split(":");
            t = new Time(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]));
        }
        catch (Exception e) {
            logger.debug("time[" + time + "] convert failed [HH:mm:ss]");
        }
        return t;
    }

    public static Timestamp StringToTimestamp(String date) {
        Timestamp t = null;
        if (StringUtils.isNotBlank((String)date)) {
            if (date.contains("/")) {
                date = date.replaceAll("/", "-");
            }
            try {
                t = Timestamp.valueOf(date);
            }
            catch (Exception e) {
                logger.debug("date[" + date + "] convert failed [yyyy-mm-dd hh:mm:ss]");
            }
        }
        return t;
    }



}

