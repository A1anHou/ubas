package com.alan.service.impl;

import com.alan.dao.AdminDao;
import com.alan.model.Admin;
import com.alan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin getAdminByTel(long adminTel) {
        return adminDao.selectAdminByTel(adminTel);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.selectAllAdmin();
    }

    @Override
    public Admin getAdminById(int id) {
        return adminDao.selectAdminById(id);
    }
}
