package com.sky.demo.web_demo_multi_tenant_separate_schema.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 16/9/28.
 */
public class BaseAuth {

    private static final Logger logger = LoggerFactory.getLogger(BaseAuth.class);

    public static void login(String configFile, UsernamePasswordToken token) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            logger.error("check login error", e);
            if (e instanceof DisabledAccountException) {
                logger.error("禁用的帐号");
            } else if (e instanceof LockedAccountException) {
                logger.error("锁定的帐号");
            } else if (e instanceof UnknownAccountException) {
                logger.error("错误的帐号");
            } else if (e instanceof ExcessiveAttemptsException) {
                logger.error("登录失败次数过多");
            } else if (e instanceof IncorrectCredentialsException) {
                logger.error("错误的凭证");
            } else if (e instanceof ExpiredCredentialsException) {
                logger.error("过期的凭证");
            }
        }

    }


}
