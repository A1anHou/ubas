package com.alan.service;

import com.alan.model.Admin;

import java.util.List;

public interface AdminService {
    Admin getAdminByTel(long adminTel);


    List<Admin> getAllAdmin();

    Admin getAdminById(int id);
}
