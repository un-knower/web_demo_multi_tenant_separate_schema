package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.io.IOException;

/**
 * Created by user on 16/10/26.
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    /**
     * http get
     *
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

//            // 获取响应实体
//            HttpEntity entity = httpResponse.getEntity();
//            if (entity != null) {
//                // 响应内容长度
//                logger.info("Response content length: {}", entity.getContentLength());
//                // 响应内容
//                logger.info("Response content: {}" + EntityUtils.toString(entity));
//            }
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
     *
     * @param url
     */
    public static HttpResponse post(String url, HttpEntity httpEntity) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            logger.info("http post url:{}", httpPost.getURI());

            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

            // 获取响应实体
//            HttpEntity entity = httpResponse.getEntity();
//            if (entity != null) {
//                // 响应内容长度
//                logger.info("Response content length: {}", entity.getContentLength());
//                // 响应内容
//                logger.info("Response content: {}", EntityUtils.toString(entity));
//            }
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
     * http put
     *
     * @param url
     */
    public static HttpResponse put(String url, HttpEntity httpEntity) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(url);
            logger.info("http put url:{}", httpPut.getURI());

            httpPut.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPut);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

            // 获取响应实体
//            HttpEntity entity = httpResponse.getEntity();
//            if (entity != null) {
//                // 响应内容长度
//                logger.info("Response content length: {}", entity.getContentLength());
//                // 响应内容
//                logger.info("Response content: {}", EntityUtils.toString(entity));
//            }
        } catch (Exception e) {
            logger.error("http put error, url: {}", url, e);
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
     * http delete
     *
     * @param url
     */
    public static HttpResponse delete(String url) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            logger.info("http delete url:{}", httpDelete.getURI());

            httpResponse = httpClient.execute(httpDelete);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

//            // 获取响应实体
//            HttpEntity entity = httpResponse.getEntity();
//            if (entity != null) {
//                // 响应内容长度
//                logger.info("Response content length: {}", entity.getContentLength());
//                // 响应内容
//                logger.info("Response content: {}" + EntityUtils.toString(entity));
//            }
        } catch (IOException e) {
            logger.error("http delete error, url : {}", url, e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("close client error", e);
            }
        }
        return httpResponse;
    }






    //========================== Redirect ================================//

    /**
     * HttpClient GET 自动执行302的重定向
     *
     * @param url
     * @return
     */
    public static HttpResponse getProcessRedirect(String url) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(url);
            logger.info("http get url:{}", httpGet.getURI());

            response = httpClient.execute(httpGet);
//            httpGet.abort();       //GET 会自动重定向，不能释放请求

            //处理http返回码302的情况  GET 不会执行到这,自动重定向后，返回200
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
     * HttpClient DELETE
     *
     * @param url
     * @return
     */
    public static HttpResponse deleteProcessRedirect(String url) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpDelete httpDelete = new HttpDelete(url);
            logger.info("http delete url:{}", httpDelete.getURI());

            response = httpClient.execute(httpDelete);
            httpDelete.abort();       // 会自动重定向，不能释放请求

            //处理http返回码302的情况
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) { //302
                String locationUrl = response.getLastHeader("Location").getValue();
                response = delete(locationUrl);  //跳转到重定向的url
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
     * HttpClient GET 自动执行302的重定向
     * HttpClient POST, PUT, DELETE，不支持自动转发，因此需要对页面转向做处理。
     *
     * @param url
     * @param httpEntity
     * @return
     */
    public static HttpResponse processRedirect(HttpMethod httpMethod, String url, HttpEntity httpEntity) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            switch (httpMethod) {
                case GET: {
                    HttpGet httpGet = new HttpGet(url);
                    logger.info("http get url:{}", httpGet.getURI());

                    response = httpClient.execute(httpGet);
                    break;
                }
                case POST: {
                    HttpPost httpPost = new HttpPost(url);
                    logger.info("http post url:{}", httpPost.getURI());

                    httpPost.setEntity(httpEntity);
                    response = httpClient.execute(httpPost);
                    httpPost.abort();         //POST, PUT 不会重定向，需要释放请求
                    break;
                }

                case PUT: {
                    HttpPut httpPut = new HttpPut(url);
                    logger.info("http put url:{}", httpPut.getURI());

                    httpPut.setEntity(httpEntity);
                    response = httpClient.execute(httpPut);
                    httpPut.abort();         //POST, PUT 不会重定向，需要释放请求
                    break;
                }
                case DELETE :{
                    HttpDelete httpDelete = new HttpDelete(url);
                    logger.info("http delete url:{}", httpDelete.getURI());

                    response = httpClient.execute(httpDelete);
                    break;
                }

                default: {
                    logger.warn("other method to do");
                    break;
                }

            }

            //处理http返回码302的情况
            processMovedTemporarily(httpMethod, response, httpEntity);

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
     * 处理http返回码302的情况
     * @param response
     * @return
     */
    public static HttpResponse processMovedTemporarily(HttpMethod httpMethod, HttpResponse response, HttpEntity httpEntity) {
        Preconditions.checkNotNull(response, "response is null!");

        HttpResponse redirectResponse = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) { //302
            String locationUrl = response.getLastHeader("Location").getValue();
            switch (httpMethod) {
                case GET: {
                    // GET 自动执行302的重定向
                    break;
                }
                case POST: {
                    redirectResponse = post(locationUrl, httpEntity);  //跳转到重定向的url
                    break;
                }

                case PUT: {
                    redirectResponse = put(locationUrl, httpEntity);
                    break;
                }
                case DELETE : {
                    redirectResponse = delete(locationUrl);
                    break;
                }
                default: {
                    logger.warn("other method to do");
                    break;
                }
            }
        }
        return redirectResponse;
    }



}
