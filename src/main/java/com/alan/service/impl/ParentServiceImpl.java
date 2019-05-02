package com.alan.service.impl;

import com.alan.dao.EditParentDao;
import com.alan.dao.ParentDao;
import com.alan.model.Parent;
import com.alan.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ParentServiceImpl implements ParentService {
    @Autowired
    private ParentDao parentDao;
    @Autowired
    private EditParentDao editParentDao;

    @Override
    public List<Parent> getAllParent() {
        return parentDao.selectAllParent();
    }

    @Override
    public List<Parent> getParentByIdOrName(String keyword) {
        return parentDao.selectParentByIdOrName(keyword);
    }

    @Override
    public Parent getParentByTel(long parentTel) {
        return parentDao.selectParentByTel(parentTel);
    }

    @Override
    public void addParent(long parentTel, String parentName, String parentPwd, Date parentRegTime) {
        parentDao.insertParent(parentTel,parentName,parentPwd,parentRegTime);
    }

    @Override
    public Parent getParentById(int parentId) {
        return parentDao.selectParentById(parentId);
    }

    @Override
    public void editParentTel(int parentId, long parentTel) {
        parentDao.updateParentTel(parentId,parentTel);
    }

    @Override
    public void editParentPwd(int parentId, String parentPwd) {
        parentDao.updateParentPwd(parentId,parentPwd);
    }

    @Override
    public void recordEdit(int parentId, String parentAttr, String parentOld, String parentNew, Date parentChangeTime) {
        editParentDao.insertEditRecord(parentId,parentAttr,parentOld,parentNew,parentChangeTime);
    }
}
