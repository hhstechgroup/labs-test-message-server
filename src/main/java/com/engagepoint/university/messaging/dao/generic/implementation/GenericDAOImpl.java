package com.engagepoint.university.messaging.dao.generic.implementation;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.baseentity.Base;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public abstract class GenericDAOImpl<EntityType extends Base> implements GenericDAO<EntityType> {

    private Class<EntityType> entityClass;

    public GenericDAOImpl(Class<EntityType> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

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
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        EntityManagerUtil.getEntityManager().find(entityClass, id);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(EntityType entityType) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        EntityManagerUtil.getEntityManager().remove(EntityManagerUtil.getEntityManager()
                .find(entityClass, entityType));
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }
}
