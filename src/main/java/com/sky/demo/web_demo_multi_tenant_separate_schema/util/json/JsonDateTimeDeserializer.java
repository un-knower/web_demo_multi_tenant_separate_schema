package com.sky.demo.web_demo_multi_tenant_separate_schema.util.json;

import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Constants;

/**
 * Created by rg on 2015/7/14.
 */
public class JsonDateTimeDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.DATETIME_PATTERN);
        return DateTime.parse(p.getText(),formatter).toDate();
    }
}
