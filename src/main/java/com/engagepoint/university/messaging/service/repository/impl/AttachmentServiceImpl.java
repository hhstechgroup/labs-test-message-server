package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.AttachmentDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.entity.Attachment;
import com.engagepoint.university.messaging.service.repository.AttachmentService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

    @Inject
    private AttachmentDAO attachmentDAO;


    @Override
    public List<AttachmentDTO> getAttachmentsByName(String name) {
        List<Attachment> attachmentList = attachmentDAO.getAttachmentsByName(name);
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        for (Attachment attachment:attachmentList) {
            attachmentDTOs.add(Converter.convert(attachment));
        }
        return attachmentDTOs;
    }

    @Override
    public AttachmentDTO getById(Long id) {
        Attachment attachment = attachmentDAO.getById(id);
        return Converter.convert(attachment);
    }

    @Override
    public List<AttachmentDTO> getAll() {
        List<Attachment> attachmentList = attachmentDAO.getAll();
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        for (Attachment attachment:attachmentList) {
            attachmentDTOs.add(Converter.convert(attachment));
        }
        return attachmentDTOs;
    }

    @Override
    public void save(AttachmentDTO attachmentDTO) {
        Attachment attachment = Converter.convert(attachmentDTO);
        attachmentDAO.save(attachment);
    }

    @Override
    public void update(AttachmentDTO attachmentDTO) {
        Attachment attachment = Converter.convert(attachmentDTO);
        attachmentDAO.update(attachment);
    }

    @Override
    public void delete(Long id) {
        attachmentDAO.delete(id);
    }

    @Override
    public void delete(AttachmentDTO attachmentDTO) {
        Attachment attachment = Converter.convert(attachmentDTO);
        attachmentDAO.delete(attachment);
    }
}
