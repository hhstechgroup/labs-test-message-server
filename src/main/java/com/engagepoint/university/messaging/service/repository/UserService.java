package com.engagepoint.university.messaging.service.repository;


import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.service.GenericService;

import java.util.List;

public interface UserService extends GenericService<UserDTO> {
    public List<UserDTO> getUsersByName(String name);
    public List<UserDTO> getUsersByEmail(String email);
    public List<UserDTO> getUsersByPhoneNumber(String phoneNumber);
}
