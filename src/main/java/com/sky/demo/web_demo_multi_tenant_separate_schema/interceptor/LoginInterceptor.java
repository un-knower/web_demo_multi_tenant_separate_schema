package com.sky.demo.web_demo_multi_tenant_separate_schema.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.sky.demo.web_demo_multi_tenant_separate_schema.context.AppContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantUserForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.SessionInfo;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SessionUtil;

/**
 * Created by user on 16/9/24.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private static final List<String> IGNORE_URIS = Lists.newArrayList();

    static {
        IGNORE_URIS.add("/login.jsp");
        IGNORE_URIS.add("/login/");
        IGNORE_URIS.add("/login");
        IGNORE_URIS.add("/logout");
        IGNORE_URIS.add("/static");
        IGNORE_URIS.add("/js");
        IGNORE_URIS.add("/css");
        IGNORE_URIS.add("/images");

        IGNORE_URIS.add("/v1");

        IGNORE_URIS.add("/http");
        IGNORE_URIS.add("/report");
        IGNORE_URIS.add("/redis");
    }

    private static final Predicate<String> isContainIgnoreUrl = new Predicate<String>() {
        @Override
        public boolean apply(String input) {
            boolean result = false;
            for (String url : IGNORE_URIS) {
                if (input.contains(url) || url.contains(input)) {
                    result = true;
                    break;
                }
            }
            return result;
        }
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        AppContext.releaseResources();
//        DBContext.releaseResources();

        boolean flag = true;
        try {
            String url = request.getRequestURI();
            if (isContainIgnoreUrl.apply(url)) {
                flag = true;
            } else {
                SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
                if (sessionInfo == null || sessionInfo.getTenantUser() == null) {
                    logger.error("session info is null!! send 403");
                    response.sendError(403);
                    flag = false;
                } else {
                    //validate some url

                    TenantUserForm tenantUser = sessionInfo.getTenantUser();
//                    AppContext.initResourcesByUserName(tenantUser.getUserName());
                    DBContext.initResourcesByUserName(tenantUser.getUserName());

                }
            }
        } catch (Exception e) {
            logger.error("pre handle login error", e);
            flag = false;
        }
        return flag;
    }
}
