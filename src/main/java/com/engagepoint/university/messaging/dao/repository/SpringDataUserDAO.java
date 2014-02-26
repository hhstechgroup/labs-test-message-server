package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("springDataUserDAO")
public interface SpringDataUserDAO extends JpaRepository<User, Long> {
}
