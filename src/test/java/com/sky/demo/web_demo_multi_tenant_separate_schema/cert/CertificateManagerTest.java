package com.sky.demo.web_demo_multi_tenant_separate_schema.cert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by user on 16/11/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml"})
public class CertificateManagerTest {

    @Resource
    private CertificateManager certificateManager;

    @Test
    public void test_initSPSDefaultCert() {

        certificateManager.initSPSDefaultCert();

    }
}
