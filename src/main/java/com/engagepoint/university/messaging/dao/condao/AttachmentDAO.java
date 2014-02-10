package com.engagepoint.university.messaging.dao.condao;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.Attachment;

import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public interface AttachmentDAO extends GenericDAO<Attachment> {
    public List<Attachment> getAttachmentsByMimetype(String mimetype);
    public List<Attachment> getAttachmentsByName(String name);
}
