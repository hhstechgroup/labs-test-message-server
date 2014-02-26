package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;

import java.util.List;

public interface AttachmentDAO extends GenericDAO<AttachmentDTO> {
    public List<AttachmentDTO> getAttachmentsByName(String name);
}
