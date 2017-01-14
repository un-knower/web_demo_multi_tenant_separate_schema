package com.sky.demo.web_demo_multi_tenant_separate_schema.redis.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/1/14.
 */
public class HashRequest {

    private String key;
    private String field;

    private Object value;

    private Map<String, Object> fieldValueMap;

    private List<String> toDelFields;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Map<String, Object> getFieldValueMap() {
        return fieldValueMap;
    }

    public void setFieldValueMap(Map<String, Object> fieldValueMap) {
        this.fieldValueMap = fieldValueMap;
    }

    public List<String> getToDelFields() {
        return toDelFields;
    }

    public void setToDelFields(List<String> toDelFields) {
        this.toDelFields = toDelFields;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("key", key)
                .add("field", field)
                .add("value", value)
                .add("fieldValueMap", fieldValueMap)
                .add("toDelFields", toDelFields)
                .toString();
    }
}
