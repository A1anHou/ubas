package com.alan.dao;

import com.alan.model.UseState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UseStateDao {
    List<UseState> selectUseStateByUserIdAndDate(@Param("userId")int userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

    List<UseState> selectUseStateByUserId(int userId);

    void insertUseState(@Param("userId")int userId, @Param("appId")int appId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
