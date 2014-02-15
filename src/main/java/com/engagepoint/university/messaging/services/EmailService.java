package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.smtp.SMTPMessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EmailService implements Serializable,Runnable {

    private SMTPMessageHandlerFactory emailFactory;

    public EmailService() {
        Thread thread = new Thread(this,"SubeThaSMTP");
        thread.start();
    }

    @Override
    public void run() {
        emailFactory = new SMTPMessageHandlerFactory();
        SMTPServer server = new SMTPServer(emailFactory);
        server.setPort(25000);
        server.start();
    }

    @Inject
    private EmailDAO emailDAO;

    @Inject
    private InitService initService;

    private List<Email> emailList;

    private List<Email> emailList2;

    public void setEmailList(List<Email> emails) {

        this.emailList = emails;
    }

    public List<Email> getEmailList(){

        return emailDAO.getEmailsSortByDeliverDate();
    }

    public void setEmailList2(List<Email> emails) {

        this.emailList = emails;
    }

    public List<Email> getEmailList2(){

        return initService.getEmailDTOList();
    }
}
