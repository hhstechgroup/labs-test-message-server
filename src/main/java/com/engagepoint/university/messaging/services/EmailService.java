package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.smtp.SMTPEmailServer;
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

    private SMTPEmailServer emailServer;
    private SMTPMessageHandlerFactory emailFactory;
//    InetAddress addr = InetAddress.getByName("127.0.0.1");

    public EmailService() {
        Thread thread = new Thread();
        thread.start();
//        emailServer = new SMTPEmailServer();
//        emailFactory = new SMTPMessageHandlerFactory();
//        emailServer.setMailserver(emailFactory);
//        emailServer.getServer().setHostName("127.0.0.1");
//        emailServer.setPort(24000);
//        emailServer.getServer().start();
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


    @Override
    public void run() {
        SMTPMessageHandlerFactory factory = new SMTPMessageHandlerFactory();
        SMTPServer server = new SMTPServer(factory);
        server.setPort(10001);
        server.start();
    }
}
