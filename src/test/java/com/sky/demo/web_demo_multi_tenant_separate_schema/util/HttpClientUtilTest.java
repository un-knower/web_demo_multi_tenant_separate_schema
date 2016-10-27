package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 16/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml"})//, "classpath:springmvc-servlet.xml"})
public class HttpClientUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);

    @Resource
    private RestTemplate restTemplate;


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
        String url = "http://127.0.0.1:8080/web_demo/http/queryList";

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
        String url = "http://127.0.0.1:8080/web_demo/http/queryList";

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

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
        String url = "http://127.0.0.1:8080/web_demo/http/redirect/queryList";

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

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
        String url = "http://127.0.0.1:8080/web_demo/http/redirect/queryList";

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(json);

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


    @Test
    public void test_get_restTemplate() throws IOException {
        String url = "http://127.0.0.1:8080/web_demo/http/query/1";

        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);

    }

    @Test
    public void test_get_redirect_restTemplate() throws IOException {
//        String url = "http://127.0.0.1:8080/web_demo/http/redirect/query/1";
//
//        String result = restTemplate.getForObject(url, String.class);
//        System.out.println(result);

        String url = "https://172.22.111.75:31075/app/v1/protocol/all";

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON); //Also tried with multipart...

        headers.put("Accept", Lists.newArrayList("text/plain;charset=UTF-8", ""));
        headers.put("User-Agent", Lists.newArrayList("Twisted Web Client Example"));
        headers.put("Authorization", Lists.newArrayList("Basic MTQ3Mzc1OTIzNDI1ODozZTc0MWI3NTY2OTJmZjhkM2M2MmE4NjI2NGQwNDRmODAwNDk0YWJiYjM4ZjJmMjA3NjgxMzFlMDQ0NjE2MDM2OlBSMTI4MEgxNjA1MDkwMDAx"));

        org.springframework.http.HttpEntity httpEntity = new  org.springframework.http.HttpEntity("", headers);

        final RestTemplate template = new RestTemplate();
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        template.setRequestFactory(factory);

        String result = template.getForObject(url, String.class, httpEntity);
        System.out.println(result);
    }

    @Test
    public void test_post_restTemplate() throws IOException {
        String url = "http://127.0.0.1:8080/web_demo/http/queryList";

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON); //Also tried with multipart...

        headers.put("Accept", Lists.newArrayList("text/plain;charset=UTF-8", ""));
        headers.put("User-Agent", Lists.newArrayList("Twisted Web Client Example"));
        headers.put("Authorization", Lists.newArrayList("Basic MTQ3Mzc1OTIzNDI1ODozZTc0MWI3NTY2OTJmZjhkM2M2MmE4NjI2NGQwNDRmODAwNDk0YWJiYjM4ZjJmMjA3NjgxMzFlMDQ0NjE2MDM2OlBSMTI4MEgxNjA1MDkwMDAx"));

        org.springframework.http.HttpEntity httpEntity = new  org.springframework.http.HttpEntity(json, headers);

        String result = restTemplate.postForObject(url, httpEntity, String.class, "");
        System.out.println(result);
    }

    @Test
    public void test_post_redirect_restTemplate() throws IOException {
//        String url = "http://127.0.0.1:8080/web_demo/http/redirect/queryList";
        String url = "https://172.22.111.75:31075/app/v1/protocol/all";

        String json = "{\n" +
                "    \"pageNumber\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"beginDate\": \"2016-01-01\",\n" +
                "    \"endDate\": \"2018-01-01\"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON); //Also tried with multipart...

        headers.put("Accept", Lists.newArrayList("text/plain;charset=UTF-8", ""));
        headers.put("User-Agent", Lists.newArrayList("Twisted Web Client Example"));
        headers.put("Authorization", Lists.newArrayList("Basic MTQ3Mzc1OTIzNDI1ODozZTc0MWI3NTY2OTJmZjhkM2M2MmE4NjI2NGQwNDRmODAwNDk0YWJiYjM4ZjJmMjA3NjgxMzFlMDQ0NjE2MDM2OlBSMTI4MEgxNjA1MDkwMDAx"));

        org.springframework.http.HttpEntity httpEntity = new  org.springframework.http.HttpEntity(json, headers);

        final RestTemplate template = new RestTemplate();
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        template.setRequestFactory(factory);

        String result = template.postForObject(url, httpEntity, String.class, "");
        System.out.println(result);
    }

}
