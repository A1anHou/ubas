package com.alan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EditRelationDao {
    void insertEditRecord(@Param("relationId")int relationId,@Param("parentId")int parentId, @Param("userId")int userId,@Param("relationAttr")String relationAttr, @Param("relationOld")String relationOld, @Param("relationNew")String relationNew, @Param("relationChangeTime") Date relationChangeTime);
}
