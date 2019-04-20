package com.alan.service.impl;

import com.alan.dao.AppDao;
import com.alan.model.App;
import com.alan.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppServiceImpl implements AppService {
    @Autowired
    private AppDao appDao;

    @Override
    public List<App> getAllApp() {
        return appDao.selectAllApp();
    }

}
