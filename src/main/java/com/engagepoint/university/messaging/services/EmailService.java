package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EmailService implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

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
