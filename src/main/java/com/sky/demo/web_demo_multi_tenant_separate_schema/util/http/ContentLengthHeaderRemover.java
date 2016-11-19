package com.sky.demo.web_demo_multi_tenant_separate_schema.util.http;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created by user on 16/11/19.
 */
public class ContentLengthHeaderRemover implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        // fighting org.apache.http.protocol.RequestContent's
        // ProtocolException("Content-Length header already present");
        request.removeHeaders(HTTP.CONTENT_LEN);
    }
}
