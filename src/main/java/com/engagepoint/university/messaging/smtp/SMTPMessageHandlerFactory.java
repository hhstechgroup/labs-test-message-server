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
        EmailDTO mail ;

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
        public void data(InputStream data)  {
            //TODO fantasticheskoe rakovstvo. Peredelat'.
            String s = this.convertStreamToStringTwo(data);
            String[] wer = s.split("Date:");
            String[] wer1 = wer[1].split("From:");
            mail.setSendDate(new Date(wer1[0]));
            String[] wer2 = s.split("Subject:");
            String[] wer3 = wer2[1].split("Content-Type:");
            mail.setSubject(wer3[0].trim());
            String[] wer4 = wer3[1].split("7bit");
            mail.setBody(wer4[1].trim().trim());
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
