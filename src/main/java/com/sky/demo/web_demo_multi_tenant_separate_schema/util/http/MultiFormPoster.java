/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.commons.collections.MapUtils
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.entity.ContentType
 *  org.apache.http.entity.mime.MultipartEntityBuilder
 *  org.apache.http.entity.mime.content.ByteArrayBody
 *  org.apache.http.entity.mime.content.ContentBody
 *  org.apache.http.entity.mime.content.FileBody
 *  org.apache.http.entity.mime.content.InputStreamBody
 *  org.apache.http.entity.mime.content.StringBody
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.http.client.HttpComponentsClientHttpRequestFactory
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.util.http;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MultiFormPoster {
    private static final Logger logger = LoggerFactory.getLogger(MultiFormPoster.class);
    private ContentType contentType;
    private HttpComponentsClientHttpRequestFactory requestFactory;
    private Map<String, String> authHeaders;

    public MultiFormPoster(RestTemplateWrapper restTemplate) {
        this(restTemplate, null);
    }

    public MultiFormPoster(RestTemplateWrapper restTemplate, Map<String, String> authHeaders) {
        try {
            this.requestFactory = RestTemplateWrapper.buildTrustFactory();
            this.authHeaders = authHeaders;
        }
        catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
            logger.warn("getTrustFactory() error.", (Throwable)e);
        }
    }

    public HttpResponse postForm(String url, Map<String, ContentBody> body) throws Exception {
        HttpClient httpClient = this.requestFactory.getHttpClient();
        HttpPost request = new HttpPost(url);
        this.addAuthHeaders(request);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (Map.Entry<String, ContentBody> entry : body.entrySet()) {
            builder.addPart(entry.getKey(), entry.getValue());
        }
        HttpEntity entity = builder.build();
        request.setEntity(entity);
        HttpResponse response = httpClient.execute((HttpUriRequest)request);
        return response;
    }

    private void addAuthHeaders(HttpPost request) {
        if (MapUtils.isNotEmpty(this.authHeaders)) {
            this.authHeaders.forEach((name, value) -> {
                if (!"Content-Type".equals(name)) {
                    request.addHeader(name, value);
                }
            }
            );
        }
    }

    public void closeQuietly() {
        try {
            this.requestFactory.destroy();
        }
        catch (Exception var1_1) {
            // empty catch block
        }
    }

    public HttpResponse postForm(String url, String filedName, File file, String filename) throws Exception {
        HashMap<String, ContentBody> body = new HashMap<String, ContentBody>();
        if (this.contentType == null) {
            this.contentType = ContentType.DEFAULT_BINARY;
        }
        filename = filename == null ? file.getName() : filename;
        body.put(filedName, (ContentBody)new FileBody(file, this.contentType, filename));
        body.put("filename", (ContentBody)new StringBody(filename, ContentType.DEFAULT_TEXT));
        return this.postForm(url, body);
    }

    public HttpResponse postForm(String url, String filedName, byte[] data, String filename) throws Exception {
        HashMap<String, ContentBody> body = new HashMap<String, ContentBody>();
        if (this.contentType == null) {
            this.contentType = ContentType.DEFAULT_BINARY;
        }
        body.put(filedName, (ContentBody)new ByteArrayBody(data, this.contentType, filename));
        return this.postForm(url, body);
    }

    public HttpResponse postForm(String url, String filedName, InputStream is, String filename) throws Exception {
        HashMap<String, ContentBody> body = new HashMap<String, ContentBody>();
        if (this.contentType == null) {
            this.contentType = ContentType.DEFAULT_BINARY;
        }
        body.put(filedName, (ContentBody)new InputStreamBody(is, this.contentType, filename));
        return this.postForm(url, body);
    }

    public HttpResponse postForm(String url, String filedName, String str, String mimeType) throws Exception {
        HashMap<String, ContentBody> body = new HashMap<String, ContentBody>();
        body.put(filedName, (ContentBody)new StringBody(str, mimeType == null ? ContentType.create((String)mimeType) : ContentType.DEFAULT_TEXT));
        return this.postForm(url, body);
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}

