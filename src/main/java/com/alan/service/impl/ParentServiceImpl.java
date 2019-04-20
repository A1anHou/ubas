package com.alan.service.impl;

import com.alan.dao.ParentDao;
import com.alan.model.Parent;
import com.alan.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ParentServiceImpl implements ParentService {
    @Autowired
    private ParentDao parentDao;

    @Override
    public List<Parent> getAllParent() {
        return parentDao.selectAllParent();
    }
}
