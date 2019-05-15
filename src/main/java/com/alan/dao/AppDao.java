package com.alan.dao;

import com.alan.model.App;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppDao {
    List<App> selectAllApp();

    List<App> selectAppByNameOrType(String keyword);

    void deleteApp(int appId);

    void updateAppCategory(@Param("appId")int appId, @Param("appType")String appType);

    List<App> selectRecentApp(int num);

    int selectAppNumByDate(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    App selectAppById(int appId);

    int selectAppIdByPackage(String appPackage);

    void insertApp(@Param("appName")String appName, @Param("appPackage")String appPackage, @Param("appType")String appType, @Param("appIcon")String appIcon, @Param("appAddTime")Date appAddTime, @Param("submitUserId")int submitUserId);

    String selectAppPackageByAppId(int appId);

    App selectAppByPackage(String appPackage);
}
