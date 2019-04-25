package com.alan.service;

import com.alan.model.UseState;

import java.util.Date;
import java.util.List;

public interface UseStateService {
    List<UseState> getUseStateByUserIdAndDate(int userId, Date startTime, Date endTime);
}
