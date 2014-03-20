package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.service.repository.AttachmentService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.codec.binary.Base64;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.util.List;

@Named
public class AttachmentController {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);

    @Inject
    private AttachmentService attachmentService;

    private Base64 base64codec;

    public AttachmentController() {
        base64codec = new Base64();
    }

    public static AttachmentDTO encodeAttachment(String name, byte[] content) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(name);
        Base64 base64codec = new Base64();
        attachmentDTO.setContent(base64codec.encodeToString(content));
        return attachmentDTO;
    }

    public StreamedContent downloadAttachment(Long id) {
        AttachmentDTO attachmentDTO = attachmentService.getById(id);
        LOG.info(attachmentDTO.toString());
        return new DefaultStreamedContent(new ByteArrayInputStream(base64codec.decode(attachmentDTO.getContent())),
                    "application/force-download",
                    attachmentDTO.getName());

    }

    public List<AttachmentDTO> allAttachment() {
        return attachmentService.getAll();
    }

}
