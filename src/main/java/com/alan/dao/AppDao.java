package com.alan.dao;

import com.alan.model.App;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDao {
    List<App> selectAllApp();
}
