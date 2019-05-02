package com.alan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EditAdminDao {
    void insertEditRecord(@Param("adminId")int adminId, @Param("adminAttr")String adminAttr, @Param("adminOld")String adminOld, @Param("adminNew")String adminNew,@Param("optAdminId")int optAdminId, @Param("adminChangeTime") Date adminChangeTime);
}
