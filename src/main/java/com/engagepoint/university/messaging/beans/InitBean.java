package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.condao.SmsDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.entities.Sms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ApplicationScoped
public class InitBean implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(InitBean.class);

    @Inject
    private EmailDAO emailDAO;

    @Inject
    private SmsDAO smsDAO;

    public void addEmail() {
        emailDAO.save(new Email(new EmailDTO("author 1","Hello 1!","Body 1",new Date(),new Date())));
        emailDAO.save(new Email(new EmailDTO("author 2","Hello 2!","Body 2",new Date(),new Date())));
        emailDAO.save(new Email(new EmailDTO("author 3","Hello 3!","Body 3",new Date(),new Date())));
    }

    public void addSms() {
        smsDAO.save(new Sms(new SmsDTO("author 1","Body 1",new Date(),new Date())));
        smsDAO.save(new Sms(new SmsDTO("author 2","Body 2",new Date(),new Date())));
        smsDAO.save(new Sms(new SmsDTO("author 3","Body 3",new Date(),new Date())));
        }

}
