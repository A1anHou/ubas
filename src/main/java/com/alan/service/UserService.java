package com.alan.service;

import com.alan.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by AlanHou on 2019/4/15.
 */
public interface UserService {
    List<User> getAllUser();

    List<User> getUserByIdOrName(String keyword);

    List<User> getRecentUser(int num);

    int getUserNumByDate(Date startTime, Date endTime);

    User getUserById(int userId);

    User getUserByTel(long userTel);

    void addUser(long userTel, String userName, String userPwd, Date userBirthday, int userGender, String userJob, Date userRegTime);

    void editUserInfo(int userId, long userTel, String userJob);

    void editUserPwd(int userId, String userPwd);

    void recordEdit(int userId, String userAttr, String userOld, String userNew, Date userChangeTime);
}
