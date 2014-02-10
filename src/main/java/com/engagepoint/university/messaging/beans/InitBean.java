package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by KArt on 10.02.14.
 */
@Named
@ApplicationScoped
public class InitBean implements Serializable {

    @Inject
    private EmailDAO emailDAO;

    public void init() {
        EmailDTO newEmailDTO = new EmailDTO();
        newEmailDTO.setSender("author 1");
        newEmailDTO.setSubject("Subject: Hello from author 1.");
        newEmailDTO.setBody("Email body from author 1.");
        newEmailDTO.setSendDate(new Date(12315612L));
        newEmailDTO.setDeliveryDate(new Date(123425671L));

        EmailDTO newEmailDTO1 = new EmailDTO();
        newEmailDTO1.setSender("author 2");
        newEmailDTO1.setSubject("Subject: Hello from author 2.");
        newEmailDTO1.setBody("Email body from author 2.");
        newEmailDTO1.setSendDate(new Date(12344678L));
        newEmailDTO1.setDeliveryDate(new Date(123456789L));

        EmailDTO newEmailDTO2 = new EmailDTO();
        newEmailDTO2.setSender("author 3");
        newEmailDTO2.setSubject("Subject: Hello from author 3.");
        newEmailDTO2.setBody("Email body from author 3.");
        newEmailDTO2.setSendDate(new Date(12343678L));
        newEmailDTO2.setDeliveryDate(new Date(123446789L));

        emailDAO.save(new Email(newEmailDTO));
        emailDAO.save(new Email(newEmailDTO1));
        emailDAO.save(new Email(newEmailDTO2));
    }


}
