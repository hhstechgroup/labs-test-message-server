package com.engagepoint.university.messaging.dao;

import com.engagepoint.university.messaging.entity.base.BaseEntity;

import java.util.List;

public interface GenericDAO<EntityType extends BaseEntity> {

    public EntityType getById(Long id);
    public List<EntityType> getAll();
    public void save(EntityType EntityType);
    public void update(EntityType EntityType);
    public void delete(Long id);
    public void delete(EntityType EntityType);
}
