package com.engagepoint.university.messaging.dao.specific.impl;

import com.engagepoint.university.messaging.dao.specific.UserDAO;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.entities.User;
import com.engagepoint.university.messaging.util.Converter;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public UserDTO getById(Long id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        User user = EntityManagerUtil.getEntityManager().find(User.class, id);
        UserDTO userDTO = Converter.convert(user);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<User> users = EntityManagerUtil.getEntityManager()
                .createNamedQuery(User.GET_ALL_USERS, User.class).getResultList();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return userDTOs;
    }

    @Override
    public void save(UserDTO userDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        User user = Converter.convert(userDTO);
        if (!EntityManagerUtil.getEntityManager().contains(user)) {
            EntityManagerUtil.getEntityManager().merge(user);
        } else {
            EntityManagerUtil.getEntityManager().persist(user);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(UserDTO userDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        User user = Converter.convert(userDTO);
        EntityManagerUtil.getEntityManager().merge(user);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        User user = EntityManagerUtil.getEntityManager().find(User.class, id);
        if (user != null) {
            EntityManagerUtil.getEntityManager().detach(user);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(UserDTO userDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        User user = Converter.convert(userDTO);
        EntityManagerUtil.getEntityManager().detach(user);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public List<UserDTO> getUsersByName(String name) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<User> users = EntityManagerUtil.getEntityManager()
                .createNamedQuery(User.GET_ALL_BY_USER_NAME, User.class).getResultList();
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByEmail(String email) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<User> users = EntityManagerUtil.getEntityManager()
                .createNamedQuery(User.GET_ALL_BY_USER_EMAIL, User.class).getResultList();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByPhoneNumber(String phoneNumber) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<User> users = EntityManagerUtil.getEntityManager()
                .createNamedQuery(User.GET_ALL_BY_USER_PHONE_NUMBER, User.class)
                .setParameter("phoneNumber", phoneNumber).getResultList();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return userDTOs;
    }
}
