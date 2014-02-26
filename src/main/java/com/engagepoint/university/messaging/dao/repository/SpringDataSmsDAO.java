package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entities.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("springDataSmsDAO")
public interface SpringDataSmsDAO extends JpaRepository<Sms, Long> {
}
