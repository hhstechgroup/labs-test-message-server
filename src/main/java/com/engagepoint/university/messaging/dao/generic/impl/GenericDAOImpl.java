package com.engagepoint.university.messaging.dao.generic.impl;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.base.Base;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class GenericDAOImpl<EntityType extends Base> implements GenericDAO<EntityType> {

    private Class<EntityType> entityClass;

    public GenericDAOImpl(Class<EntityType> entityClass) {
        this.entityClass = entityClass;
    }

    public GenericDAOImpl() {
    }

    public EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager();
    }

    @Override
    public EntityType getById(Integer id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<EntityType> getAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public void save(EntityType entityType) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entityType);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(EntityType entityType) {
        getEntityManager().merge(entityType);
    }

    @Override
    public void delete(Integer id) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(getEntityManager().find(entityClass, id));
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(EntityType entityType) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(getEntityManager()
                .find(entityClass, entityType));
        getEntityManager().getTransaction().commit();
    }
}
