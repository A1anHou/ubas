package com.alan.service;

import com.alan.model.Unlock;

import java.util.Date;
import java.util.List;

public interface UnlockService {
    List<Unlock> getUnlockByUserIdAndDate(int userId, Date startTime, Date endTime);
}