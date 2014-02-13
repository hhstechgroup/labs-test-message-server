package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.condao.AttachmentDAO;
import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.entities.Attachment;
import com.engagepoint.university.messaging.entities.Email;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@Named
public class AttachmentService {

    @Inject
    private AttachmentDAO attachmentDAO;

    @Inject
    private EmailDAO emailDAO;

    //TODO: how save and receive attachment???  Maybe List<InputStream> or List<File> or Name, Type, Content ???
    public void saveAttachments(List<Attachment> attachments){

    }

    //TODO: Create query,that receive idAttachmnet for idEmail !!!!!
    public List<Attachment> downloadAttachments(int emailId){
        Email email = emailDAO.getById(emailId);
        return (List<Attachment>) email.getAttachmentCollection();
    }

    public  StreamedContent downloadAttachment(int id){

        String lol = "attachment #" + id;
        BASE64Encoder encoder = new BASE64Encoder();
        String s = encoder.encode(lol.getBytes());

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return new DefaultStreamedContent(new ByteArrayInputStream(decoder.decodeBuffer(s)), "application/force-download", "attachment_№" + id + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
