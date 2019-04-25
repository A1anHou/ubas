package com.alan.dao;

import com.alan.model.Parent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParentDao {
    List<Parent> selectAllParent();

    List<Parent> selectParentByIdOrName(String keyword);

    Parent selectParentByTel(long parentTel);

    void insertParent(@Param("parentTel")long parentTel, @Param("parentName")String parentName, @Param("parentPwd")String parentPwd, @Param("parentRegTime")Date parentRegTime);

    Parent selectParentById(int parentId);

    void updateParentTel(@Param("parentId")int parentId, @Param("parentTel")long parentTel);

    void updateParentPwd(@Param("parentId")int parentId, @Param("parentPwd")String parentPwd);
}
