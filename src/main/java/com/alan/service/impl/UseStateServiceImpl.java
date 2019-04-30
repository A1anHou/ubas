package com.alan.service.impl;

import com.alan.dao.UseStateDao;
import com.alan.model.UseState;
import com.alan.service.UseStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UseStateServiceImpl implements UseStateService {
    @Autowired
    UseStateDao useStateDao;

    @Override
    public List<UseState> getUseStateByUserIdAndDate(int userId, Date startTime, Date endTime) {
        return useStateDao.selectUseStateByUserIdAndDate(userId,startTime,endTime);
    }

    @Override
    public List<UseState> getUseStateByUserId(int userId) {
        return useStateDao.selectUseStateByUserId(userId);
    }

    @Override
    public void addUseState(int userId, int appId, Date startTime, Date endTime) {
        useStateDao.insertUseState(userId,appId,startTime,endTime);
    }
}
