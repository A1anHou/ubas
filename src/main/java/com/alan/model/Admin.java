package com.alan.model;

import java.util.Date;

public class Admin {

    private int adminId;
    private long adminTel;
    private String adminName;
    private String adminPwd;
    private Date adminRegTime;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public long getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(long adminTel) {
        this.adminTel = adminTel;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public Date getAdminRegTime() {
        return adminRegTime;
    }

    public void setAdminRegTime(Date adminRegTime) {
        this.adminRegTime = adminRegTime;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminTel=" + adminTel +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminRegTime=" + adminRegTime +
                '}';
    }
}
