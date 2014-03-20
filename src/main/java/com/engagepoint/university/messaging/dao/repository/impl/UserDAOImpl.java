package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SpringDataUserDAO;
import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service("userDAO")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataUserDAO springDataUserDAO;

    @Override
    @Transactional
    public User getById(Long id) {
        return springDataUserDAO.findOne(id);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return springDataUserDAO.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        springDataUserDAO.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        springDataUserDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        springDataUserDAO.delete(id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        springDataUserDAO.delete(user);
    }

    @Override
    @Transactional
    public List<User> getUsersByName(String name) {
        List<User> users = entityManager
                .createNamedQuery(User.GET_ALL_BY_USER_NAME, User.class).getResultList();
        return users;
    }

    @Override
    @Transactional
    public List<User> getUsersByEmail(String email) {
        List<User> users = entityManager
                .createNamedQuery(User.GET_ALL_BY_USER_EMAIL, User.class).getResultList();
        return users;
    }

    @Override
    @Transactional
    public List<User> getUsersByPhoneNumber(String phoneNumber) {
        List<User> users = entityManager
                .createNamedQuery(User.GET_ALL_BY_USER_PHONE_NUMBER, User.class)
                .setParameter("phoneNumber", phoneNumber).getResultList();
        return users;
    }
}
