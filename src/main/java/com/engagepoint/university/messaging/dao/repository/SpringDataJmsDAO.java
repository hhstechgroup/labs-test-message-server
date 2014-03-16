package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entity.Jms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("springDataJmsDAO")
public interface SpringDataJmsDAO extends JpaRepository<Jms, Long> {
}
