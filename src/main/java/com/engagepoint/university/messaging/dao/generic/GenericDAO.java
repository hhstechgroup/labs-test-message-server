package com.engagepoint.university.messaging.dao.generic;

import com.engagepoint.university.messaging.entities.base.Base;

import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public interface GenericDAO<EntityType extends Base> {

    public EntityType getById(Integer id);
    public List<EntityType> getAll();
    public void save(EntityType entityType);
    public void update(EntityType entityType);
    public void delete(Integer id);
    public void delete(EntityType entityType);
}
