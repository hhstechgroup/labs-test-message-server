package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.impl.AttachmentDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;

@Named
public class AttachmentService {

    @Inject
    private AttachmentDAOImpl attachmentDAO;

    @Inject
    private EmailDAOImpl emailDAO;

    private BASE64Decoder decoder;

    private BASE64Encoder encoder;

    public AttachmentService() {
        decoder = new BASE64Decoder();
        encoder = new BASE64Encoder();
    }

    //TODO: how save and receive attachment???  Maybe List<InputStream> or List<File> or Name, Type, Content ???
    public void saveAttachment(String name, byte[] content){
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(name);
        attachmentDTO.setContent(encoder.encode(content));

        //TODO: Create via attachmentDTO, delete Entity
//        attachmentDAO.save(attachmentDTO);
        attachmentDAO.save(attachmentDTO);

    }

    //TODO: Create query,that receive idAttachmnet for idEmail !!!!!
//    public List<Attachment> downloadAttachments(int emailId){
//        EmailDTO email = emailDAO.getById(emailId);
//        return (List<Attachment>) email.getAttachmentCollection();
//    }

    public  StreamedContent downloadAttachment(int id){

        String lol = "attachment #" + id;
        String s = encoder.encode(lol.getBytes());
        try {
            return new DefaultStreamedContent(new ByteArrayInputStream(decoder.decodeBuffer(s)), "application/force-download", "attachment_№" + id + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
