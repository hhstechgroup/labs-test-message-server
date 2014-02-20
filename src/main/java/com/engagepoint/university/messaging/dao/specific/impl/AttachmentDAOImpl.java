package com.engagepoint.university.messaging.dao.specific.impl;

import com.engagepoint.university.messaging.dao.specific.AttachmentDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.entities.Attachment;
import com.engagepoint.university.messaging.util.Converter;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AttachmentDAOImpl implements AttachmentDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentDAOImpl.class);

    @Override
    public AttachmentDTO getById(Long id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Attachment attachment = EntityManagerUtil.getEntityManager().find(Attachment.class, id);
        AttachmentDTO attachmentDTO = Converter.convert(attachment);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return attachmentDTO;
    }

    @Override
    public List<AttachmentDTO> getAll() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Attachment> attachments = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Attachment.GET_ALL_ATTACHMENTS, Attachment.class).getResultList();
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        Iterator<Attachment> attachmentIterator = attachments.iterator();
        while (attachmentIterator.hasNext()) {
            attachmentDTOs.add(Converter.convert(attachmentIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return attachmentDTOs;
    }

    @Override
    public void save(AttachmentDTO attachmentDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Attachment attachment = Converter.convert(attachmentDTO);
        if (!EntityManagerUtil.getEntityManager().contains(attachment)) {
            EntityManagerUtil.getEntityManager().merge(attachment);
        } else {
            EntityManagerUtil.getEntityManager().persist(attachment);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(AttachmentDTO attachmentDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Attachment attachment = Converter.convert(attachmentDTO);
        EntityManagerUtil.getEntityManager().merge(attachment);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Attachment attachment = EntityManagerUtil.getEntityManager().find(Attachment.class, id);
        if (attachment != null) {
            EntityManagerUtil.getEntityManager().detach(attachment);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(AttachmentDTO attachmentDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Attachment attachment = Converter.convert(attachmentDTO);
        EntityManagerUtil.getEntityManager().detach(attachment);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public List<AttachmentDTO> getAttachmentsByName(String name) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Attachment> attachments = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Attachment.GET_ALL_BY_NAME, Attachment.class)
                .setParameter("name", name).getResultList();
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        Iterator<Attachment> attachmentIterator = attachments.iterator();
        while (attachmentIterator.hasNext()) {
            attachmentDTOs.add(Converter.convert(attachmentIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return attachmentDTOs;
    }
}
