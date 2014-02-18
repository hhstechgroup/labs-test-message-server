package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.smpp.ServerMain;
import com.engagepoint.university.messaging.smtp.SMTPMessageHandlerFactory;
import com.engagepoint.university.messaging.util.UtilGeneratorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.server.SMTPServer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class InitService implements Serializable, Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(InitService.class);
    @Inject
    private ServerMain serverMain;

    @Inject
    private EmailDAO emailDAO;

    @Inject
    private SmsDAO smsDAO;

    @Inject
    private SMTPMessageHandlerFactory emailFactory;

    @PostConstruct
    void init() {
        emailDTOList = new ArrayList<EmailDTO>();
        smsDTOList = new ArrayList<SmsDTO>();
        emailDTOList = emailDAO.getAll();
        smsDTOList = smsDAO.getAll();
        Thread thread = new Thread(this, "SubeThaSMTP");
        thread.start();
        try {
            serverMain.startSmppServer();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        SMTPServer server = new SMTPServer(emailFactory);
        server.setPort(25000);
        server.start();
    }

    private List<EmailDTO> emailDTOList;

    private List<SmsDTO> smsDTOList;

    public List<EmailDTO> getEmailDTOList() {
        return emailDTOList;
    }

    public void setEmailDTOList(List<EmailDTO> emailDTOList) {
        this.emailDTOList = emailDTOList;
    }

    public List<SmsDTO> getSmsDTOList() {
        return smsDTOList;
    }

    public void setSmsDTOList(List<SmsDTO> smsDTOList) {
        this.smsDTOList = smsDTOList;
    }

    public void addEmail() {
        LOG.debug("Create attachment");
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName("attachment.txt");
        attachmentDTO.setContent("YXR0YWNobWVudA==");

        AttachmentDTO attachmentDTO1 = new AttachmentDTO();
        attachmentDTO1.setName("attachment1.txt");
        attachmentDTO1.setContent("YXR0YWNobWVudCBURVNU");

        AttachmentDTO attachmentDTO2 = new AttachmentDTO();
        attachmentDTO2.setName("attachment2.txt");
        attachmentDTO2.setContent("SGkgSXZhbiBWYXNpbGlpdiEhIQ==");

        Collection<AttachmentDTO> attachmentCollection = new ArrayList<>();
        attachmentCollection.add(attachmentDTO);
        attachmentCollection.add(attachmentDTO1);
        attachmentCollection.add(attachmentDTO2);

        LOG.debug("Begin add email");
        EmailDTO emailDTO1 = new EmailDTO();
        emailDTO1.setSender("author 1");
        emailDTO1.setSubject("Hello 1!");
        emailDTO1.setBody("Body 1");
        emailDTO1.setSendDate(new Date());
        emailDTO1.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        emailDTO1.setAttachmentCollection(attachmentCollection);
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        emailDAO.save(emailDTO1);
        //emailDTOList.add(new Email(emailDTO1));

        EmailDTO emailDTO2 = new EmailDTO();
        emailDTO2.setSender("author 2");
        emailDTO2.setSubject("Hello 2!");
        emailDTO2.setBody("Body 2");
        emailDTO2.setSendDate(new Date());
        emailDTO2.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO2.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        emailDAO.save(emailDTO2);
        //emailDTOList.add(new Email(emailDTO2));

        EmailDTO emailDTO3 = new EmailDTO();
        emailDTO3.setSender("author 3");
        emailDTO3.setSubject("Hello 3!");
        emailDTO3.setBody("Body 3");
        emailDTO3.setSendDate(new Date());
        emailDTO3.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO3.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        emailDAO.save(emailDTO3);
        //emailDTOList.add(new Email(emailDTO3));
        emailDTOList = emailDAO.getAll();
    }

    public void addSms() {
        SmsDTO smsDTO1 = new SmsDTO();
        smsDTO1.setSender("author 1");
        smsDTO1.setBody("Hello 1!");
        smsDTO1.setSendDate(new Date());
        smsDTO1.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        smsDAO.save(smsDTO1);

        SmsDTO smsDTO2 = new SmsDTO();
        smsDTO2.setSender("author 2");
        smsDTO2.setBody("Hello 2!");
        smsDTO2.setSendDate(new Date());
        smsDTO2.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        smsDAO.save(smsDTO2);

        SmsDTO smsDTO3 = new SmsDTO();
        smsDTO3.setSender("author 3");
        smsDTO3.setBody("Hello 3!");
        smsDTO3.setSendDate(new Date());
        smsDTO3.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        smsDAO.save(smsDTO3);

        smsDTOList = smsDAO.getAll();
    }

    public void refreshSms() {
        smsDTOList = smsDAO.getAll();
    }

    public void refreshEmail() {
        emailDTOList = emailDAO.getAll();
    }

    public void deleteCheckedEmails() {
        List<Long> idList = new ArrayList<Long>();
        List<EmailDTO> removeList = new ArrayList<EmailDTO>();

        for (EmailDTO item : emailDTOList) {
            if (item.getFlag()) {
                idList.add(item.getId());
                removeList.add(item);
            }
        }

        for (EmailDTO item : removeList) {
            emailDTOList.remove(item);
        }

        emailDAO.deleteIdList(idList);
        idList.clear();
        removeList.clear();
    }

    public void deleteCheckedSMS() {
        List<Long> idList = new ArrayList<Long>();
        List<SmsDTO> removeList = new ArrayList<SmsDTO>();

        for (SmsDTO item : smsDTOList) {
            if (item.getFlag()) {
                idList.add(item.getId());
                removeList.add(item);
            }
        }

        for (SmsDTO item : removeList) {
            smsDTOList.remove(item);
        }

        smsDAO.deleteIdList(idList);
        idList.clear();
        removeList.clear();
    }
}