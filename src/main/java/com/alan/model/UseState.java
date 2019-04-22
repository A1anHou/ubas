package com.alan.model;

import java.util.Date;

public class UseState {
    private int useStateId;
    private int appId;
    private int userId;
    private Date startTime;
    private Date endTime;

    public int getUseStateId() {
        return useStateId;
    }

    public void setUseStateId(int useStateId) {
        this.useStateId = useStateId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
