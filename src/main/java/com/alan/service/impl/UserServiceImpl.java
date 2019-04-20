package com.alan.service.impl;

import com.alan.dao.UserDao;
import com.alan.model.User;
import com.alan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by AlanHou on 2019/4/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

//    public User getUserById(Long userId) {
//        return userDao.selectUserById(userId);
//    }
//
//    public User getUserByPhoneOrEmail(String emailOrPhone, Short state) {
//        return userDao.selectUserByPhoneOrEmail(emailOrPhone,state);
//    }
//
    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }
}
