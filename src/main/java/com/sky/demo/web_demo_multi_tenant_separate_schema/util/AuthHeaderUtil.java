package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

/**
 * Created by user on 16/9/29.
 */
public class AuthHeaderUtil {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String AUTHORIZATION = "Authorization";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT_TYPE = "Accept";


    private static Map<String, String> generateAuthHeader(String timestamp, String id, String token) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        map.put(CONTENT_TYPE, APPLICATION_JSON);
        map.put(ACCEPT_TYPE, APPLICATION_JSON);

        String authCode = generateAuthorization(timestamp, id, token);
        map.put(AUTHORIZATION, authCode);
        return map;
    }

    private static String generateAuthorization(String timestamp, String id, String token) throws UnsupportedEncodingException {
        String authCode = null;
        if (StringUtils.isNotBlank(id)) {
            authCode = timestamp + token + id;
        } else {
            authCode = timestamp + token;
        }

        String enCodeBySha = SHAUtil.encrypt(authCode);       //SHA-256
        System.out.println("SHA-256 : " + enCodeBySha);
        String code = null;
        if (StringUtils.isNotBlank(id)) {
            code = timestamp + ":" + enCodeBySha + ":" + id;
        } else {
            code = timestamp + ":" + enCodeBySha;
        }

        return CodecUtil.encode(code);      //Base64编码
    }

    public static void main(String[] args) throws Exception {
//        String timestamp = String.valueOf(new Date().getTime());
        String timestamp = "1477463583"; //"1477463568";

        String id = "PR1710G316050005";      //"4a3e7145-e757-4660-b3ea-8328e88d8f66";
        String token = "0df51cf6619f461482e95b40da2a2024";      //"1aaa57afec464a979f946f1c6967a8cf";

//        Map<String, String> map = AuthHeaderUtil.generateAuthHeader(id, token);
//        System.out.println(map);

        String auth = generateAuthorization(timestamp, id, token);
        System.out.println(auth);

        System.out.println("\n=====device auth======");
        token = "sky";
        auth = generateAuthorization(timestamp, null, token);
        System.out.println(auth);

//        System.out.println("uuids:");
//        for (int i = 0; i < 5 ; ++i) {
//            System.out.println(UUID.randomUUID().toString());
//        }
    }

}
