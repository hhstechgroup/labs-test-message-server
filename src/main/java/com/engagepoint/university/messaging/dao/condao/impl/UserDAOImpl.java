package com.engagepoint.university.messaging.dao.condao.impl;

import com.engagepoint.university.messaging.dao.condao.UserDAO;
import com.engagepoint.university.messaging.dao.generic.impl.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.User;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public List<User> getUsersByName(String name) {
        getEntityManager().getTransaction().begin();
        List<User> users = getEntityManager()
                .createNamedQuery(User.GET_ALL_BY_USER_NAME, User.class)
                .setParameter("name", name).getResultList();
        getEntityManager().getTransaction().commit();
        return users;
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        getEntityManager().getTransaction().begin();
        List<User> users = getEntityManager()
                .createNamedQuery(User.GET_ALL_BY_USER_EMAIL, User.class)
                .setParameter("email", email).getResultList();
        getEntityManager().getTransaction().commit();
        return users;
    }

    @Override
    public List<User> getUsersByPhoneNumber(String phoneNumber) {
        getEntityManager().getTransaction().begin();
        List<User> users = getEntityManager()
                .createNamedQuery(User.GET_ALL_BY_USER_PHONE_NUMBER, User.class)
                .setParameter("phoneNumber", phoneNumber).getResultList();
        getEntityManager().getTransaction().commit();
        return users;
    }
}
