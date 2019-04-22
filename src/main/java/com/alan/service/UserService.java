package com.alan.service;

import com.alan.model.User;

import java.util.List;

/**
 * Created by AlanHou on 2019/4/15.
 */
public interface UserService {
    List<User> getAllUser();

    List<User> getUserByIdOrName(String keyword);
//
//    User getUserByPhoneOrEmail(String emailOrPhone, Short state);
//
//    User getUserById(Long userId);
}
