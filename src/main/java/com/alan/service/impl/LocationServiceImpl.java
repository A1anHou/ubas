package com.alan.service.impl;

import com.alan.dao.LocationDao;
import com.alan.model.Location;
import com.alan.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationDao locationDao;

    @Override
    public List<Location> getLocationByUserIdAndDate(int userId, Date startTime, Date endTime) {
        return locationDao.selectLocationByUserIdAndDate(userId,startTime,endTime);
    }

    @Override
    public void addLocation(int userId, double longitude, double latitude, Date startTime, Date endTime) {
        locationDao.insertLocation(userId,longitude,latitude,startTime,endTime);
    }

    @Override
    public List<Location> getLocationByUserId(int userId) {
        return locationDao.selectLocationByUserId(userId);
    }
}
