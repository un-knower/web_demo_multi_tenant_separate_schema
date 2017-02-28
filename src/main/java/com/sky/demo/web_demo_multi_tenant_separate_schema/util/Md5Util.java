package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 17/2/28.
 */
public class Md5Util {

    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    public static String md5sumForString(String input) {
        Preconditions.checkNotNull(input, "input is null!");
        String result = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");  //SHA1

            byte[] inputByteArray = input.getBytes(StandardCharsets.UTF_8);
//            messageDigest.update(inputByteArray);

            //转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest(inputByteArray);

//            result = new String(resultByteArray, StandardCharsets.UTF_8);
            result = byteArrayToString(resultByteArray);
//            result = byteArrayToHexStr(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            logger.error("md5sum for string error", e);
        }

        return result;
    }


    private static String byteArrayToString(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String byteArrayToHexStr(byte[] byteArray) {
        Preconditions.checkNotNull(byteArray, "byteArray is null");

        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static String md5sumForStr(String input) {
        Preconditions.checkNotNull(input, "input is null");
        return DigestUtils.md5Hex(input);
    }

    public static String md5sumForFile(String inputPath) {
        Preconditions.checkNotNull(inputPath, "input path is null");
        String result = null;


        return result;
    }

    public static void main(String[] args) {
        String input = "xxx\n";   // linux echo 'xxx' | md5sum  (echo auto add \n)
        String md5sum = md5sumForString(input);
        System.out.println(md5sum);

        System.out.println(md5sumForStr(input));
    }
}
