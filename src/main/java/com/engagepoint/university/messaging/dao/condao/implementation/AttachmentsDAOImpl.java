package com.engagepoint.university.messaging.dao.condao.implementation;
import com.engagepoint.university.messaging.dao.condao.AttachmentDAO;
import com.engagepoint.university.messaging.dao.generic.implementation.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.Attachment;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
@RequestScoped
public class AttachmentsDAOImpl extends GenericDAOImpl<Attachment> implements AttachmentDAO {

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

    @Override
    protected EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager();
    }
}
