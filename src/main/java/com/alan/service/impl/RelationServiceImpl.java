package com.alan.service.impl;

import com.alan.dao.RelationDao;

import com.alan.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RelationDao relationDao;

    @Override
    public Integer getChildNum(int parentId) {
        return relationDao.queryChildNum(parentId);
    }

    @Override
    public Integer getParentNum(int userId) {
        return relationDao.queryParentNum(userId);
    }
}
