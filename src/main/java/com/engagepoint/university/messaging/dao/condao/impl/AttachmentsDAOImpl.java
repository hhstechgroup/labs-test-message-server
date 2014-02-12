package com.engagepoint.university.messaging.dao.condao.impl;
import com.engagepoint.university.messaging.dao.condao.AttachmentDAO;
import com.engagepoint.university.messaging.dao.generic.impl.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.Attachment;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class AttachmentsDAOImpl extends GenericDAOImpl<Attachment> implements AttachmentDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentsDAOImpl.class);

    public AttachmentsDAOImpl() {
        super(Attachment.class);
    }

    @Override
    public List<Attachment> getAttachmentsByMimetype(String mimetype) {
        getEntityManager().getTransaction().begin();
        List<Attachment> attachments = getEntityManager()
                .createNamedQuery(Attachment.GET_ALL_BY_MIME_TYPE, Attachment.class)
                .setParameter("mimeType", mimetype).getResultList();
        getEntityManager().getTransaction().commit();
        return attachments;
    }

    @Override
    public List<Attachment> getAttachmentsByName(String name) {
        getEntityManager().getTransaction().begin();
        List<Attachment> attachments = getEntityManager()
                .createNamedQuery(Attachment.GET_ALL_BY_NAME, Attachment.class)
                .setParameter("name", name).getResultList();
        getEntityManager().getTransaction().commit();
        return attachments;
    }


}
