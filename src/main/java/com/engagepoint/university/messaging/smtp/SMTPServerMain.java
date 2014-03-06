package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.services.AttachmentService;
import com.sun.mail.util.BASE64DecoderStream;
import org.apache.commons.io.IOUtils;
import org.subethamail.smtp.*;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.server.SMTPServer;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class SMTPServerMain{
    private static final int PORT = 25;
    private static final String HOSTNAME = "localhost";
    SMTPServer server;
    MessageHandlerFactory messageHandlerFactory;

    @Inject
    private SmsDTO smsDTO;
    @Inject
    private EmailDAO emailDAO;

    public SMTPServerMain() {
        setMessageHandlerFactory();
        setSMTPServer();
    }

    private void setMessageHandlerFactory() {
        this.messageHandlerFactory = new SMTPMessageHandlerFactory();
    }

    private MessageHandlerFactory getMessageHandlerFactory () {
        return this.messageHandlerFactory;
    }

    private void setSMTPServer () {
        this.server = new SMTPServer(getMessageHandlerFactory());
    }

    private SMTPServer getServer () {
        return this.server;
    }

    public void startSMTPServer () {
        getServer().setPort(getPort());
        getServer().setHostName(getHostname());
        getServer().start();
        /*
        Thread t = new Thread(this,"SMTPServeSubetha");
        t.start();
        */
    }

    public void shutDownSMTPServer () {
        getServer().stop();
    }

    public int getPort () {
        return PORT;
    }

    public String getHostname () {
        return HOSTNAME;
    }

    class SMTPMessageHandlerFactory implements MessageHandlerFactory {

        private EmailHandler emailHandler;

        @Override
        public MessageHandler create(MessageContext messageContext) {
            this.emailHandler = new EmailHandler(messageContext);
            return emailHandler;
        }

        class EmailHandler implements MessageHandler {



            MessageContext ctx;
            EmailDTO mail;

            public EmailHandler(MessageContext ctx) {
                this.ctx = ctx;
                mail = new EmailDTO();

            }

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

            @Override
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

            @Override
            public void done() {
                emailDAO.save(mail);
            }
        }
    }

}
