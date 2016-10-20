/*
 * Decompiled with CFR 0_118.
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.info;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class IncidentEntryInfo implements Serializable {

    private static final long serialVersionUID = -393895083912837132L;
    @JsonIgnore
    private long id;

    private String entryUuid;
    private String appUuid;
    private String commonName;
    private String distinguishedName;
    private String fullName;
    private String logonName;
    private String department;
    private String manager;
    private String title;
    private String mobile;
    private String telephone;
    private String mail;
    private String username;
    private String ip;
    private String hostname;
    private String domain;
    private String deviceName;
    private String appName;
    private int entryType;
    private String countryCode;
    private String regionCode;
    private String cityCode;
    private String url;

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDistinguishedName() {
        return this.distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public int getEntryType() {
        return this.entryType;
    }

    public void setEntryType(int entryType) {
        this.entryType = entryType;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLogonName() {
        return this.logonName;
    }

    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntryUuid() {
        return this.entryUuid;
    }

    public void setEntryUuid(String entryUuid) {
        this.entryUuid = entryUuid;
    }

    public String getAppUuid() {
        return this.appUuid;
    }

    public void setAppUuid(String appUuid) {
        this.appUuid = appUuid;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return this.manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "IncidentEntryInfo{" +
                "id=" + id +
                ", entryUuid='" + entryUuid + '\'' +
                ", appUuid='" + appUuid + '\'' +
                ", commonName='" + commonName + '\'' +
                ", distinguishedName='" + distinguishedName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", logonName='" + logonName + '\'' +
                ", department='" + department + '\'' +
                ", manager='" + manager + '\'' +
                ", title='" + title + '\'' +
                ", mobile='" + mobile + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", domain='" + domain + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", appName='" + appName + '\'' +
                ", entryType=" + entryType +
                ", countryCode='" + countryCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

