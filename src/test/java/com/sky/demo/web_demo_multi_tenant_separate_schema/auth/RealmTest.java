package com.sky.demo.web_demo_multi_tenant_separate_schema.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 16/9/27.
 */
public class RealmTest {

    private static final Logger logger = LoggerFactory.getLogger(RealmTest.class);

    @Test
    public void test_hello_world() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro.ini", token);

        Subject subject = SecurityUtils.getSubject();
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }


    @Test
    public void test_CustomMultiRealm() {
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "456");
        BaseAuth.login("classpath:auth/shiro-multi-realm.ini", token);

        Subject subject = SecurityUtils.getSubject();
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }


    @Test
    public void test_JDBCRealm() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro-jdbc-realm.ini", token);

        Subject subject = SecurityUtils.getSubject();
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }



}
