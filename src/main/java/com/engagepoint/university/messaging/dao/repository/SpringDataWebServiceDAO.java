package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entity.WebService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("springDataWebServiceDAO")
public interface SpringDataWebServiceDAO extends JpaRepository<WebService, Long> {
}
