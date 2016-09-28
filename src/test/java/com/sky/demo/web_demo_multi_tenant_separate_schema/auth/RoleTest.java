package com.sky.demo.web_demo_multi_tenant_separate_schema.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by user on 16/9/28.
 */
public class RoleTest {


    @Test
    public void test_HasRole() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro-role.ini", token);

        Subject subject = SecurityUtils.getSubject();
        //判断拥有角色：role1
        Assert.assertTrue(subject.hasRole("role1"));

        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));

        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }




    @Test
    public void test_IsPermitted() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro-permission.ini", token);

        Subject subject = SecurityUtils.getSubject();
        //判断拥有权限：user:create
        Assert.assertTrue(subject.isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject.isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void test_CheckPermission () {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        BaseAuth.login("classpath:auth/shiro-permission.ini", token);

        Subject subject = SecurityUtils.getSubject();
        //断言拥有权限：user:create
        subject.checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject.checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject.checkPermissions("user:view");
    }
}
