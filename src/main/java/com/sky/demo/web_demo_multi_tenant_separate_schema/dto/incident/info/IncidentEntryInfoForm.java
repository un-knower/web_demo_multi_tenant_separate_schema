package com.sky.demo.web_demo_multi_tenant_separate_schema.dto.incident.info;

import java.io.Serializable;

/**
 * Created by user on 16/10/20.
 */
public class IncidentEntryInfoForm implements Serializable {

    private static final long serialVersionUID = -3206197728085754061L;
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

    public String getEntryUuid() {
        return entryUuid;
    }

    public void setEntryUuid(String entryUuid) {
        this.entryUuid = entryUuid;
    }

    public String getAppUuid() {
        return appUuid;
    }

    public void setAppUuid(String appUuid) {
        this.appUuid = appUuid;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogonName() {
        return logonName;
    }

    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getEntryType() {
        return entryType;
    }

    public void setEntryType(int entryType) {
        this.entryType = entryType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "IncidentEntryInfoForm{" +
                "entryUuid='" + entryUuid + '\'' +
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
