package com.alan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EditUserDao {
    void insertEditRecord(@Param("userId")int userId, @Param("userAttr")String userAttr, @Param("userOld")String userOld, @Param("userNew")String userNew, @Param("userChangeTime")Date userChangeTime);
}
