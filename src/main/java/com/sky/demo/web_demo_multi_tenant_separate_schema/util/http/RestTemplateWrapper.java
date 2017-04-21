/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.commons.collections.CollectionUtils
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.HttpClient
 *  org.apache.http.conn.socket.LayeredConnectionSocketFactory
 *  org.apache.http.conn.ssl.SSLConnectionSocketFactory
 *  org.apache.http.conn.ssl.SSLContextBuilder
 *  org.apache.http.conn.ssl.TrustStrategy
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 *  org.apache.http.impl.client.HttpClients
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.http.client.ClientHttpRequestFactory
 *  org.springframework.http.client.HttpComponentsClientHttpRequestFactory
 *  org.springframework.http.converter.HttpMessageConverter
 *  org.springframework.http.converter.StringHttpMessageConverter
 *  org.springframework.stereotype.Component
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.util.http;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class RestTemplateWrapper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpComponentsClientHttpRequestFactory factory;

    private ThreadLocal<RestTemplateEx> template = new ThreadLocal<RestTemplateEx>(){

        @Override
        protected RestTemplateEx initialValue() {
            RestTemplateEx restTemplate = new RestTemplateEx();
            RestTemplateWrapper.this.logger.debug("Initiate RestTemplate");
            try {
                factory = RestTemplateWrapper.buildTrustFactory();
            } catch (Exception e) {
                logger.error("get Factory error", e);
            }
            restTemplate.setRequestFactory(factory);
            RestTemplateWrapper.replaceStringMessageConverter(restTemplate.getMessageConverters());
            return restTemplate;
        }
    };



    private static void replaceStringMessageConverter(List<HttpMessageConverter<?>> messageConverters) {
        if (CollectionUtils.isNotEmpty(messageConverters)) {
            int index = -1;
            for (int i = 0; i < messageConverters.size(); ++i) {
                HttpMessageConverter converter = messageConverters.get(i);
                if (!(converter instanceof StringHttpMessageConverter)) continue;
                index = i;
            }
            if (index != -1) {
                messageConverters.remove(index);
                messageConverters.add(0, (StringHttpMessageConverter)new StringHttpMessageConverter(Charset.forName("UTF-8")));
            }
        }
    }

    public RestTemplateEx getTemplate() {
        return this.getTemplate(null);
    }

    public RestTemplateEx getTemplate(Map<String, String> headers) {
        HeaderInterceptor interceptor = null;
        interceptor = headers != null && headers.size() > 0 ? new HeaderInterceptor(headers) : new HeaderInterceptor();
        template.get().setInterceptors(Collections.singletonList(interceptor));
        return template.get();
    }

    @PreDestroy
    public void destroy() throws Exception {
        if (factory != null) {
            factory.destroy();
        }
    }


    public static HttpComponentsClientHttpRequestFactory buildTrustFactory() throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        SSLContextBuilder builder = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });

        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslSF)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        //set timeout
        requestFactory.setConnectTimeout(20 * 1000);
        requestFactory.setReadTimeout(60 * 1000);
        return requestFactory;
    }

}

