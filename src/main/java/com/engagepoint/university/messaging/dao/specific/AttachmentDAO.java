package com.engagepoint.university.messaging.dao.specific;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;

import java.util.List;

public interface AttachmentDAO extends GenericDAO<AttachmentDTO> {
    public List<AttachmentDTO> getAttachmentsByMimetype(String mimetype);
    public List<AttachmentDTO> getAttachmentsByName(String name);
}
