package com.alan.dao;

import com.alan.model.Parent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentDao {
    List<Parent> selectAllParent();
}
