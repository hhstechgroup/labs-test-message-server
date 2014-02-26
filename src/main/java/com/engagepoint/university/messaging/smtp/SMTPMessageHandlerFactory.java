package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
        EmailDTO mail;
        AttachmentDTO attdto;
        Collection<AttachmentDTO> atachCollection;
        private BASE64Encoder encoder = new BASE64Encoder();
        private final String WARNING1 = "Sorry, the attachment was too big.";
        String encodedAttachmentWarning = encoder.encode(WARNING1.getBytes());
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

        public String getSendDate(String stream) {
            String[] wer = stream.split("Date:");
            String[] wer1 = wer[1].split("From:");
            return wer1[0].trim();
        }

        public String getSubject(String stream) {
            String[] wer2 = stream.split("Subject:");
            String[] wer3 = wer2[1].split("Content-Type:");
            return wer3[0].trim();
        }

        public String getContent(String stream) {
            String s = "";
            if (stream.contains("filename")) {
                String[] wer4 = stream.split("7bit");
                String[] wer5 = wer4[1].split("-");
                s = wer5[0].trim();
            } else {
                String[] wer4 = stream.split("7bit");
                s = wer4[1].trim();
            }
            return s;
        }

        public String getAttachmentName(String stream) {
            String[] att = stream.split("filename=\"");
            String[] att1 = att[1].split("\"");
            return att1[0].trim();
        }

        public String getAttachmentBase64(String stream) {
            String[] att = stream.split("filename=\"");
            String[] att1 = att[1].split("\"");
            return att1[1].trim();
        }

        public String getBoundary(String stream) {
            String[] bound = stream.split("boundary=\"");
            String[] bound1 = bound[1].split("\"");
            return bound1[0].trim();
        }

        public Collection<AttachmentDTO> testGetMultiAttachments(String stream) {
            AttachmentDTO attachmentDTO;
            atachCollection = new ArrayList<AttachmentDTO>();
            String s = stream;
            String boundString = getBoundary(s);
            byte[] boundArr = boundString.getBytes();
            String[] arr = stream.split("Content-Type:");
            if (arr.length > 0) {
                for (int i = 1; i <= arr.length; i++) {
                    attachmentDTO = new AttachmentDTO();
                    String[] att = stream.split("filename=\"");
                    String[] att1 = att[1].split("\"");
                    attachmentDTO.setName(att1[0]);
                    String attString = att1[1];
                    byte[] attArr = attString.getBytes();
                    attachmentDTO.setContent(attString.substring(0, (attArr.length - 4) - boundArr.length));
                    atachCollection.add(attachmentDTO);
                }
            }
            return atachCollection;
        }

        @Override
        public void data(InputStream data) {
            //TODO fantasticheskoe rakovstvo. Peredelat'.
            String s = this.convertStreamToStringTwo(data);
            mail.setDeliveryDate(new Date(getSendDate(s)));
            if (s.contains("Subject:")) {
                mail.setSubject(getSubject(s));
            } else {
                mail.setSubject("");
            }
            byte[] msgBodyArr = getContent(s).getBytes();
            if (!(msgBodyArr.length > 1553)) {
                mail.setBody(getContent(s));
            } else {
                mail.setBody(WARNING2);
            }
            if (s.contains("filename")) {
                atachCollection = new ArrayList<>();
                String[] arr = s.split("Content-Disposition:");
                ArrayList<String> attaches = new ArrayList<>(Arrays.asList(arr));
                attaches.remove(0);
                //TODO отрефакторить и запилить чистку boundary
                String boundString = getBoundary(s);
                byte[] boundArr = boundString.getBytes();
                for (String attachments : attaches) {
                    attdto = new AttachmentDTO();
                    attdto.setName(getAttachmentName(attachments));
                    byte[] checkAttBody = getAttachmentBase64(attachments).getBytes();
                    if (!(checkAttBody.length > 14000000)) {
                        String attBase = getAttachmentBase64(attachments);
                        byte[] att = attBase.getBytes();
                        attdto.setContent(attBase);
                    } else {
                        attdto.setContent(encodedAttachmentWarning);
                    }

                    atachCollection.add(attdto);
                }
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
