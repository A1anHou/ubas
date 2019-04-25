package com.alan.dao;

import com.alan.model.Location;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationDao {
    List<Location> selectLocationByUserIdAndDate(@Param("userId")int userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
