package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by user on 16/10/26.
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    /**
     * http get
     * @param url
     */
    public static HttpResponse get(String url) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            logger.info("http get url:{}", httpGet.getURI());

            httpResponse = httpClient.execute(httpGet);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

            // 获取响应实体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                // 响应内容长度
                logger.info("Response content length: {}", entity.getContentLength());
                // 响应内容
                logger.info("Response content: {}" + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            logger.error("http get error, url : {}", url, e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("close client error", e);
            }
        }
        return httpResponse;
    }

    /**
     * http post
     * @param url
     */
    public static HttpResponse post(String url, HttpEntity httpEntity) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

            // 获取响应实体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                // 响应内容长度
                logger.info("Response content length: {}", entity.getContentLength());
                // 响应内容
                logger.info("Response content: {}", EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            logger.error("http post error, url: {}", url, e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("close client error", e);
            }
        }
        return httpResponse;
    }


    /**
     * HttpClient GET 自动执行302的重定向
     * @param url
     * @return
     */
    public static HttpResponse getProcessRedirect(String url) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(url);

            response = httpClient.execute(httpGet);
//            httpGet.abort();       //GET 会自动重定向，不能释放请求

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 响应内容长度
                logger.info("Response content length: {}", entity.getContentLength());
                // 响应内容
                logger.info("Response content: {}", EntityUtils.toString(entity));
            }

            //处理http返回码302的情况
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) { //302
                String locationUrl = response.getLastHeader("Location").getValue();
                response = get(locationUrl);  //跳转到重定向的url
            }
        } catch (IOException e) {
            logger.error("http redirect error", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("close client error", e);
            }
        }
        return response;
    }

    /**
     * HttpClient POST 和 PUT，不支持自动转发，因此需要对页面转向做处理。
     * @param url
     * @param httpEntity
     * @return
     */
    public static HttpResponse postProcessRedirect(String url, HttpEntity httpEntity) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(httpEntity);

            response = httpClient.execute(httpPost);
            httpPost.abort();       //POST 不会重定向，需要释放请求

            //处理http返回码302的情况
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) { //302
                String locationUrl = response.getLastHeader("Location").getValue();
                response = post(locationUrl, httpEntity);  //跳转到重定向的url
            }
        } catch (IOException e) {
            logger.error("http redirect error", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("close client error", e);
            }
        }
        return response;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
//        String url = "http://127.0.0.1:8080/web_demo/http/query/1";
//        HttpResponse httpResponse = get(url);

        String url = "http://127.0.0.1:8080/web_demo/http/redirect/query/1";
        HttpResponse httpResponse = getProcessRedirect(url);

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

//        String postUrl = "http://127.0.0.1:8080/web_demo/http/queryList";
//        HttpResponse response = post(postUrl, httpEntity);

//        String postUrl = "http://127.0.0.1:8080/web_demo/http/redirect/queryList";
//        HttpResponse response = postProcessRedirect(postUrl, httpEntity);


    }
}
