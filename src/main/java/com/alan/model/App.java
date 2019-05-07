package com.alan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

    private int appId;
    private String appName;
    private String appPackage;
    private String appType;
    private String appIcon;
    private Date appAddTime;
    private int useTime;
    private List<UseState> useStateList = new ArrayList<UseState>();

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

    public List<UseState> getUseStateList() {
        return useStateList;
    }

    public void setUseStateList(List<UseState> useStateList) {
        this.useStateList = useStateList;
    }

    public int getUseTime() {
        return useTime;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }


}
