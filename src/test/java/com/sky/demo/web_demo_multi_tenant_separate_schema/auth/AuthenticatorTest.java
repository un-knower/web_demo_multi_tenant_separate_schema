package com.sky.demo.web_demo_multi_tenant_separate_schema.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 16/9/28.
 */
public class AuthenticatorTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticatorTest.class);

    @Test
    public void test_AllSuccessfulStrategy() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro-authenticator-all-success.ini", token);

        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro-authenticator-all-fail.ini", token);

        Subject subject = SecurityUtils.getSubject();
    }

}
