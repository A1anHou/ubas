package com.alan.dao;

import com.alan.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdminDao {
    Admin selectAdminByTel(@Param("adminTel") long adminTel);


    List<Admin> selectAllAdmin();

    Admin selectAdminById(int id);

    void deleteAdmin(int adminId);

    void updateAdminTel(@Param("adminId")int adminId, @Param("adminTel")long adminTel);

    void updateAdminPwd(@Param("adminId")int adminId, @Param("adminPwd")String adminPwd);

    void insertAdmin(@Param("adminTel")long adminTel, @Param("adminName")String adminName, @Param("adminPwd")String adminPwd, @Param("adminRegTime")Date adminRegTime);

    List<Admin> selectAdminByIdOrName(String keyword);
}
