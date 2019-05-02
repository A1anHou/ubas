package com.alan.service;

import com.alan.model.Admin;

import java.util.Date;
import java.util.List;

public interface AdminService {
    Admin getAdminByTel(long adminTel);


    List<Admin> getAllAdmin();

    Admin getAdminById(int id);

    void delAdmin(int adminId);

    void editAdminTel(int adminId, long adminTel);

    void editAdminPwd(int adminId, String adminPwd);

    void addAdmin(long adminTel, String adminName, String adminPwd, Date adminRegTime);

    List<Admin> getAdminByIdOrName(String keyword);

    void recordEdit(int adminId, String adminAttr, String adminOld, String adminNew, int optAdminId, Date adminChangeTime);
}
