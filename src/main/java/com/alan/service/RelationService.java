package com.alan.service;

import com.alan.model.Relation;

import java.util.Date;
import java.util.List;

public interface RelationService {
    Integer getChildNum(int parentId);

    Integer getParentNum(int userId);

    List<Relation> getRelationByParentId(int parentId);

    Relation getRelationByParentIdAndUserId(int parentId, int userId);

    void addRelation(int parentId, int userId, String relationship, Date relateTime);

    void delRelation(int relationId);

    List<Relation> getRelationByUserId(int userId);

    void recordEdit(int relationId,int parentId, int userId,String relationAttr, String relationOld, String relationNew, Date relationChangeTime);

    Relation getRelationById(int relationId);
}
