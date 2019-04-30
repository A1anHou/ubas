package com.alan.service.impl;

import com.alan.dao.UserDao;
import com.alan.model.User;
import com.alan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by AlanHou on 2019/4/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public User getUserByTel(long userTel) {
        return userDao.selectUserByTel(userTel);
    }

    @Override
    public void addUser(long userTel, String userName, String userPwd, Date userBirthday, int userGender, String userJob, Date userRegTime) {
        userDao.insertUser(userTel,userName,userPwd,userBirthday,userGender,userJob,userRegTime);
    }

    @Override
    public void editUserInfo(int userId, long userTel, String userJob) {
        userDao.updateUserInfo(userId,userTel,userJob);
    }

    @Override
    public void editUserPwd(int userId, String userPwd) {
        userDao.updateUserPwd(userId,userPwd);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public List<User> getUserByIdOrName(String keyword) {
        return userDao.selectUserByIdOrName(keyword);
    }

    @Override
    public List<User> getRecentUser(int num) {
        return userDao.selectRecentUser(num);
    }

    @Override
    public int getUserNumByDate(Date startTime, Date endTime) {
        return userDao.selectUserNumByDate(startTime,endTime);
    }

}
