package com.sky.demo.web_demo_multi_tenant_separate_schema.util.http;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultRedirectStrategy;

/**
 * Created by user on 16/12/6.
 */
public class LaxRedirectStrategyEx  extends DefaultRedirectStrategy {

    /**
     * Redirectable methods.
     */
    private static final String[] REDIRECT_METHODS = new String[] {
            HttpGet.METHOD_NAME,
            HttpPost.METHOD_NAME,
            HttpHead.METHOD_NAME,
            HttpDelete.METHOD_NAME,
            HttpPut.METHOD_NAME
    };

    @Override
    protected boolean isRedirectable(final String method) {
        for (final String m: REDIRECT_METHODS) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

}
