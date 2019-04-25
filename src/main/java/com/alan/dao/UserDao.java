package com.alan.dao;

import com.alan.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Repository
public interface UserDao {

    User selectUserById(int userId);

    List<User> selectAllUser();

    List<User> selectUserByIdOrName(String keyword);

    List<User> selectRecentUser(int num);

    int selectUserNumByDate(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    User selectUserByTel(long userTel);
}
