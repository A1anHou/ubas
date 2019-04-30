package com.alan.dao;

import com.alan.model.Location;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationDao {
    List<Location> selectLocationByUserIdAndDate(@Param("userId")int userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

    void insertLocation(@Param("userId")int userId, @Param("longitude")double longitude, @Param("latitude")double latitude, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

    List<Location> selectLocationByUserId(int userId);
}
