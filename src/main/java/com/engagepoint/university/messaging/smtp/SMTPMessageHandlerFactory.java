package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.services.AttachmentService;
import com.sun.mail.util.BASE64DecoderStream;
import org.apache.commons.io.IOUtils;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.*;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class SMTPMessageHandlerFactory implements MessageHandlerFactory {

    private EmailHandler emailHandler;

    @Override
    public MessageHandler create(MessageContext messageContext) {
        this.emailHandler = new EmailHandler(messageContext);
        return emailHandler;
    }

    class EmailHandler implements MessageHandler {

        MessageContext ctx;
        EmailDAO mdao = new EmailDAOImpl();
        EmailDTO mail;

        public void handleMessage(Message message) throws IOException, MessagingException {
            Object content = message.getContent();
            if (content instanceof String) {
                mail.setBody(String.valueOf(content));
            } else if (content instanceof Multipart) {
                Multipart mp = (Multipart) content;
                handleMultipart(mp);
            }
        }

        public void handleMultipart(Multipart mp) throws MessagingException, IOException {
            int count = mp.getCount();
            Collection<AttachmentDTO> attachmentDTOs = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                BodyPart bp = mp.getBodyPart(i);
                Object content = bp.getContent();
                if (content instanceof String) {
//                    if(bp.getFileName()!=null || !bp.getFileName().equals("")){
//                        byte[] byteArray = IOUtils.toByteArray(bp.getDataHandler().getInputStream());
//                        attachmentDTOs.add(AttachmentService.encodeAttachment(bp.getFileName(), byteArray));
//                    }
//                    else{
//                        mail.setBody(String.valueOf(content));
//                    }
                    mail.setBody(String.valueOf(content));
                } else if (content instanceof InputStream) {
                    BASE64DecoderStream base64DecoderStream = (BASE64DecoderStream) content;
                    byte[] byteArray = IOUtils.toByteArray(base64DecoderStream);
                    attachmentDTOs.add(AttachmentService.encodeAttachment(bp.getFileName(), byteArray));
                } else if (content instanceof Message) {
                    Message message = (Message) content;
                    handleMessage(message);
                } else if (content instanceof Multipart) {
                    Multipart mp2 = (Multipart) content;
                    handleMultipart(mp2);
                }
            }
            mail.setAttachmentCollection(attachmentDTOs);
        }

        public EmailHandler(MessageContext ctx) {
            this.ctx = ctx;
            mail = new EmailDTO();
        }

        public void from(String from) throws RejectException {
            mail.setSender(from);
        }

        public void recipient(String recipient) throws RejectException {
//            mail.set(recipient);
        }

        @Override
        public void data(InputStream data) throws RejectException, TooMuchDataException, IOException {
            Session s = Session.getDefaultInstance(new Properties());
            try {
                MimeMessage message = new MimeMessage(s, data);
                mail.setSubject(message.getSubject());
                mail.setDeliveryDate(message.getSentDate());
                mail.setSendDate(message.getReceivedDate());
                handleMessage(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        public void done() {
            mdao.save(mail);
        }
    }
}
