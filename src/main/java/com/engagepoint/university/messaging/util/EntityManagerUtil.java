package com.engagepoint.university.messaging.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private EntityManager entityManager;

    public EntityManagerUtil(){
        entityManager = Persistence.createEntityManagerFactory("messaging").createEntityManager();
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
}
