package com.alan.service;

import com.alan.model.Location;

import java.util.Date;
import java.util.List;

public interface LocationService {
    List<Location> getLocationByUserIdAndDate(int userId, Date startTime, Date endTime);
}
