package com.engagepoint.university.messaging.service.repository.impl;


import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.entity.User;
import com.engagepoint.university.messaging.service.repository.UserService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Inject
    UserDAO userDAO;


    @Override
    public List<UserDTO> getUsersByName(String name) {
        List<User> users = userDAO.getUsersByName(name);
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByEmail(String email) {
        List<User> users = userDAO.getUsersByEmail(email);
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByPhoneNumber(String phoneNumber) {
        List<User> users = userDAO.getUsersByPhoneNumber(phoneNumber);
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userDAO.getById(id);
        UserDTO userDTO = Converter.convert(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userDAO.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            userDTOs.add(Converter.convert(userIterator.next()));
        }
        return userDTOs;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = Converter.convert(userDTO);
        userDAO.save(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = Converter.convert(userDTO);
        userDAO.save(user);
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public void delete(UserDTO userDTO) {
        User user = Converter.convert(userDTO);
        userDAO.delete(user);
    }
}
