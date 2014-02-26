package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEmailDAO extends JpaRepository<Email, Long> {
}
