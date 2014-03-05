package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SpringDataUserDAO;
import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.entities.User;
import com.engagepoint.university.messaging.util.Converter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataUserDAO springDataUserDAO;

    @Override
    @Transactional
    public UserDTO getById(Long id) {
        User user = springDataUserDAO.findOne(id);
        UserDTO userDTO = Converter.convert(user);
        return userDTO;
    }

    @Override
    @Transactional
    public List<UserDTO> getAll() {
        List<User> users = springDataUserDAO.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        User user = Converter.convert(userDTO);
        springDataUserDAO.save(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = Converter.convert(userDTO);
        springDataUserDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        springDataUserDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(UserDTO userDTO) {
        User user = Converter.convert(userDTO);
        springDataUserDAO.delete(user);
    }

    @Override
    @Transactional
    public List<UserDTO> getUsersByName(String name) {
        List<User> users = entityManager
                .createNamedQuery(User.GET_ALL_BY_USER_NAME, User.class).getResultList();
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    @Transactional
    public List<UserDTO> getUsersByEmail(String email) {
        List<User> users = entityManager
                .createNamedQuery(User.GET_ALL_BY_USER_EMAIL, User.class).getResultList();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    @Transactional
    public List<UserDTO> getUsersByPhoneNumber(String phoneNumber) {
        List<User> users = entityManager
                .createNamedQuery(User.GET_ALL_BY_USER_PHONE_NUMBER, User.class)
                .setParameter("phoneNumber", phoneNumber).getResultList();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }
}
