package com.alan.model;

import java.util.Date;

public class Parent {

    private int parentId;
    private long parentTel;
    private String parentName;
    private String parentPwd;
    private Date parentRegTime;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public long getParentTel() {
        return parentTel;
    }

    public void setParentTel(long parentTel) {
        this.parentTel = parentTel;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPwd() {
        return parentPwd;
    }

    public void setParentPwd(String parentPwd) {
        this.parentPwd = parentPwd;
    }

    public Date getParentRegTime() {
        return parentRegTime;
    }

    public void setParentRegTime(Date parentRegTime) {
        this.parentRegTime = parentRegTime;
    }

}
