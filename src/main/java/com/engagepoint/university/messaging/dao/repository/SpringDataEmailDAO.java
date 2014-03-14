package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("springDataEmailDAO")
public interface SpringDataEmailDAO extends JpaRepository<Email, Long> {
}
