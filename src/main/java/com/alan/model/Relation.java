package com.alan.model;

import java.util.Date;

public class Relation {
    private int relationId;
    private int userId;
    private int parentId;
    private String relationship;
    private Date relateTime;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Date getRelateTime() {
        return relateTime;
    }

    public void setRelateTime(Date relateTime) {
        this.relateTime = relateTime;
    }
}
