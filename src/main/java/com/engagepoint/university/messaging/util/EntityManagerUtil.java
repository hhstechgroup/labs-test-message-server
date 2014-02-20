package com.engagepoint.university.messaging.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static final EntityManager entityManager = Persistence.
            createEntityManagerFactory("messaging").createEntityManager();

    private EntityManagerUtil() {
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
