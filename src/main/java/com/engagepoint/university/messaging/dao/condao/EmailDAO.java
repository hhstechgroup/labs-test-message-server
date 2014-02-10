package com.engagepoint.university.messaging.dao.condao;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.Email;
import com.icegreen.greenmail.user.GreenMailUser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public interface EmailDAO extends GenericDAO<Email> {
    public List<Email> getEmailsBySender(String sender);
    public List<Email> getEmailsBySubject(String subject);
    public GreenMailUser createUser(String userMail,String userLogin,String userPassword) ;
    public Session initSession(String host);
    public Message createMessage(String userEmail,String recipientEmail,
                                 String subject,String text,Session session)throws MessagingException;
    public void sendEmail (String host, String userEmail,String userPassword,
                           Session session,Message message) throws MessagingException;
    public List<Message> getAllEmails();
    public Message[] getTestEmailArray();
}
