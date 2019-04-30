package com.alan.service;

import com.alan.model.UseState;

import java.util.Date;
import java.util.List;

public interface UseStateService {
    List<UseState> getUseStateByUserIdAndDate(int userId, Date startTime, Date endTime);

    void addUseState(int userId, int appId, Date startTime, Date endTime);

    List<UseState> getUseStateByUserId(int userId);
}
