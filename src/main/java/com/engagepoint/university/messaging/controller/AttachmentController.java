package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.service.repository.AttachmentService;
import com.engagepoint.university.messaging.service.repository.EmailService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Named
public class AttachmentController {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);

    @Inject
    private AttachmentService attachmentService;

    private static BASE64Decoder decoder;

    private static BASE64Encoder encoder;

    static {
        decoder = new BASE64Decoder();
        encoder = new BASE64Encoder();
    }

    public AttachmentController() {
    }

    public static AttachmentDTO encodeAttachment(String name, byte[] content) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(name);
        attachmentDTO.setContent(encoder.encode(content));
        return attachmentDTO;
    }

    public StreamedContent downloadAttachment(Long id) {
        AttachmentDTO attachmentDTO = attachmentService.getById(id);
        LOG.info(attachmentDTO.toString());
        try {
            return new DefaultStreamedContent(
                    new ByteArrayInputStream(decoder.decodeBuffer(attachmentDTO.getContent())),
                    "application/force-download",
                    attachmentDTO.getName());
        } catch (IOException e) {
            LOG.info(e.getMessage(), e);
            return null;
        }
    }

    public List<AttachmentDTO> allAttachment() {
        return attachmentService.getAll();
    }

}
