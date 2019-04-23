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
}
