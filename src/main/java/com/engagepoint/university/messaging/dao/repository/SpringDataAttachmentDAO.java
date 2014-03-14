package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("springDataAttachmentDAO")
public interface SpringDataAttachmentDAO extends JpaRepository<Attachment, Long> {
}
