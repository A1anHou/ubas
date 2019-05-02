package com.alan.service;

import com.alan.model.Parent;

import java.util.Date;
import java.util.List;

public interface ParentService {
    List<Parent> getAllParent();

    List<Parent> getParentByIdOrName(String keyword);

    Parent getParentByTel(long parseLong);

    void addParent(long parentTel, String parentName, String parentPwd, Date parentRegTime);

    Parent getParentById(int parentId);

    void editParentTel(int parentId, long parentTel);

    void editParentPwd(int parentId, String parentPwd);

    void recordEdit(int parentId, String parent_pwd, String oldPwd, String parentPwd, Date date);
}
