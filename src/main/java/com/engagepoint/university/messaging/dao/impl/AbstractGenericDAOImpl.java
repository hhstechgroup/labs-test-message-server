package com.engagepoint.university.messaging.dao.impl;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public abstract class AbstractGenericDAOImpl<T> implements GenericDAO<T>{

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    private Class<T> type;

    protected AbstractGenericDAOImpl() {
        EntityManagerUtil emu = new EntityManagerUtil();
        this.em = emu.getEntityManager();
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T getById(int id) {
        em.getTransaction().begin();
        T t = em.find(type, id);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public List<T> getAll() {
        em.getTransaction().begin();
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        final Query query = this.em.createQuery(queryString.toString(), type);
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void save(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(this.em.getReference(type, id));
        em.getTransaction().commit();
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }
}
