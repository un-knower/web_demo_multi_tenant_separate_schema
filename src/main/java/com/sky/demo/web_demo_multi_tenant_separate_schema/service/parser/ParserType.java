package com.sky.demo.web_demo_multi_tenant_separate_schema.service.parser;

/**
 * Created by user on 17/5/17.
 */
public enum ParserType {

    JSON_PARSER(1, "JSON_PARSER"),
    XML_PARSER(2, "JSON_PARSER");

    private int code;
    private String name;

    ParserType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ParserType getParserTypeByCode(int code) {
        for (ParserType parserType : ParserType.values()) {
            if (parserType.code == code) {
                return parserType;
            }
        }
        return null;
    }
}
