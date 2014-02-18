package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.AttachmentDAO;
import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
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
public class AttachmentService {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentService.class);

    @Inject
    private AttachmentDAO attachmentDAO;

    @Inject
    private EmailDAO emailDAO;

    private BASE64Decoder decoder;

    private BASE64Encoder encoder;

    public AttachmentService() {
        decoder = new BASE64Decoder();
        encoder = new BASE64Encoder();
    }

    public AttachmentDTO encodeAttachment(String name, byte[] content) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(name);
        attachmentDTO.setContent(encoder.encode(content));
        return attachmentDTO;
    }

    public StreamedContent downloadAttachment(Long id) {
        AttachmentDTO attachmentDTO = attachmentDAO.getById(id);
        LOG.info(attachmentDTO.toString());
        try {
            return new DefaultStreamedContent(
                    new ByteArrayInputStream(decoder.decodeBuffer(attachmentDTO.getContent())),
                    "application/force-download",
                    attachmentDTO.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AttachmentDTO> allAttachment() {
        return attachmentDAO.getAll();
    }

}
