package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by user on 16/10/27.
 */
public class HttpClientUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);

    @Test
    public void test_get() throws IOException {

        String url = "http://127.0.0.1:8080/web_demo/http/query/1";
        HttpResponse response = HttpClientUtil.get(url);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }

    @Test
    public void test_post() throws IOException {

//        // 创建参数队列
//        List<NameValuePair> pairList = Lists.newArrayList();
//        pairList.add(new BasicNameValuePair("val", "110110"));
//        HttpEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairList, "UTF-8");

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

        String url = "http://127.0.0.1:8080/web_demo/http/queryList";
        HttpResponse response = HttpClientUtil.post(url, httpEntity);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }

    @Test
    public void test_put() throws IOException {

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

        String url = "http://127.0.0.1:8080/web_demo/http/queryList";
        HttpResponse response = HttpClientUtil.put(url, httpEntity);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }

    @Test
    public void test_delete() throws IOException {

        String url = "http://127.0.0.1:8080/web_demo/http/delete/1";
        HttpResponse response = HttpClientUtil.delete(url);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }




    //========================== Redirect ================================//


    @Test
    public void test_get_ProcessRedirect() throws IOException {

        String url = "http://127.0.0.1:8080/web_demo/http/redirect/query/1";
        HttpResponse response = HttpClientUtil.getProcessRedirect(url);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }

    @Test
    public void test_post_ProcessRedirect() throws IOException {

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

        String url = "http://127.0.0.1:8080/web_demo/http/redirect/queryList";
        HttpResponse response = HttpClientUtil.processRedirect(HttpMethod.POST, url, httpEntity);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }

    @Test
    public void test_put_ProcessRedirect() throws IOException {

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

        String url = "http://127.0.0.1:8080/web_demo/http/redirect/queryList";
        HttpResponse response = HttpClientUtil.processRedirect(HttpMethod.PUT, url, httpEntity);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }

    @Test
    public void test_delete_ProcessRedirect() throws IOException {

        String url = "http://127.0.0.1:8080/web_demo/http/redirect/delete/1";
        HttpResponse response = HttpClientUtil.deleteProcessRedirect(url);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应内容长度
            logger.info("Response content length: {}", entity.getContentLength());
            // 响应内容
//            logger.info("Response content: {}" + EntityUtils.toString(entity));
        }
    }
}
