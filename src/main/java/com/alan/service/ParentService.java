package com.alan.service;

import com.alan.model.Parent;

import java.util.List;

public interface ParentService {
    List<Parent> getAllParent();

    List<Parent> getParentByIdOrName(String keyword);
}
