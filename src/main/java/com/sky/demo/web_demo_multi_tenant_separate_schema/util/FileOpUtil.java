package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by user on 17/2/28.
 */
public class FileOpUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileOpUtil.class);


    public static String readFile(String inputPath) {
        Preconditions.checkNotNull(inputPath, "input path is null!");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8));

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (FileNotFoundException e) {
            logger.error("file not found", e);
        } catch (IOException e) {
            logger.error("io error", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("close BufferedReader error", e);
                }
            }
        }

        return sb.toString();
    }





}
