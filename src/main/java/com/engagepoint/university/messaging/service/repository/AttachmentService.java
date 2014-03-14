package com.engagepoint.university.messaging.service.repository;


import com.engagepoint.university.messaging.service.GenericService;
import com.engagepoint.university.messaging.dto.AttachmentDTO;

import java.util.List;

public interface AttachmentService extends GenericService<AttachmentDTO> {
    public List<AttachmentDTO> getAttachmentsByName(String name);
}
