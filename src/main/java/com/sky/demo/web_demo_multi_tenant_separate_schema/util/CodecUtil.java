package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by user on 16/9/29.
 */
public class CodecUtil {

    private static final String CHARSET = "utf-8";

    public static String encode(String input) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(input.getBytes(CHARSET));
    }

    public static String decode(String input) throws UnsupportedEncodingException {
        byte[] b = Base64.getDecoder().decode(input);
        return new String(b, CHARSET);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str1 = "MTQ3NzQ2MzU2ODpiZjgzYmZhMzg3MDI1NGY5ODEwNTBjMmYzZTk1ZGJmZTYyZTRhYjQ1NTY2NDRmYjcxYzg4MGVlMmU2ZjI5Yzc0OlBSMTcxMEczMTYwNTAwMDU";

        //MTQ3Mzc1OTIzNDI1ODozZTc0MWI3NTY2OTJmZjhkM2M2MmE4NjI2NGQwNDRmODAwNDk0YWJiYjM4ZjJmMjA3NjgxMzFlMDQ0NjE2MDM2OlBSMTI4MEgxNjA1MDkwMDAx
        System.out.println(decode(str1));
        //1477463583:24bb414a7d75f0ba03c761358c81f6b2ffb7f04c04532fa13dadaa17e6368c75:PR1710G316050005

        //1477463568:bf83bfa3870254f981050c2f3e95dbfe62e4ab4556644fb71c880ee2e6f29c74:PR1710G316050005

    }
}
