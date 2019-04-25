package com.alan.dao;

import com.alan.model.Unlock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UnlockDao {
    List<Unlock> selectUnlockByUserIdAndDate(@Param("userId")int userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
