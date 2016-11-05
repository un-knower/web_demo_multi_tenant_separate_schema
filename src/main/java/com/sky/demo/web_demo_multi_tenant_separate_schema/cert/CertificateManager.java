/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.skyguard.sps.base.common.util.DBHostInfo
 *  com.skyguard.sps.base.utils.certificate.CertificateInfo
 *  com.skyguard.sps.base.utils.certificate.CertificateUtils
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 *  org.bouncycastle.asn1.DEREncodable
 *  org.bouncycastle.asn1.DERObjectIdentifier
 *  org.bouncycastle.asn1.x509.BasicConstraints
 *  org.bouncycastle.asn1.x509.X509Extensions
 *  org.bouncycastle.asn1.x509.X509Name
 *  org.bouncycastle.x509.X509V3CertificateGenerator
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Component
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.cert;

import com.sky.demo.web_demo_multi_tenant_separate_schema.cert.certificate.CertificateInfo;
import com.sky.demo.web_demo_multi_tenant_separate_schema.cert.certificate.CertificateUtils;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.AppConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

@Component
public class CertificateManager {

    protected static final Logger logger = LoggerFactory.getLogger(CertificateManager.class);

    @Resource
    private CertificateFactory certificateFactory;

    //@PostConstruct
    public void initSPSDefaultCert() {
        createSPSManagerCert();
        createSPSWebServiceCert();
        createThriftKeyStore();
    }

    private void createSPSManagerCert() {
        try {
            if (!Files.exists(Paths.get(AppConfig.getItem("webManager.cert.path"), new String[0]), new LinkOption[0])) {
                CertificateInfo certificateInfo = this.certificateFactory.instanceCertificateInfo(
                        new X509Name("C=CH, ST=BJ, L=BJ, O=skyguard, OU=skyguard, CN=webManager.skyguardmis.com"));

                X509V3CertificateGenerator gen = CertificateUtils.createCertGenerator(certificateInfo);
                gen.addExtension(X509Extensions.BasicConstraints, false, new BasicConstraints(false));
                X509Certificate newCert = gen.generate(certificateInfo.getIntermidateKeyPair().getPrivate(), "BC");

                Files.write(Paths.get(AppConfig.getItem("webManager.private.key.path"), new String[0]),
                        CertificateUtils.convertToBase64PEMString(certificateInfo.getKeyPair()).getBytes("utf-8"), new OpenOption[0]);
                Files.write(Paths.get(AppConfig.getItem("webManager.cert.path"), new String[0]),
                        CertificateUtils.convertToBase64PEMString(newCert).getBytes("utf-8"), new OpenOption[0]);
            }
        } catch (Exception e) {
            logger.error("create default sps cert failed", e);
        }
    }

    private void createSPSWebServiceCert() {
        try {
            if (!Files.exists(Paths.get(AppConfig.getItem("webService.cert.path"), new String[0]), new LinkOption[0])) {
                CertificateInfo certificateInfo = this.certificateFactory.instanceCertificateInfo(
                        new X509Name("C=CH, ST=BJ, L=BJ, O=skyguard, OU=skyguard, CN=webService.skyguardmis.com"));
                X509V3CertificateGenerator gen = CertificateUtils.createCertGenerator(certificateInfo);
                gen.addExtension(X509Extensions.BasicConstraints, false, new BasicConstraints(false));
                X509Certificate newCert = gen.generate(certificateInfo.getIntermidateKeyPair().getPrivate(), "BC");

                Files.write(Paths.get(AppConfig.getItem("webService.private.key.path"), new String[0]),
                        CertificateUtils.convertToBase64PEMString((Object)certificateInfo.getKeyPair()).getBytes("utf-8"), new OpenOption[0]);
                Files.write(Paths.get(AppConfig.getItem("webService.cert.path"), new String[0]),
                        CertificateUtils.convertToBase64PEMString((Object)newCert).getBytes("utf-8"), new OpenOption[0]);
            }
        } catch (Exception e) {
            logger.error("create default sps web service cert failed", e);
        }
    }

    private void createThriftKeyStore() {
        try {
            if (!Files.exists(Paths.get(AppConfig.getItem("keystore.file"), new String[0]), new LinkOption[0])) {
                CertificateInfo certificateInfo = certificateFactory.instanceCertificateInfo(
                        new X509Name("C=CH, ST=BJ, L=BJ, O=skyguard, OU=skyguard, CN=thrift.skyguardmis.com"));
                X509V3CertificateGenerator gen = CertificateUtils.createCertGenerator(certificateInfo);
                gen.addExtension(X509Extensions.BasicConstraints, false, new BasicConstraints(false));

                X509Certificate newCert = gen.generate(certificateInfo.getIntermidateKeyPair().getPrivate(), "BC");
                Certificate[] certChain = new X509Certificate[]{newCert};
                createKeyStore(Paths.get(AppConfig.getItem("keystore.file"), new String[0]), certChain,
                        certificateInfo.getKeyPair().getPrivate(), AppConfig.getItem("sps.thrift.password"), "");
            }
        } catch (Exception e) {
            logger.error("create default sps cert failed", e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createKeyStore(Path filePath, Certificate[] certChains, Key key, String password, String alias) {
        FileOutputStream fileOutputStream = null;
        try {
            KeyStore store = KeyStore.getInstance("JKS");
            store.load(null, null);
            store.setKeyEntry(alias, key, password.toCharArray(), certChains);
            fileOutputStream = FileUtils.openOutputStream(filePath.toFile());
            store.store(fileOutputStream, password.toCharArray());
        } catch (Exception e) {
            logger.error("create pkcs12 failed", e);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

}

