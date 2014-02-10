package com.engagepoint.university.messaging.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Alexey on 2/10/14.
 */
public class EntityManagerUtil {

    private static final EntityManager entityManager = buildEntityManager();

    private static EntityManager buildEntityManager() {
        return Persistence.createEntityManagerFactory("messaging").createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
