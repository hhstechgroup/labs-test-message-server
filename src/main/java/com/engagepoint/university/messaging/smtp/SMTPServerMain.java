package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.controller.AttachmentController;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;

import com.engagepoint.university.messaging.service.repository.EmailService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.*;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.server.SMTPServer;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class SMTPServerMain {
    private static final Logger LOG = LoggerFactory.getLogger(SMTPServerMain.class);
//    private static final int PORT = 25;
//    private static final String HOSTNAME = "127.0.0.1";
    SMTPServer server;
    MessageHandlerFactory messageHandlerFactory;

    private String host;
    private int port;

    public String getHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
    public int getPort(){
        return server.getPort();
    }

    @Inject
    private EmailService emailService;

    public SMTPServerMain() {
        setMessageHandlerFactory();
        setSMTPServer();
    }

    private void setMessageHandlerFactory() {
        this.messageHandlerFactory = new SMTPMessageHandlerFactory();
    }

    private MessageHandlerFactory getMessageHandlerFactory() {
        return this.messageHandlerFactory;
    }

    private void setSMTPServer() {
        this.server = new SMTPServer(getMessageHandlerFactory());
    }

    private SMTPServer getServer() {
        return this.server;
    }

    public void startSMTPServer() {
        getServer().start();
    }

    public void shutDownSMTPServer() {
        getServer().stop();
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

            private void handleMessage(Message message) throws MessagingException, IOException {
                Object content = message.getContent();
                if (content instanceof String) {
                    mail.setBody(String.valueOf(content));
                } else if (content instanceof Multipart) {
                    Multipart mp = (Multipart) content;
                    handleMultipart(mp);
                }
            }

            private void handleMultipart(Multipart mp) throws MessagingException, IOException {
                int count = mp.getCount();
                Collection<AttachmentDTO> attachmentDTOs = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    BodyPart bp = mp.getBodyPart(i);
                    Object content = bp.getContent();
                    if (content instanceof String) {
                        mail.setBody(String.valueOf(content));
                    } else if (content instanceof InputStream) {
                        byte[] byteArray = IOUtils.toByteArray((InputStream) content);
                        attachmentDTOs.add(AttachmentController.encodeAttachment(bp.getFileName(), byteArray));
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
            public void from(String from) {
                mail.setSender(from);
            }

            public void recipient(String recipient) {
                mail.setRecipient(recipient);
            }

            @Override
            public void data(InputStream data) throws IOException {
                Session s = Session.getDefaultInstance(new Properties());
                try {
                    MimeMessage message = new MimeMessage(s, data);
                    mail.setSubject(message.getSubject());
                    mail.setDeliveryDate(message.getSentDate());
                    mail.setSendDate(message.getReceivedDate());
                    handleMessage(message);
                } catch (MessagingException e) {
                    LOG.info(e.getMessage(), e);
                }
            }

            @Override
            public void done() {
                emailService.save(mail);
            }
        }
    }

}
