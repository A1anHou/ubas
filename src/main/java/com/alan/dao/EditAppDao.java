package com.alan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EditAppDao {
    void insertEditRecord(@Param("appId")int appId, @Param("appAttr")String appAttr, @Param("appOld")String appOld, @Param("appNew")String appNew, @Param("optAdminId")int optAdminId, @Param("appChangeTime") Date appChangeTime);
}
