package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.AttachmentDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataAttachmentDAO;
import com.engagepoint.university.messaging.entity.Attachment;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service("attachmentDAO")
public class AttachmentDAOImpl implements AttachmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataAttachmentDAO springDataAttachmentDAO;

    @Override
    @Transactional
    public Attachment getById(Long id) {
        return springDataAttachmentDAO.findOne(id);
    }

    @Override
    @Transactional
    public List<Attachment> getAll() {
        return springDataAttachmentDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Attachment attachment) {
        springDataAttachmentDAO.save(attachment);
    }

    @Override
    @Transactional
    public void update(Attachment attachment) {
        springDataAttachmentDAO.save(attachment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        springDataAttachmentDAO.delete(id);
    }

    @Override
    @Transactional
    public void delete(Attachment attachment) {
        springDataAttachmentDAO.delete(attachment);
    }

    @Override
    @Transactional
    public List<Attachment> getAttachmentsByName(String name) {
        List<Attachment> attachments = entityManager
                .createNamedQuery(Attachment.GET_ALL_BY_NAME, Attachment.class)
                .setParameter("name", name).getResultList();
        return attachments;
    }
}
