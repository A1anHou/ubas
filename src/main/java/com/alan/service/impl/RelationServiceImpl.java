package com.alan.service.impl;

import com.alan.dao.RelationDao;

import com.alan.model.Relation;
import com.alan.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RelationDao relationDao;

    @Override
    public Integer getChildNum(int parentId) {
        return relationDao.selectChildNum(parentId);
    }

    @Override
    public Integer getParentNum(int userId) {
        return relationDao.selectParentNum(userId);
    }

    @Override
    public List<Relation> getRelationByParentId(int parentId) {
        return relationDao.selectRelationByParentId(parentId);
    }

    @Override
    public Relation getRelationByParentIdAndUserId(int parentId, int userId) {
        return relationDao.selectRelationByParentIdAndUserId(parentId,userId);
    }

    @Override
    public void addRelation(int parentId, int userId, String relationship, Date relateTime) {
        relationDao.insertRelation(parentId,userId,relationship,relateTime);
    }

    @Override
    public void delRelation(int relationId) {
        relationDao.deleteRelation(relationId);
    }

    @Override
    public List<Relation> getRelationByUserId(int userId) {
        return relationDao.selectRelationByUserId(userId);
    }
}
