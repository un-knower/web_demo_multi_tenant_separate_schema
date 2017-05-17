/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.sky.sps.base.common.util.DBHostInfo
 *  com.sky.sps.base.utils.certificate.CertificateInfo
 *  com.sky.sps.base.utils.certificate.CertificateInfo$Builder
 *  com.sky.sps.base.utils.certificate.CertificateUtils
 *  org.bouncycastle.asn1.x509.X509Name
 *  org.springframework.stereotype.Component
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.cert;

import com.sky.demo.web_demo_multi_tenant_separate_schema.cert.certificate.CertificateInfo;
import com.sky.demo.web_demo_multi_tenant_separate_schema.cert.certificate.CertificateUtils;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.bouncycastle.asn1.x509.X509Name;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Date;

@Component
public class CertificateFactory {

    public static final String WEB_SERVICE_SUBJECT = "C=CH, ST=BJ, L=BJ, O=sky, OU=sky, CN=webService.skymis.com";
    public static final String WEB_MANAGER_SUBJECT = "C=CH, ST=BJ, L=BJ, O=sky, OU=sky, CN=webManager.skymis.com";
    public static final String THRIFT_SUBJECT = "C=CH, ST=BJ, L=BJ, O=sky, OU=sky, CN=thrift.skymis.com";

    public CertificateInfo instanceCertificateInfo(X509Name subject) throws Exception {
        X509Certificate intermediateCertificate = CertificateUtils.loadCertificateFromFile(
                Paths.get(AppConfig.getItem("sps.certificate.path"), new String[0]));
        long beginDate = System.currentTimeMillis();
        long endDate = beginDate + CertificateUtils.DEFAULT_EXPIRATION;
        X509Name issuerDN = new X509Name(intermediateCertificate.getSubjectDN().getName());
        KeyPair certKey = CertificateUtils.genearateDefaultKeyPair("RSA", "BC", 2048);
        KeyPair intermediateKeyPair = CertificateUtils.loadKeyPairFromFile(
                Paths.get(AppConfig.getItem("sps.private.key.path"), new String[0]), "");

        CertificateInfo certificateInfo = new CertificateInfo.Builder()
                .beginDate(new Date(beginDate))
                .endDate(new Date(endDate))
                .issuerDN(issuerDN)
                .subjectName(subject)
                .keyPair(certKey)
                .signatureAlgorithm("SHA256WithRSAEncryption")
                .intermidateKeyPair(intermediateKeyPair)
                .build();
        return certificateInfo;
    }
}

