package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.condao.SmsDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.util.UtilGeneratorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class InitService implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(InitService.class);

    @Inject
    private EmailDAO emailDAO;

    @Inject
    private SmsDAO smsDAO;

    private List<Email> emailDTOList;
    private List<Sms> smsDTOList;

    public List<Email> getEmailDTOList() {
        return emailDTOList;
    }

    public void setEmailDTOList(List<Email> emailDTOList) {
        this.emailDTOList = emailDTOList;
    }

    public List<Sms> getSmsDTOList() {
        return smsDTOList;
    }

    public void setSmsDTOList(List<Sms> smsDTOList) {
        this.smsDTOList = smsDTOList;
    }

    @PostConstruct
    void init(){
        emailDTOList = new ArrayList<Email>();
        smsDTOList = new ArrayList<Sms>();
    }

    public void addEmail() {
        EmailDTO emailDTO1 = new EmailDTO();
        emailDTO1.setSender("author 1");
        emailDTO1.setSubject("Hello 1!");
        emailDTO1.setBody("Body 1");
        emailDTO1.setSendDate(new Date());
        emailDTO1.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        emailDAO.save(new Email(emailDTO1));
        //emailDTOList.add(new Email(emailDTO1));

        EmailDTO emailDTO2 = new EmailDTO();
        emailDTO2.setSender("author 2");
        emailDTO2.setSubject("Hello 2!");
        emailDTO2.setBody("Body 2");
        emailDTO2.setSendDate(new Date());
        emailDTO2.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO2.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        emailDAO.save(new Email(emailDTO2));
        //emailDTOList.add(new Email(emailDTO2));

        EmailDTO emailDTO3 = new EmailDTO();
        emailDTO3.setSender("author 3");
        emailDTO3.setSubject("Hello 3!");
        emailDTO3.setBody("Body 3");
        emailDTO3.setSendDate(new Date());
        emailDTO3.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO3.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        emailDAO.save(new Email(emailDTO3));
        //emailDTOList.add(new Email(emailDTO3));
        emailDTOList = emailDAO.getEmailsSortByDeliverDate();
    }

    public void addSms() {
        SmsDTO smsDTO1 = new SmsDTO();
        smsDTO1.setSender("author 1");
        smsDTO1.setBody("Hello 1!");
        smsDTO1.setSendDate(new Date());
        smsDTO1.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        smsDAO.save(new Sms(smsDTO1));

        SmsDTO smsDTO2 = new SmsDTO();
        smsDTO2.setSender("author 2");
        smsDTO2.setBody("Hello 2!");
        smsDTO2.setSendDate(new Date());
        smsDTO2.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        smsDAO.save(new Sms(smsDTO2));

        SmsDTO smsDTO3 = new SmsDTO();
        smsDTO3.setSender("author 3");
        smsDTO3.setBody("Hello 3!");
        smsDTO3.setSendDate(new Date());
        smsDTO3.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        smsDAO.save(new Sms(smsDTO3));

        smsDTOList = smsDAO.getAll();
    }

}
