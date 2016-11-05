/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 *  org.bouncycastle.asn1.ASN1Set
 *  org.bouncycastle.asn1.DERBMPString
 *  org.bouncycastle.asn1.DEREncodable
 *  org.bouncycastle.asn1.DERObjectIdentifier
 *  org.bouncycastle.asn1.DERSet
 *  org.bouncycastle.asn1.pkcs.CertificationRequestInfo
 *  org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers
 *  org.bouncycastle.asn1.x509.BasicConstraints
 *  org.bouncycastle.asn1.x509.X509Extensions
 *  org.bouncycastle.asn1.x509.X509Name
 *  org.bouncycastle.jce.PKCS10CertificationRequest
 *  org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
 *  org.bouncycastle.jce.provider.BouncyCastleProvider
 *  org.bouncycastle.openssl.PEMReader
 *  org.bouncycastle.openssl.PEMWriter
 *  org.bouncycastle.openssl.PasswordFinder
 *  org.bouncycastle.util.encoders.Base64
 *  org.bouncycastle.x509.X509V3CertificateGenerator
 *  org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure
 *  org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure
 *  org.joda.time.LocalDate
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.cert.certificate;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.openssl.PasswordFinder;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class CertificateUtils {

    private static final Logger logger = LoggerFactory.getLogger(CertificateUtils.class);
    public static final Long DEFAULT_EXPIRATION = 315360000000L;
    public static final String SIGNNAME = "SHA256WithRSAEncryption";
    public static final String PROVIDER = "BC";
    public static final String ALGORITHM = "RSA";
    public static final int SIZE = 2048;

    static {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Object loadCertificate(Path filePath, final String pemPassword) throws Exception {
        PEMReader pemParser = null;
        InputStreamReader in = null;
        try {
            Object object;
            in = new InputStreamReader(new FileInputStream(filePath.toString()));
            pemParser = new PEMReader((Reader)in, new PasswordFinder(){

                public char[] getPassword() {
                    return pemPassword.toCharArray();
                }
            });
            Object object2 = object = pemParser.readObject();
            return object2;
        }
        finally {
            if (pemParser != null) {
                try {
                    pemParser.close();
                }
                catch (Exception e) {
                    logger.error("close pem parser failed");
                }
            }
            if (in != null) {
                try {
                    in.close();
                }
                catch (Exception e) {
                    logger.error("close InputStreamReader  failed");
                }
            }
        }
    }

    public static KeyPair loadKeyPairFromFile(Path filePath, String pemPassword) throws Exception {
        Object object = CertificateUtils.loadCertificate(filePath, pemPassword);
        if (object instanceof KeyPair) {
            return (KeyPair)object;
        }
        logger.error("can not get certificate");
        return null;
    }

    public static X509Certificate loadCertificateFromFile(Path certificatePah) throws Exception {
        Object object = CertificateUtils.loadCertificate(certificatePah, null);
        if (object instanceof X509Certificate) {
            return (X509Certificate)object;
        }
        logger.error("can not get certificate");
        return null;
    }

    public static PublicKey getPublicKeyFromCertFile(Path certPath) throws Exception {
        X509Certificate cert = CertificateUtils.loadCertificateFromFile(certPath);
        return cert.getPublicKey();
    }

    public static PKCS10CertificationRequest loadCSRFromFile(Path csrPath) throws Exception {
        Object object = CertificateUtils.loadCertificate(csrPath, null);
        if (object instanceof PKCS10CertificationRequest) {
            return (PKCS10CertificationRequest)object;
        }
        logger.error("can not read csr from " + csrPath);
        return null;
    }

    public static PKCS10CertificationRequest genCsrByX509Certificate(X509Certificate cert, PrivateKey privateKey) throws Exception {
        String subject = cert.getSubjectDN().getName();
        X509Name xname = new X509Name(subject);
        PKCS10CertificationRequest csr = new PKCS10CertificationRequest("SHA256WithRSAEncryption", xname, cert.getPublicKey(), (ASN1Set)new DERSet(), privateKey);
        return csr;
    }

    public static KeyPair genearateDefaultKeyPair(String algorithm, String provider, int size) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm, provider);
        kpg.initialize(size);
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }

    public static PKCS10CertificationRequest buildCSRFromBytes(byte[] csrBytes) {
        if (csrBytes == null || csrBytes.length == 0) {
            logger.error("input parameter is empty....");
            return null;
        }
        String temp = new String(csrBytes);
        if (temp.indexOf("-----BEGIN CERTIFICATE REQUEST") != -1) {
            temp = temp.substring(temp.indexOf("\n") + 1);
            temp = temp.substring(0, temp.indexOf("---"));
            csrBytes = temp.getBytes();
        }
        byte[] decoded = Base64.decode((byte[])csrBytes);
        return new PKCS10CertificationRequest(decoded);
    }

    public static X509Certificate signCSR(PKCS10CertificationRequest csr, PrivateKey issuerPrivateKey, X509Certificate issuerCertificate, int validDays) throws Exception {
        if (!csr.verify()) {
            logger.error("failed to verify this csr certificate." + (Object)csr.getCertificationRequestInfo().getSubject());
        }
        X509V3CertificateGenerator gen = new X509V3CertificateGenerator();
        X509Name issuerDN = new X509Name(issuerCertificate.getSubjectDN().getName());
        BigInteger serialNumber = BigInteger.probablePrime(64, new Random());
        gen.setIssuerDN(issuerDN);
        Date currentDate = LocalDate.now().toDate();
        gen.setNotBefore(currentDate);
        gen.setNotAfter(new Date(currentDate.getTime() + (long)(validDays * 24 * 60 * 60) * 1000));
        gen.setSerialNumber(serialNumber);
        gen.setPublicKey(csr.getPublicKey());
        gen.setSubjectDN(csr.getCertificationRequestInfo().getSubject());
        gen.setSignatureAlgorithm("SHA256WithRSAEncryption");
        gen.addExtension(X509Extensions.AuthorityKeyIdentifier, false, (DEREncodable)new AuthorityKeyIdentifierStructure(issuerCertificate));
        gen.addExtension(X509Extensions.SubjectKeyIdentifier, false, (DEREncodable)new SubjectKeyIdentifierStructure(csr.getPublicKey()));
        gen.addExtension(X509Extensions.BasicConstraints, false, (DEREncodable)new BasicConstraints(false));
        X509Certificate signCert = gen.generate(issuerPrivateKey);
        signCert.checkValidity(new Date());
        signCert.verify(issuerCertificate.getPublicKey());
        PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier)signCert;
        X509Name csrname = csr.getCertificationRequestInfo().getSubject();
        Vector dns = csrname.getValues();
        bagAttr.setBagAttribute(PKCSObjectIdentifiers.pkcs_9_at_friendlyName, (DEREncodable)new DERBMPString(dns.get(dns.size() - 1).toString()));
        bagAttr.setBagAttribute(PKCSObjectIdentifiers.pkcs_9_at_localKeyId, (DEREncodable)new SubjectKeyIdentifierStructure(csr.getPublicKey()));
        return signCert;
    }

    public static X509Certificate generateSelfSignedCertificate(CertificateInfo certificateInfo) throws NoSuchProviderException, NoSuchAlgorithmException, CertificateEncodingException, SignatureException, InvalidKeyException, CertificateParsingException {
        X509V3CertificateGenerator gen = CertificateUtils.createCertGenerator(certificateInfo);
        gen.addExtension(X509Extensions.BasicConstraints, false, (DEREncodable)new BasicConstraints(true));
        X509Certificate certificate = gen.generate(certificateInfo.getKeyPair().getPrivate(), "BC");
        return certificate;
    }

    public static X509V3CertificateGenerator createCertGenerator(CertificateInfo certificateInfo) throws CertificateParsingException {
        X509V3CertificateGenerator gen = new X509V3CertificateGenerator();
        if (certificateInfo != null) {
            gen.setSerialNumber(certificateInfo.getSerialNumber());
            gen.setSignatureAlgorithm(certificateInfo.getSignatureAlgorithm());
            gen.setPublicKey(certificateInfo.getKeyPair().getPublic());
            gen.setIssuerDN(certificateInfo.getIssuerDN());
            gen.setNotBefore(certificateInfo.getBeginDate());
            gen.setNotAfter(certificateInfo.getEndDate());
            gen.setSubjectDN(certificateInfo.getSubjectName());
            gen.addExtension(X509Extensions.SubjectKeyIdentifier, false, (DEREncodable)new SubjectKeyIdentifierStructure(certificateInfo.getKeyPair().getPublic()));
        }
        return gen;
    }

    public static void saveX509Certificate(X509Certificate cert, String certPath) throws CertificateEncodingException, IOException {
        FileOutputStream sout = new FileOutputStream(certPath);
        sout.write(cert.getEncoded());
        sout.close();
    }

    public static String convertToBase64PEMString(Object cert) throws CertificateEncodingException, IOException {
        StringWriter sw = new StringWriter();
        PEMWriter pw = new PEMWriter((Writer)sw);
        pw.writeObject(cert);
        pw.flush();
        pw.close();
        return sw.toString();
    }

    public static Certificate[] loadCertificateChainFromPKCS12(String p12Path, String caPassword, String caAlias) throws Exception {
        KeyStore caks = KeyStore.getInstance("PKCS12", "BC");
        caks.load(new FileInputStream(p12Path), caPassword.toCharArray());
        Certificate[] chains = caks.getCertificateChain(caAlias);
        return chains;
    }

    public static PrivateKey loadPrivateKeyFromPKCS12(String p12Path, String caPassword, String caAlias) throws Exception {
        KeyStore caks = KeyStore.getInstance("PKCS12", "BC");
        caks.load(new FileInputStream(p12Path), caPassword.toCharArray());
        PrivateKey key = (PrivateKey)caks.getKey(caAlias, caPassword.toCharArray());
        return key;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void createPKCS12(Path filePath, Certificate[] certChains, Key key, String password, String alias) {
        FileOutputStream sout = null;
        try {
            KeyStore store = KeyStore.getInstance("PKCS12", "BC");
            store.load(null, null);
            store.setKeyEntry(alias, key, null, certChains);
            sout = FileUtils.openOutputStream((File)filePath.toFile());
            store.store(sout, password.toCharArray());
        }
        catch (Exception e) {
            logger.error("create pkcs12 failed", (Throwable)e);
        }
        finally {
            IOUtils.closeQuietly((OutputStream)sout);
        }
    }

    public static KeyStore loadKeyStoreFromPKCS12(Path p12Path, String caPassword) throws Exception {
        KeyStore caks = KeyStore.getInstance("PKCS12", "BC");
        caks.load(new FileInputStream(p12Path.toString()), caPassword.toCharArray());
        return caks;
    }

    public static void main(String[] args) {
        try {
            X509Certificate intermediateCertificate = CertificateUtils.loadCertificateFromFile(Paths.get("D:\\certtest\\seven\\dlp-intermediate.crt", new String[0]));
            long beginDate = System.currentTimeMillis();
            long endDate = beginDate + DEFAULT_EXPIRATION;
            X509Name subjectName = new X509Name("C=CH, ST=BJ, L=BJ, O=skyguard, OU=skyguard, CN=endpoint.skyguardmis.com");
            X509Name issuerDN = new X509Name(intermediateCertificate.getSubjectDN().getName());
            KeyPair endpointKeyPair = CertificateUtils.genearateDefaultKeyPair("RSA", "BC", 2048);
            CertificateInfo certificateInfo = new CertificateInfo.Builder().beginDate(new Date(beginDate)).endDate(new Date(endDate)).issuerDN(issuerDN).subjectName(subjectName).keyPair(endpointKeyPair).signatureAlgorithm("SHA256WithRSAEncryption").build();
            X509V3CertificateGenerator gen = CertificateUtils.createCertGenerator(certificateInfo);
            gen.addExtension(X509Extensions.BasicConstraints, false, (DEREncodable)new BasicConstraints(false));
            KeyPair intermidateKeyPair = CertificateUtils.loadKeyPairFromFile(Paths.get("D:\\certtest\\seven\\dlp-intermediate.key", new String[0]), "");
            X509Certificate endpointCert = gen.generate(intermidateKeyPair.getPrivate(), "BC");
            Certificate[] certChain = new X509Certificate[]{endpointCert};
            CertificateUtils.createPKCS12(Paths.get("D:\\certtest\\seven\\endpoint.pfx", new String[0]), certChain, endpointKeyPair.getPrivate(), "123", "endpoint");
        }
        catch (Exception e) {
            logger.error("Create endpoint pkcs12 file failed." + e.getMessage());
        }
    }

    private static void signsps() {
        try {
            String clientcsr = "D:\\certtest\\seven\\webService.csr";
            X509Certificate ca = CertificateUtils.loadCertificateFromFile(Paths.get("D:\\certtest\\seven\\dlp-intermediate.crt", new String[0]));
            KeyPair keyPair = CertificateUtils.loadKeyPairFromFile(Paths.get("D:\\certtest\\seven\\dlp-intermediate.key", new String[0]), "");
            PKCS10CertificationRequest csr = CertificateUtils.loadCSRFromFile(Paths.get(clientcsr, new String[0]));
            X509Certificate clientcert = CertificateUtils.signCSR(csr, keyPair.getPrivate(), ca, 3650);
            String certstring = CertificateUtils.convertToBase64PEMString(clientcert);
            FileOutputStream sout = new FileOutputStream("D:\\certtest\\seven\\webService.crt");
            sout.write(certstring.getBytes());
            sout.flush();
            sout.close();
        }
        catch (Exception e) {
            logger.debug("ssss");
            e.printStackTrace();
        }
    }

    private static void signspe() {
        try {
            String clientcsr = "d:\\certtest\\cert\\webManager.csr";
            KeyStore keyStore = CertificateUtils.loadKeyStoreFromPKCS12(Paths.get("d:\\certtest\\cert\\ca.p12", new String[0]), "ca");
            Certificate[] chain = keyStore.getCertificateChain("ca");
            X509Certificate ca = null;
            if (chain != null && chain.length > 0) {
                ca = (X509Certificate)chain[0];
            }
            PrivateKey key = (PrivateKey)keyStore.getKey("ca", "ca".toCharArray());
            PKCS10CertificationRequest csr = CertificateUtils.loadCSRFromFile(Paths.get(clientcsr, new String[0]));
            X509Certificate clientcert = CertificateUtils.signCSR(csr, key, ca, 3650);
            String certstring = CertificateUtils.convertToBase64PEMString(clientcert);
            FileOutputStream sout = new FileOutputStream("d:\\certtest\\cert\\sps1.cer");
            sout.write(certstring.getBytes());
            sout.flush();
            sout.close();
        }
        catch (Exception e) {
            logger.debug("ssss");
            e.printStackTrace();
        }
    }


}

