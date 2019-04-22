package com.alan.model;

import java.util.Date;

public class Unlock {
    private int unlockId;
    private int userId;
    private Date unlockTime;

    public int getUnlockId() {
        return unlockId;
    }

    public void setUnlockId(int unlockId) {
        this.unlockId = unlockId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }
}
