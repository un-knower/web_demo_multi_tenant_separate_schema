package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 2015/7/15.
 * SHA加密
 */
public class SHAUtil {

    private static final Logger logger = LoggerFactory.getLogger(SHAUtil.class);

    private static final String DEFAULT_SHA = "SHA-256";

    public static String SHASumForString(String input) {
        Preconditions.checkNotNull(input, "input is null!");
        String result = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DEFAULT_SHA);

            byte[] byteArray = input.getBytes("UTF-8");
            byte[] resultByteArray = messageDigest.digest(byteArray);

//            result = new String(resultByteArray, StandardCharsets.UTF_8);
            result = byteArrayToString(resultByteArray);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("sha string error",e);
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


    public static String SHAForStrByDigestUtils(String input) {
        Preconditions.checkNotNull(input, "input is null");

        return DigestUtils.sha256Hex(input);
    }

    public static String SHAForStrByGuava(String input) {
        Preconditions.checkNotNull(input, "input is null");

        HashCode hashCode = Hashing.sha256().hashString(input, StandardCharsets.UTF_8);
        return hashCode.toString();
    }


    public static void main(String[] args) {
        String str = "abc";
        String encrypt = SHAUtil.SHASumForString(str);
        System.out.println(encrypt);

        encrypt = SHAForStrByDigestUtils(str);
        System.out.println(encrypt);

        encrypt = SHAForStrByGuava(str);
        System.out.println(encrypt);
    }
}
