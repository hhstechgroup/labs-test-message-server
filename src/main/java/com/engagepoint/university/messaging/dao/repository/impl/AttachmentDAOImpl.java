package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.AttachmentDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataAttachmentDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.entities.Attachment;
import com.engagepoint.university.messaging.util.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("attachmentDAO")
public class AttachmentDAOImpl implements AttachmentDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataAttachmentDAO springDataAttachmentDAO;

    @Override
    @Transactional
    public AttachmentDTO getById(Long id) {
        Attachment attachment = springDataAttachmentDAO.findOne(id);
        AttachmentDTO attachmentDTO = Converter.convert(attachment);
        return attachmentDTO;
    }

    @Override
    @Transactional
    public List<AttachmentDTO> getAll() {
        List<Attachment> attachments = springDataAttachmentDAO.findAll();
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        Iterator<Attachment> attachmentIterator = attachments.iterator();
        while (attachmentIterator.hasNext()) {
            attachmentDTOs.add(Converter.convert(attachmentIterator.next()));
        }
        return attachmentDTOs;
    }

    @Override
    @Transactional
    public void save(AttachmentDTO attachmentDTO) {
        Attachment attachment = Converter.convert(attachmentDTO);
        springDataAttachmentDAO.save(attachment);
    }

    @Override
    @Transactional
    public void update(AttachmentDTO attachmentDTO) {
        Attachment attachment = Converter.convert(attachmentDTO);
        springDataAttachmentDAO.save(attachment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        springDataAttachmentDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(AttachmentDTO attachmentDTO) {
        Attachment attachment = Converter.convert(attachmentDTO);
        springDataAttachmentDAO.delete(attachment);
    }

    @Override
    @Transactional
    public List<AttachmentDTO> getAttachmentsByName(String name) {
        List<Attachment> attachments = entityManager
                .createNamedQuery(Attachment.GET_ALL_BY_NAME, Attachment.class)
                .setParameter("name", name).getResultList();
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        Iterator<Attachment> attachmentIterator = attachments.iterator();
        while (attachmentIterator.hasNext()) {
            attachmentDTOs.add(Converter.convert(attachmentIterator.next()));
        }
        return attachmentDTOs;
    }
}
