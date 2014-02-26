package com.engagepoint.university.messaging.dao.repository;
import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.UserDTO;

import java.util.List;

public interface UserDAO extends GenericDAO<UserDTO> {
    public List<UserDTO> getUsersByName(String name);
    public List<UserDTO> getUsersByEmail(String email);
    public List<UserDTO> getUsersByPhoneNumber(String phoneNumber);
}
