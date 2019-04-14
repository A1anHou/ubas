package com.alan.service.impl;

import com.alan.dao.AdminDao;
import com.alan.model.Admin;
import com.alan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin queryAdminByTel(long adminTel) {
        return adminDao.selectAdminByTel(adminTel);
    }
}
