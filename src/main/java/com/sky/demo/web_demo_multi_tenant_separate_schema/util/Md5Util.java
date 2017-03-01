package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 17/2/28.
 */
public class Md5Util {

    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    private static final String DIGEST_ALGORITHM = "MD5";
    private static final int BUFFER_SIZE = 256 * 1024;


    public static String md5sumForString(String input) {
        Preconditions.checkNotNull(input, "input is null!");
        String result = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);  //SHA1

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


    public static String md5sumForStringByDigestUtils(String input) {
        Preconditions.checkNotNull(input, "input is null");
        return DigestUtils.md5Hex(input);
    }

    public static String md5sumForStringByGuava(String input) {
        Preconditions.checkNotNull(input, "input is null");
        HashCode hashCode = Hashing.md5().hashString(input, StandardCharsets.UTF_8);
        return hashCode.toString();
    }


    //=============file=============

    public static String md5sumForFile(String inputPath) {
        Preconditions.checkNotNull(inputPath, "input path is null");
        String result = null;
        FileInputStream fileInputStream = null;
        DigestInputStream digestInputStream = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
            fileInputStream = new FileInputStream(Paths.get(inputPath).toString());
            digestInputStream = new DigestInputStream(fileInputStream, messageDigest);

            byte[] buffer = new byte[BUFFER_SIZE];
            while (digestInputStream.read(buffer) > 0) {
            }

            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();

            byte[] resultByteArray = messageDigest.digest();

            result = byteArrayToString(resultByteArray);
//            result = byteArrayToHexStr(resultByteArray);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("close file input stream error", e);
                }
            }
            if (digestInputStream != null) {
                try {
                    digestInputStream.close();
                } catch (IOException e) {
                    logger.error("close digest input stream error", e);
                }

            }
        }
        return result;
    }


    public static String md5sumForFileByDigestUtils(String inputPath) {
        Preconditions.checkNotNull(inputPath, "input path is null");
        String result = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(Paths.get(inputPath).toString());
            result = DigestUtils.md5Hex(fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    public static String md5sumForFileByGuava(String inputPath) {
        Preconditions.checkNotNull(inputPath, "input path is null");
        String result = null;

        File inputFile = new File(inputPath);
        try {
            HashCode hashCode = Files.hash(inputFile, Hashing.md5());

            result = hashCode.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) {
        String input = "xxx\n";   // linux echo 'xxx' | md5sum  (echo auto add \n)
        String md5sum = md5sumForString(input);
        System.out.println(md5sum);

        md5sum = md5sumForStringByDigestUtils(input);
        System.out.println(md5sum);

        md5sum = md5sumForStringByGuava(input);
        System.out.println(md5sum);

        System.out.println("***************");
        String inputPath = "/Users/user/software/debian7.8/debian-7.8.0-amd64-DVD-1.iso";
        //"/Users/user/share/poms.tar.gz";
        //String md5 = md5sumForFile(inputPath);
        //System.out.println(md5);

        //String md5ForFile = md5sumForFileByDigestUtils(inputPath);
        //System.out.println(md5ForFile);

        String md5ForFileByGuava = md5sumForFileByGuava(inputPath);
        System.out.println(md5ForFileByGuava);
    }
}
