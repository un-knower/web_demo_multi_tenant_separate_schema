package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantUserForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.SessionInfo;

/**
 * Created by user on 16/9/24.
 */
public class SessionUtil {

    private static final String SESSION_INFO = "SESSION_INFO";

    public static void setSessionInfo(HttpServletRequest request, TenantUserForm tenantUser) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setTenantUser(tenantUser);

        request.getSession(true).setAttribute(SESSION_INFO, sessionInfo);
    }

    public static SessionInfo getSessionInfo(HttpServletRequest request) {
        SessionInfo sessionInfo = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionInfo = (SessionInfo) session.getAttribute(SESSION_INFO);
        }
        return sessionInfo;
    }

    public static void removeSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(SESSION_INFO);
            session.invalidate();
        }
    }

    public static String getSessionId(HttpServletRequest request) {
        String id = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            id = session.getId();
        }
        return id;
    }
}
