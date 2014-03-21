package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.controller.lazydatamodel.impl.LazyEmailDTODataModel;
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

    public List<EmailDTO> getEmailDTOList() {
        return emailDTOList;
    }

    @PostConstruct
    public void init() {
        emailDTOList = new ArrayList<EmailDTO>();
        emailDTOList = emailService.getAll();
        lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
    }

    public void refreshEmail() {
        emailDTOList = new ArrayList<EmailDTO>();
        emailDTOList = emailService.getAll();
        lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
        if (this.getQuickSearchPhrase() != null) {
            this.setQuickSearchPhrase("");
        }
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
        emailDTO1.setSender("author@mail.com");
        emailDTO1.setSubject("Hello 1!");
        emailDTO1.setBody("Hi there, Doreen\n" +
                "\n" +
                "\n" +
                "Long time no see. Hope all's well in your world!\n" +
                "\n" +
                "I haven't been to JALT all year, shame on me, but I needed the break.\n" +
                "I still see the guys and I'm going to the national, so don't write me off\n" +
                "yet!\n");
        emailDTO1.setSendDate(new Date());
        emailDTO1.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        emailDTO1.setAttachmentCollection(attachmentCollection);
        emailService.save(emailDTO1);

        EmailDTO emailDTO2 = new EmailDTO();
        emailDTO2.setSender("aura@ukr.net");
        emailDTO2.setSubject("Hello 2!");
        emailDTO2.setBody("Hi there, Doreen\n" +
                "\n" +
                "Long time no see. Hope all's well in your world!　\n" +
                "\n" +
                "I haven't been to JALT all year, shame on me, but I needed the break. \n" +
                "I still see the guys and I'm going to the national, so don't write me off \n" +
                "yet!\n" +
                "\n" +
                "I was trying to remember who you've published with in the past, and I\n" +
                "wondered if you had any contacts at Nan'Un-Do. I want to send a \n" +
                "proposal to them but have no names, and the personal touch is always \n" +
                "best!\n" +
                "\n" +
                "I might go to see Jane Willis, family commitments permitting, any plans in\n" +
                "that direction? Going to Shizuoka I presume?\n" +
                "\n" +
                "Bye for now \n" +
                "All the best ");
        emailDTO2.setSendDate(new Date());
        emailDTO2.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        emailService.save(emailDTO2);

        EmailDTO emailDTO3 = new EmailDTO();
        emailDTO3.setSender("authora@gmail.ru");
        emailDTO3.setSubject("Hello 3!");
        emailDTO3.setBody("Hi there, Doreen\n" +
                "\n" +
                "Long time no see. Hope all's well in your world!　\n" +
                "\n" +
                "I haven't been to JALT all year, shame on me, but I needed the break. \n" +
                "I still see the guys and I'm going to the national, so don't write me off \n" +
                "yet!\n" +
                "\n");
        emailDTO3.setSendDate(new Date());
        emailDTO3.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        emailService.save(emailDTO3);
        LOG.warn("ADD 3 EMAILS");
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
        if (this.getQuickSearchPhrase() == null || "".equals(getQuickSearchPhrase())) {
            this.refreshEmail();
        } else {
            emailDTOList = emailService.quickSearch(this.getQuickSearchPhrase());
            lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
        }
    }
}
