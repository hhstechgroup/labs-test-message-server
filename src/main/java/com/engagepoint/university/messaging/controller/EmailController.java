package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.controller.LazyDataModel.impl.LazyEmailDTODataModel;
import com.engagepoint.university.messaging.service.repository.EmailService;
import com.engagepoint.university.messaging.util.UtilGeneratorMessage;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class EmailController implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Inject
    private EmailService emailService;

    private List<EmailDTO> emailDTOList;

    private LazyEmailDTODataModel lazyDataModel;

    private String quickSearchPhrase;

    private String senderForFilteringEmail;  //word which the list of email will be sorted by

    public List<EmailDTO> getEmailDTOList() {
        return emailDTOList;
    }

    @PostConstruct
    public void init() {
        emailDTOList = new ArrayList<EmailDTO>();
        emailDTOList = emailService.getAll();
        lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
    }

    public String getSenderForFilteringEmail() {
        return senderForFilteringEmail;
    }

    public void setSenderForFilteringEmail(String senderForFilteringEmail) {
        this.senderForFilteringEmail = senderForFilteringEmail;
    }

    public void refreshEmail() {
        emailDTOList = new ArrayList<EmailDTO>();
        emailDTOList = emailService.getAll();
        lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
    }

    public void deleteCheckedEmails() {
        List<Long> idList = new ArrayList<Long>();
        List<EmailDTO> removeList = new ArrayList<EmailDTO>();
        if (emailDTOList != null) {
            for (EmailDTO item : emailDTOList) {
                if (item.getFlag()) {
                    idList.add(item.getId());
                    removeList.add(item);
                }
            }

            for (EmailDTO item : removeList) {
                emailDTOList.remove(item);
            }

            emailService.deleteIdList(idList);
            idList.clear();
            removeList.clear();
        }
    }

    //performed when user press Do FilterEmail button
    public void doFilterEmail() {
        List<EmailDTO> listForReturn;
        listForReturn = emailService.getEmailsBySender(senderForFilteringEmail);
        lazyDataModel = new LazyEmailDTODataModel(listForReturn);
    }

    //performed when user press Cancel FilterEmail button
    public void cancelFilterEmail() {
        emailDTOList = emailService.getAll();
        lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
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
        emailService.save(emailDTO1);

        EmailDTO emailDTO2 = new EmailDTO();
        emailDTO2.setSender("aura 2");
        emailDTO2.setSubject("Hello 2!");
        emailDTO2.setBody("Body 2");
        emailDTO2.setSendDate(new Date());
        emailDTO2.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        emailService.save(emailDTO2);

        EmailDTO emailDTO3 = new EmailDTO();
        emailDTO3.setSender("authora 3");
        emailDTO3.setSubject("Hello 3!");
        emailDTO3.setBody("Body 3");
        emailDTO3.setSendDate(new Date());
        emailDTO3.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        emailService.save(emailDTO3);
    }

    public LazyDataModel getLazyDataModel() {
        return lazyDataModel;
    }

    public String getQuickSearchPhrase() {
        return quickSearchPhrase;
    }

    public void setQuickSearchPhrase(String quickSearchPhrase) {
        this.quickSearchPhrase = quickSearchPhrase;
    }

    public void quickSearch(){
        if (this.getQuickSearchPhrase().equals(null) || this.getQuickSearchPhrase().equals("")) {
            this.refreshEmail();
        } else {
            emailDTOList = emailService.quickSearch(this.getQuickSearchPhrase());
            lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
        }
    }
}
