package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
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
    private EmailDAOImpl emailDAO;

    @Inject
    private InitService initService;

    private List<EmailDTO> emailList;

    private List<EmailDTO> emailList2;

    public void setEmailList(List<EmailDTO> emails) {

        this.emailList = emails;
    }

    public List<EmailDTO> getEmailList(){

        return emailDAO.getEmailsSortByDeliverDate();
    }

    public void setEmailList2(List<EmailDTO> emails) {

        this.emailList = emails;
    }

    public List<EmailDTO> getEmailList2(){

        return initService.getEmailDTOList();
    }


}
