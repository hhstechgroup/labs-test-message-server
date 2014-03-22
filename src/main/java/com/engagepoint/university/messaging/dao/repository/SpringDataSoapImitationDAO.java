package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entity.ReqResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataSoapImitationDAO extends JpaRepository<ReqResp, Long> {
}
