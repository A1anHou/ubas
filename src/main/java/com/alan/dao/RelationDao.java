package com.alan.dao;

import com.alan.model.Relation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RelationDao {
    Integer selectChildNum(int parentId);

    Integer selectParentNum(int userId);

    List<Relation> selectRelationByParentId(int parentId);

    Relation selectRelationByParentIdAndUserId(@Param("parentId")int parentId, @Param("userId")int userId);

    void insertRelation(@Param("parentId")int parentId, @Param("userId")int userId, @Param("relationship")String relationship, @Param("relateTime")Date relateTime);

    void deleteRelation(int relationId);

    List<Relation> selectRelationByUserId(int userId);
}
