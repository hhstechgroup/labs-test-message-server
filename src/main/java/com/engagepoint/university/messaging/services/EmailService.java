package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.smtp.SMTPMessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class EmailService implements Serializable, Runnable {

    @Inject
    private EmailDAOImpl emailDAO;

    @Inject
    private InitService initService;

    @Inject
    private SMTPMessageHandlerFactory emailFactory;

    public EmailService() {
        Thread thread = new Thread(this,"SubeThaSMTP");
        thread.start();
    }

    @Override
    public void run() {
        SMTPServer server = new SMTPServer(emailFactory);
        server.setPort(25000);
        server.start();
    }

    private List<EmailDTO> emailListSortedByBackend;

    private List<EmailDTO> emailListSortedByFrontend;

    public void setEmailListSortedByBackend(List<EmailDTO> emails) {

        this.emailListSortedByBackend = emails;
    }

    public List<EmailDTO> getEmailListSortedByBackend(){

        return emailDAO.getEmailsSortByDeliverDate();
    }

    public void setEmailListSortedByFrontend(List<EmailDTO> emails) {

        this.emailListSortedByBackend = emails;
    }

    public List<EmailDTO> getEmailListSortedByFrontend(){

        return initService.getEmailDTOList();
    }



}
