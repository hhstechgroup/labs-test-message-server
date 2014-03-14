package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SpringDataUserDAO;
import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.entity.User;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
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
        User user = springDataUserDAO.findOne(id);
        return user;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        List<User> users = springDataUserDAO.findAll();
        return users;
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
