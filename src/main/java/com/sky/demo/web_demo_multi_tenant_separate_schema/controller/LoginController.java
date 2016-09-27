package com.sky.demo.web_demo_multi_tenant_separate_schema.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetData;
import com.sky.demo.web_demo_multi_tenant_separate_schema.base.RetStatus;
import com.sky.demo.web_demo_multi_tenant_separate_schema.context.AppContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantUserForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.model.Account;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.AccountService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantUserService;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.RetUtil;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SessionUtil;

/**
 * Created by user on 16/9/24.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Resource
    private TenantUserService tenantUserService;
    @Resource
    private AccountService accountService;


    @RequestMapping("/login")
    public ModelAndView login(@RequestParam String userName, @RequestParam String password,
                                         HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            AppContext.initAppResourcesByUserName(userName);
            TenantUserForm tenantUserForm = tenantUserService.queryByUserName(userName);

            if (tenantUserForm != null) {
                //validate userName and password
                Account account = accountService.query(userName, password);
                if (account == null) {
                    modelAndView.setViewName("error");

                } else {
                    //login success
                    SessionUtil.setSessionInfo(request, tenantUserForm);

                    modelAndView.addObject("sessionId", SessionUtil.getSessionId(request));
                    modelAndView.addObject("tenantUser", tenantUserForm);
                    modelAndView.setViewName("welcome");
                }
            } else {
                modelAndView.setViewName("error");
            }

        } catch (Exception e) {
            logger.error("login error", e);
            modelAndView.setViewName("error");
        } finally {
            AppContext.releaseAppResources();
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public RetData<String> logout(HttpServletRequest request, HttpServletResponse response) {

        RetData<String> result = null;
        try {
            SessionUtil.removeSessionInfo(request);

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("logout error", e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        } finally {
            AppContext.releaseAppResources();
        }
        return result;
    }


}
