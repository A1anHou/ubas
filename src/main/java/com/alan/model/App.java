package com.alan.model;

import java.util.Date;

public class App {

    private int appId;
    private String appName;
    private String appPackage;
    private String appType;
    private String appIcon;
    private Date appAddTime;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public Date getAppAddTime() {
        return appAddTime;
    }

    public void setAppAddTime(Date appAddTime) {
        this.appAddTime = appAddTime;
    }
}
