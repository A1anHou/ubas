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
}
