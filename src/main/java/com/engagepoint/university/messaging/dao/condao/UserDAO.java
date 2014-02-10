package com.engagepoint.university.messaging.dao.condao;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.User;

import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public interface UserDAO extends GenericDAO<User> {
    public List<User> getUsersByName(String name);
    public List<User> getUsersByEmail(String email);
    public List<User> getUsersByPhoneNumber(String phoneNumber);
}
