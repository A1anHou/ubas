package com.alan.service.impl;

import com.alan.dao.UnlockDao;
import com.alan.model.Unlock;
import com.alan.service.UnlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UnlockServiceImpl implements UnlockService {
    @Autowired
    UnlockDao unlockDao;

    @Override
    public List<Unlock> getUnlockByUserIdAndDate(int userId, Date startTime, Date endTime) {
        return unlockDao.selectUnlockByUserIdAndDate(userId,startTime,endTime);
    }

    @Override
    public void addUnlock(int userId, Date unlockTime) {
        unlockDao.insertUnlock(userId,unlockTime);
    }

    @Override
    public List<Unlock> getLocationByUserId(int userId) {
        return unlockDao.selectUnlockByUserId(userId);
    }

    @Override
    public Unlock getUnlockByUnlockTime(Date unlockTime) {
        return unlockDao.selectUnlockByUnlockTime(unlockTime);
    }
}
