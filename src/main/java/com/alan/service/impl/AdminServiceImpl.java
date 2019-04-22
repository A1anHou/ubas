package com.alan.service.impl;

import com.alan.dao.AdminDao;
import com.alan.model.Admin;
import com.alan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public void delAdmin(int adminId) {
        adminDao.deleteAdmin(adminId);
    }

    @Override
    public void editAdminTel(int adminId,long adminTel) {
        adminDao.updateAdminTel(adminId,adminTel);
    }

    @Override
    public void editAdminPwd(int adminId, String adminPwd) {
        adminDao.updateAdminPwd(adminId,adminPwd);
    }

    @Override
    public void addAdmin(long adminTel, String adminName, String adminPwd, Date adminRegTime) {
        adminDao.insertAdmin(adminTel,adminName,adminPwd,adminRegTime);
    }

    @Override
    public List<Admin> getAdminByIdOrName(String keyword) {
        return adminDao.selectAdminByIdOrName(keyword);
    }
}
