/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Objects$ToStringHelper
 *  org.bouncycastle.asn1.x509.X509Name
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.cert.certificate;

import org.bouncycastle.asn1.x509.X509Name;

import java.math.BigInteger;
import java.security.KeyPair;
import java.util.Date;
import java.util.Random;

public class CertificateInfo {

    private BigInteger serialNumber = BigInteger.probablePrime(64, new Random());
    private X509Name issuerDN;
    private X509Name subjectName;
    private Date beginDate = new Date(System.currentTimeMillis());
    private Date endDate = new Date(System.currentTimeMillis());
    private KeyPair keyPair;
    private String signatureAlgorithm = "SHA256WithRSAEncryption";
    private KeyPair intermidateKeyPair;

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }

    public X509Name getIssuerDN() {
        return this.issuerDN;
    }

    public void setIssuerDN(X509Name issuerDN) {
        this.issuerDN = issuerDN;
    }

    public X509Name getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(X509Name subjectName) {
        this.subjectName = subjectName;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public KeyPair getIntermidateKeyPair() {
        return this.intermidateKeyPair;
    }

    public void setIntermidateKeyPair(KeyPair intermidateKeyPair) {
        this.intermidateKeyPair = intermidateKeyPair;
    }

    @Override
    public String toString() {
        return "CertificateInfo{" +
                "serialNumber=" + serialNumber +
                ", issuerDN=" + issuerDN +
                ", subjectName=" + subjectName +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", keyPair=" + keyPair +
                ", signatureAlgorithm='" + signatureAlgorithm + '\'' +
                ", intermidateKeyPair=" + intermidateKeyPair +
                '}';
    }

    public CertificateInfo(Builder builder) {
        this.setSerialNumber(builder.serialNumber);
        this.setBeginDate(builder.beginDate);
        this.setEndDate(builder.endDate);
        this.setIssuerDN(builder.issuerDN);
        this.setKeyPair(builder.keyPair);
        this.setSubjectName(builder.subjectName);
        this.setSignatureAlgorithm(builder.signatureAlgorithm);
        this.setIntermidateKeyPair(builder.intermidateKeyPair);
    }

    public static class Builder {
        private BigInteger serialNumber = BigInteger.probablePrime(64, new Random());
        private X509Name issuerDN;
        private X509Name subjectName;
        private Date beginDate = new Date(System.currentTimeMillis());
        private Date endDate = new Date(System.currentTimeMillis());
        private KeyPair keyPair;
        private String signatureAlgorithm = "SHA1WithRSAEncryption";
        private KeyPair intermidateKeyPair;

        public Builder intermidateKeyPair(KeyPair intermidateKeyPair) {
            this.intermidateKeyPair = intermidateKeyPair;
            return this;
        }

        public Builder serialNumber(BigInteger serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder issuerDN(X509Name issuerDN) {
            this.issuerDN = issuerDN;
            return this;
        }

        public Builder subjectName(X509Name subjectName) {
            this.subjectName = subjectName;
            return this;
        }

        public Builder beginDate(Date beginDate) {
            this.beginDate = beginDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder keyPair(KeyPair keyPair) {
            this.keyPair = keyPair;
            return this;
        }

        public Builder signatureAlgorithm(String signatureAlgorithm) {
            this.signatureAlgorithm = signatureAlgorithm;
            return this;
        }

        public CertificateInfo build() {
            return new CertificateInfo(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "serialNumber=" + serialNumber +
                    ", issuerDN=" + issuerDN +
                    ", subjectName=" + subjectName +
                    ", beginDate=" + beginDate +
                    ", endDate=" + endDate +
                    ", keyPair=" + keyPair +
                    ", signatureAlgorithm='" + signatureAlgorithm + '\'' +
                    ", intermidateKeyPair=" + intermidateKeyPair +
                    '}';
        }
    }

}

