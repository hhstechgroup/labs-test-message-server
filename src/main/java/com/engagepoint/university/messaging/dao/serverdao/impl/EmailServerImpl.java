package com.engagepoint.university.messaging.dao.serverdao.impl;

import com.engagepoint.university.messaging.dao.serverdao.EmailServerInterface;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailServerImpl implements EmailServerInterface {
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private GreenMail mailServer;
    private Pattern pattern;
    private Matcher matcher;

    public EmailServerImpl(GreenMail mailServer) {
        this.mailServer = mailServer;
    }


    @Override
    public GreenMailUser createUser(String userMail, String userLogin, String userPassword) {
        GreenMailUser user = null;
        if (emailValidation(userMail)&& usernameInputValidation(userLogin)
                && usernameInputValidation(userPassword)){
            user = mailServer.setUser(userMail,userLogin,userPassword);
        } else {
            throw new ValidationException("Email or Login or Password is incorrect.");
        }
        return user;
    }

    private boolean usernameInputValidation (String username) {
        //3-15 characters
        // character @ is not allowed
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    @Override
    public Session initSession(String host) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port", ServerSetupTest.SMTP.getPort());
        Session session = Session.getInstance(properties,null);
        return session;
    }

    @Override
    public Message createMessage(String userEmail, String recipientEmail,
                                 String subject, String text, Session session) throws MessagingException {

        String sub = ""+subject;
        String txt = ""+text;
        Message message = new MimeMessage(session);
        if (emailValidation(userEmail)){
            message.setFrom(new InternetAddress(userEmail));
        } else {
            throw new ValidationException("Entered email did't pass validation.");
        }
        if (emailValidation(recipientEmail)){
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail, false));
        }else {
            throw new ValidationException("Entered email did't pass validation.");
        }
        message.setSubject(sub);
        message.setText(txt);
        message.setSentDate(new Date());
        return message;
    }

    private boolean emailValidation (String emailAddress) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    @Override
    public void sendEmail(String host, String userEmail,String userPassword,
                          Session session,Message message) throws MessagingException {
        SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
        transport.connect(host,userEmail,userPassword);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
    }

    @Override
    public List<Message> getAllEmails() {
        MimeMessage [] messages = mailServer.getReceivedMessages();
        List<Message> messageList = new ArrayList<Message>();
        for (int i = messages.length-1; i >=0;i--) {
            messageList.add(messages[i]);
        }
        return messageList;
    }

    @Override
    public Message[] getTestEmailArray() {
        MimeMessage [] messages = mailServer.getReceivedMessages();
        return messages;
    }
}
