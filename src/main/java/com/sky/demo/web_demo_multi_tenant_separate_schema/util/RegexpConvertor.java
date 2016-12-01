/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.commons.lang.StringEscapeUtils
 *  org.apache.commons.lang.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class RegexpConvertor {
    private static final Logger logger = LoggerFactory.getLogger(RegexpConvertor.class);

    private static final String WITHESPACE = " ";
    private static final String EQUAL = "=";
    private static final String NOT_EQUAL = "<>";
    private static final String MATCH_CASE_INSENSITIVE = "~*";
    private static final String MATCH_CASE_SENSITIVE = "~";
    private static final String NOT_MATCH_CASE_INSENSITIVE = "!~*";
    private static final String NOT_MATCH_CASE_SENSITIVE = "!~";
    private static final String STARTER = "^";
    private static final String ENDER = "$";
    private static final String REGEXP_OR = "|";
    private static final String SINGLE_QUOTA = "'";
    private static final String SINGLE_QUOTA_TWO = "''";
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String TIME_FORMAT = "yyyy-mm-dd hh24:mi:ss";
    private static final Map<String, String> WILDCARD_MAPPER = new LinkedHashMap<String, String>();
    private static final Map<String, String> ESCAPE_MAPPER;


    static {
        WILDCARD_MAPPER.put("\\?", ".?");
        WILDCARD_MAPPER.put("\\*", ".*");
        WILDCARD_MAPPER.put("\\+", ".+");
        WILDCARD_MAPPER.put("%", ".*");
        ESCAPE_MAPPER = new LinkedHashMap<String, String>();
        ESCAPE_MAPPER.put("'", "''");
        ESCAPE_MAPPER.put("\\\\", "\\\\\\\\");
        ESCAPE_MAPPER.put("\\.", "\\\\.");
        ESCAPE_MAPPER.put("\\(", "\\\\(");
        ESCAPE_MAPPER.put("\\)", "\\\\)");
    }

    public static String equal(String column, Object value) {
        if (StringUtils.isNotBlank((String)column)) {
            return " " + column + "=" + RegexpConvertor.value2String(value);
        }
        return " ";
    }

    public static String notEqual(String column, Object value) {
        if (StringUtils.isNotBlank((String)column)) {
            return " " + column + "<>" + RegexpConvertor.value2String(value) + " ";
        }
        return " ";
    }

    public static String match(String column, String value) {
        return RegexpConvertor.match(column, value, false, true, true);
    }

    public static String match(String column, List<String> values) {
        return RegexpConvertor.match(column, values, false, true, true);
    }

    public static String match(String column, String value, boolean caseSensitive, boolean partial, boolean matched) {
        if (StringUtils.isNotBlank((String)column)) {
            return RegexpConvertor.regexp(column, value, RegexpConvertor.chooseOperator(caseSensitive, matched), partial);
        }
        return " ";
    }

    public static String match(String column, List<String> values, boolean caseSensitive, boolean partial, boolean matched) {
        if (StringUtils.isNotBlank((String)column)) {
            return RegexpConvertor.regexp(column, values, RegexpConvertor.chooseOperator(caseSensitive, matched), partial);
        }
        return " ";
    }

    private static String regexp(String column, Object value, String connector, boolean partial) {
        String retVal = "";
        if (value instanceof String) {
            retVal = RegexpConvertor.regexpReplace((String)value, partial);
        } else if (value instanceof List) {
            retVal = RegexpConvertor.regexpReplace((List)value, partial);
        }
        retVal = " " + column + connector + "'" + retVal + "'" + " ";
        logger.debug("convert to regexp, before:" + value + ", after:" + retVal);
        return retVal;
    }

    private static String regexpReplace(List<String> values, boolean partial) {
        if (values == null || values.isEmpty()) {
            return partial ? "" : "^$";
        }
        String retVal = "";
        Iterator<String> i = values.iterator();
        while (i.hasNext()) {
            retVal = retVal + RegexpConvertor.regexpReplace(i.next(), partial) + "|";
        }
        if (retVal.endsWith("|")) {
            retVal = retVal.substring(0, retVal.length() - 1);
        }
        return retVal;
    }

    private static String regexpReplace(String value, boolean partial) {
        if (StringUtils.isEmpty((String)value)) {
            return partial ? "" : "^$";
        }
        for (Map.Entry<String, String> entry2 : ESCAPE_MAPPER.entrySet()) {
            value = value.replaceAll(entry2.getKey(), entry2.getValue());
        }
        for (Map.Entry<String, String> entry2 : WILDCARD_MAPPER.entrySet()) {
            value = value.replaceAll(entry2.getKey(), entry2.getValue());
        }
        value = StringEscapeUtils.escapeSql((String)value);
        return partial ? value : "^" + value + "$";
    }

    private static synchronized String value2String(Object value) {
        if (value == null) {
            return "''";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Number) {
            return value.toString();
        }
        if (value instanceof Date) {
            return " to_date('" + SDF.format((Date)value) + "'" + "," + "'" + "yyyy-mm-dd hh24:mi:ss" + "'" + ")";
        }
        return "'" + value + "'";
    }

    private static String chooseOperator(boolean caseSensitive, boolean matched) {
        if (caseSensitive && matched) {
            return "~";
        }
        if (caseSensitive && !matched) {
            return "!~";
        }
        if (!caseSensitive && matched) {
            return "~*";
        }
        return "!~*";
    }

    public static void main(String[] args) {
        String column = "description";
        String value = "%d+";
        String other = "select * from sys_role where";
        ArrayList valueList = Lists.newArrayList((Object[])new String[]{"abc", "123*", "3?5", "1?3*"});
        String str = "\\\\172.22.1.35\\share\\\u673a\u5668\u5b66\u4e60--\u672a\u8bad\u7ec3C\u8bed\u8a00\\C\\c2\\mazeclean.c";
        System.out.println(str);
        for (Map.Entry<String, String> entry : ESCAPE_MAPPER.entrySet()) {
            str = str.replaceAll(entry.getKey(), entry.getValue());
        }
        System.out.println(str);
    }

}

