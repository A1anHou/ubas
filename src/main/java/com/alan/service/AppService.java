package com.alan.service;

import com.alan.model.App;

import java.util.Date;
import java.util.List;

public interface AppService {
    List<App> getAllApp();

    List<App> getAppByNameOrType(String keyword);

    void delApp(int appId);

    void editAppType(int appId, String appType);

    List<App> getRecentApp(int num);

    int getAppNumByDate(Date date, Date needTime);

    App getAppById(int appId);

    int getAppIdByPackage(String appPackage);

    void addApp(String appName, String appPackage, String appType, String appIcon, Date appAddTime,int submitUserId);

    String getAppPackageByAppId(int appId);

    void recordEdit(int appId, String appAttr, String appOld, String appNew, int optAdminId, Date appChangeTime);
}
