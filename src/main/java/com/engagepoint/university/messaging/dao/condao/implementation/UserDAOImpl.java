package com.engagepoint.university.messaging.dao.condao.implementation;

import com.engagepoint.university.messaging.dao.condao.UserDAO;
import com.engagepoint.university.messaging.dao.generic.implementation.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.User;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
@RequestScoped
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager();
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
