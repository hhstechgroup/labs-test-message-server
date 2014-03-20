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
        List<User> userList = userDAO.getUsersByName(name);
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (User user : userList) {
            userDTOs.add(Converter.convert(user));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByEmail(String email) {
        List<User> userList = userDAO.getUsersByEmail(email);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : userList) {
            userDTOs.add(Converter.convert(user));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByPhoneNumber(String phoneNumber) {
        List<User> userList = userDAO.getUsersByPhoneNumber(phoneNumber);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : userList) {
            userDTOs.add(Converter.convert(user));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userDAO.getById(id);
        return Converter.convert(user);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : userList) {
            userDTOs.add(Converter.convert(user));
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
