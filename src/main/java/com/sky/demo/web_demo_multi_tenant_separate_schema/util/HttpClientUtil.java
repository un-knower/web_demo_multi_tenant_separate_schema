package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

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
            logger.info("http request url:{}", httpGet.getURI());

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
            logger.info("http request url:{}", httpPost.getURI());

            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

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
            logger.info("http request url:{}", httpPut.getURI());

            httpPut.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPut);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

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
            logger.info("http request url:{}", httpDelete.getURI());

            httpResponse = httpClient.execute(httpDelete);
            logger.info("http response status: {}", httpResponse.getStatusLine());     // 响应状态

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
            logger.info("http request url:{}", httpGet.getURI());

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
            logger.info("http request url:{}", httpDelete.getURI());

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
                    logger.info("http request url:{}", httpGet.getURI());

                    response = httpClient.execute(httpGet);
                    break;
                }
                case POST: {
                    HttpPost httpPost = new HttpPost(url);
                    logger.info("http request url:{}", httpPost.getURI());

                    httpPost.setEntity(httpEntity);
                    response = httpClient.execute(httpPost);
                    httpPost.abort();         //POST, PUT 不会重定向，需要释放请求
                    break;
                }

                case PUT: {
                    HttpPut httpPut = new HttpPut(url);
                    logger.info("http request url:{}", httpPut.getURI());

                    httpPut.setEntity(httpEntity);
                    response = httpClient.execute(httpPut);
                    httpPut.abort();         //POST, PUT 不会重定向，需要释放请求
                    break;
                }
                case DELETE :{
                    HttpDelete httpDelete = new HttpDelete(url);
                    logger.info("http request url:{}", httpDelete.getURI());

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
            logger.error("redirect error", e);
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




    //========================== SSL ================================//


    /**
     * HttpClient连接SSL，不需要导入证书
     * 信任所有证书，跳过证书验证
     * @param url
     */
    public static HttpResponse requestSSLNoCert(HttpMethod httpMethod, String url, HttpEntity httpEntity, List<Header> headers) {
        Preconditions.checkState(StringUtils.isNotBlank(url), "url is blank!");

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = createSSLClientDefault(headers);

            switch (httpMethod) {
                case GET: {
                    HttpGet httpGet = new HttpGet(url);
                    logger.info("http request url:{}", httpGet.getURI());

                    response = httpClient.execute(httpGet);
                    break;
                }
                case POST: {
                    HttpPost httpPost = new HttpPost(url);
                    logger.info("http request url:{}", httpPost.getURI());

                    httpPost.setEntity(httpEntity);
                    response = httpClient.execute(httpPost);
                    break;
                }
                case PUT: {
                    HttpPut httpPut = new HttpPut(url);
                    logger.info("http request url:{}", httpPut.getURI());

                    httpPut.setEntity(httpEntity);
                    response = httpClient.execute(httpPut);
                    break;
                }
                case DELETE: {
                    HttpDelete httpDelete = new HttpDelete(url);
                    logger.info("http request url:{}", httpDelete.getURI());

                    response = httpClient.execute(httpDelete);
                    break;
                }
                default: {
                    logger.warn("other method to do");
                    break;
                }
            }
            logger.info("http response status: {}", response.getStatusLine());     // 响应状态

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }


    /**
     * SSL信任所有证书
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault(List<Header> headers) {
        CloseableHttpClient httpClient = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {
                // 信任所有证书
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); //

            httpClient = HttpClients.custom()
                    .setDefaultHeaders(headers)
                    .setSSLSocketFactory(socketFactory)
//                    .setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .build();


        } catch (KeyManagementException e) {
            logger.error("create ssl error", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("create ssl error", e);
        } catch (KeyStoreException e) {
            logger.error("create ssl error", e);
        }
        return httpClient;
    }


    /**
     * SSL导入证书
     * @return
     */
    public static CloseableHttpClient createSSLClientWithCert() {
        CloseableHttpClient httpClient = null;
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream inputStream = new FileInputStream(new File("E:\\tomcat.keystore"));
            try {
                // 加载keyStore
                trustStore.load(inputStream, "123456".toCharArray());
            } catch (CertificateException e) {
               logger.error("load truststore error", e);
            } finally {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    logger.error("close input stream error", e);
                }
            }


            // 相信自己的CA和所有自签名的证书
            SSLContext sslContext = SSLContexts
                    .custom()
                    .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                    .build();

            // 只允许使用TLSv1协议
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[] { "TLSv1" },
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(socketFactory)
                    .build();


        } catch (NoSuchAlgorithmException e) {
            logger.error("create ssl error", e);
        } catch (KeyStoreException e) {
            logger.error("create ssl error", e);
        } catch (KeyManagementException e) {
            logger.error("create ssl error", e);
        } catch (FileNotFoundException e) {
            logger.error("create ssl error", e);
        } catch (IOException e) {
            logger.error("create ssl error", e);
        }
        return httpClient;
    }


    public static List<Header> buildHeaders() {

        Header headerContentType = new BasicHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE, "application/json");
        Header headerAccept = new BasicHeader(org.springframework.http.HttpHeaders.ACCEPT, "text/plain;charset=UTF-8");
        Header headerUserAgent = new BasicHeader(org.springframework.http.HttpHeaders.USER_AGENT, "Twisted Web Client Example");
        Header headerAuthorization = new BasicHeader(org.springframework.http.HttpHeaders.AUTHORIZATION, "Basic MTQ3Mzc1OTIzNDI1ODozZTc0MWI3NTY2OTJmZjhkM2M2MmE4NjI2NGQwNDRmODAwNDk0YWJiYjM4ZjJmMjA3NjgxMzFlMDQ0NjE2MDM2OlBSMTI4MEgxNjA1MDkwMDAx");
        List<Header> headers = Lists.newArrayList(headerContentType, headerAccept, headerUserAgent, headerAuthorization);

        return headers;
    }
}
