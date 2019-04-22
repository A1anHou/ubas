package com.alan.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RelationDao {
    Integer queryChildNum(int parentId);

    Integer queryParentNum(int userId);
}
