package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

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
        SMTPMailParser mailParser = new SMTPMailParser();
        EmailDTO mail;
        private final String WARNING2 = "Sorry, the message body was too big.";

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

        public String convertStreamToStringTwo(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        public void data(InputStream data) {
            //TODO fantasticheskoe rakovstvo. Peredelat'.
            String s = this.convertStreamToStringTwo(data);

            if (mailParser.getMailSendDate(s) != null) {
                mail.setDeliveryDate(new Date(mailParser.getMailSendDate(s)));
            } else {
                mail.setDeliveryDate(null);
            }

            if (s.contains("Subject:")) {
                mail.setSubject(mailParser.getMailSubject(s));
            } else {
                mail.setSubject("");
            }

            byte[] msgBodyArr = mailParser.getMailContent(s).getBytes();
            if (!(msgBodyArr.length > 1553)) {
                mail.setBody(mailParser.getMailContent(s));
            } else {
                mail.setBody(WARNING2);
            }

            // checking if email has attachments
            if (s.contains("filename")) {
                // getting total attachment size
                // checking credentials
                if (!(mailParser.totalAttachmentSize(s) > 15000000)) {
                    mail.setAttachmentCollection(mailParser.getAttachment(s));
                } else {
                    mail.setAttachmentCollection(null);
                }
            }
        }

        public void done() {
            mdao.save(mail);
        }
    }
}
