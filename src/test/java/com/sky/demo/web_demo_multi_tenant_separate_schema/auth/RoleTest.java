package com.sky.demo.web_demo_multi_tenant_separate_schema.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by user on 16/9/28.
 */
public class RoleTest {



    @Test
    public void testHasRole() {
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
}
