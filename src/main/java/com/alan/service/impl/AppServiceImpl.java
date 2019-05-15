package com.alan.service.impl;

import com.alan.dao.AppDao;
import com.alan.dao.EditAppDao;
import com.alan.model.App;
import com.alan.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppServiceImpl implements AppService {
    @Autowired
    private AppDao appDao;
    private EditAppDao editAppDao;

    @Override
    public List<App> getAllApp() {
        return appDao.selectAllApp();
    }

    @Override
    public List<App> getAppByNameOrType(String keyword) {
        return appDao.selectAppByNameOrType(keyword);
    }

    @Override
    public void delApp(int appId) {
        appDao.deleteApp(appId);
    }

    @Override
    public void editAppType(int appId, String appType) {
        appDao.updateAppCategory(appId,appType);
    }

    @Override
    public List<App> getRecentApp(int num) {
        return appDao.selectRecentApp(num);
    }

    @Override
    public int getAppNumByDate(Date startTime, Date endTime) {
        return appDao.selectAppNumByDate(startTime,endTime);
    }

    @Override
    public App getAppById(int appId) {
        return appDao.selectAppById(appId);
    }

    @Override
    public int getAppIdByPackage(String appPackage) {
        return appDao.selectAppIdByPackage(appPackage);
    }

    @Override
    public void addApp(String appName, String appPackage, String appType, String appIcon, Date appAddTime,int submitUserId) {
        appDao.insertApp(appName,appPackage,appType,appIcon,appAddTime,submitUserId);
    }

    @Override
    public String getAppPackageByAppId(int appId) {
        return appDao.selectAppPackageByAppId(appId);
    }

    @Override
    public void recordEdit(int appId, String appAttr, String appOld, String appNew, int optAdminId, Date appChangeTime) {
        editAppDao.insertEditRecord(appId,appAttr,appOld,appNew,optAdminId,appChangeTime);
    }

    @Override
    public App getAppByPackage(String appPackage) {
        return appDao.selectAppByPackage(appPackage);
    }

}
