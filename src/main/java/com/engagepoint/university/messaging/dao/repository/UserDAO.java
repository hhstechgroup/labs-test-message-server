package com.engagepoint.university.messaging.dao.repository;
import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {
    public List<User> getUsersByName(String name);
    public List<User> getUsersByEmail(String email);
    public List<User> getUsersByPhoneNumber(String phoneNumber);
}
