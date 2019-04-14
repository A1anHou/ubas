package com.alan.dao;

import com.alan.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    Admin selectAdminByTel(@Param("adminTel") long adminTel);
}
