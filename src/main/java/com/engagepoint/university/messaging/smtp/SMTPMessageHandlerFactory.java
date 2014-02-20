package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
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
        EmailDTO mail ;
        AttachmentDTO attdto;
        Collection<AttachmentDTO> atachCollection;

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

        public String getSendDate(String stream) {
            String[] wer = stream.split("Date:");
            String[] wer1 = wer[1].split("From:");
            return wer1[0].trim();
        }

        public String getSubject (String stream) {
            String[] wer2 = stream.split("Subject:");
            String[] wer3 = wer2[1].split("Content-Type:");
            return wer3[0].trim();
        }

        public String getContent (String stream) {
            String s ="";
            if (stream.contains("filename")){
                String[] wer4 = stream.split("7bit");
                String[] wer5 = wer4[1].split("-");
                s = wer5[0].trim();
            } else {
                String[] wer4 = stream.split("7bit");
                s = wer4[1].trim();
            }
            return s;
        }

        public String getAttachmentName (String stream) {
            String[] att = stream.split("filename=\"");
            String[] att1 = att[1].split("\"");
            return att1[0].trim();
        }

        public String getAttachmentBase64 (String stream) {
            String[] att = stream.split("filename=\"");
            String[] att1 = att[1].split("\"");
            return att1[1].trim();
        }
        @Override
        public void data(InputStream data)  {
            //TODO fantasticheskoe rakovstvo. Peredelat'.
            String s = this.convertStreamToStringTwo(data);
            mail.setDeliveryDate(new Date(getSendDate(s)));
            if  (s.contains("Subject:")){
                mail.setSubject(getSubject(s));
            } else {
                mail.setSubject("");
            }
            mail.setBody(getContent(s));
            if  (s.contains("filename")) {
                atachCollection = new ArrayList<>();
                attdto = new AttachmentDTO();
                attdto.setName(getAttachmentName(s));
                attdto.setContent(getAttachmentBase64(s));
                atachCollection.add(attdto);
                mail.setAttachmentCollection(atachCollection);
            }
        }

        public void done() {
            mdao.save(mail);
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
    }
}
