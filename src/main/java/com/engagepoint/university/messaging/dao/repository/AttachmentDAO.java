package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.Attachment;

import java.util.List;

public interface AttachmentDAO extends GenericDAO<Attachment> {
    public List<Attachment> getAttachmentsByName(String name);
}
