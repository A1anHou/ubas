package com.alan.service.impl;

import com.alan.dao.AppDao;
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

}
