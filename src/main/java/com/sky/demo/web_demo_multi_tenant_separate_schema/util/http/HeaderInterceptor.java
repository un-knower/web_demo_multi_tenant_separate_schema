/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.springframework.http.HttpHeaders
 *  org.springframework.http.HttpRequest
 *  org.springframework.http.client.ClientHttpRequestExecution
 *  org.springframework.http.client.ClientHttpRequestInterceptor
 *  org.springframework.http.client.ClientHttpResponse
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.util.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    private static final String HEADER_ACCEPT = "application/json;text/json;charset=utf-8";
    private static final String HEADER_CONTENT_TYPE = "application/json;charset=utf-8";
    private static final String HEAD_CACHE_CONTROL = "must-revalidate, no-cache, no-store";

    private final Map<String, String> headers;

    public HeaderInterceptor() {
        this(null);
    }

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders httpHeaders = request.getHeaders();
        httpHeaders.set("Accept", "application/json;text/json;charset=utf-8");
        httpHeaders.set("Cache-Control", "must-revalidate, no-cache, no-store");
        httpHeaders.set("Content-Type", "application/json;charset=utf-8");
        httpHeaders.set("Content-Encoding", "utf-8");

        if (this.headers != null && this.headers.size() > 0) {
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("Content-Type")) {
                    httpHeaders.remove((Object)entry.getKey());
                }
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        return execution.execute(request, body);
    }
}

