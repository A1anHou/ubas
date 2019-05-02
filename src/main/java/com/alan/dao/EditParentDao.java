package com.alan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EditParentDao {
    void insertEditRecord(@Param("parentId")int parentId, @Param("parentAttr")String parentAttr, @Param("parentOld")String parentOld, @Param("parentNew")String parentNew, @Param("parentChangeTime")Date parentChangeTime);
}
