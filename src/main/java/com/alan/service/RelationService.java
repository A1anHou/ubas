package com.alan.service;

public interface RelationService {
    Integer getChildNum(int parentId);

    Integer getParentNum(int userId);
}
