package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.util.UtilGeneratorMessage;
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
public class EmailService implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Inject
    private EmailDAO emailDAO;

    private List<EmailDTO> emailDTOList;

    private String senderForFilteringEmail;  //word which the list of email will be sorted by

    private boolean flagFilterEmail = false;  //checks if user use FilterEmail

    @PostConstruct
    public void init() {
        emailDTOList = new ArrayList<EmailDTO>();
        emailDTOList = emailDAO.getAll();
    }

    public void setEmailDTOList(List<EmailDTO> emails) {

        this.emailDTOList = emails;
    }

    public List<EmailDTO> getEmailDTOList() {

        if (flagFilterEmail) return doFilterEmail();
        else {

            return cancelFilterEmail();
        }
    }

    public String getSenderForFilteringEmail() {
        return senderForFilteringEmail;
    }

    public void setSenderForFilteringEmail(String senderForFilteringEmail) {
        this.senderForFilteringEmail = senderForFilteringEmail;
    }

    public void refreshEmail() {
        emailDTOList = emailDAO.getAll();
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

            emailDAO.deleteIdList(idList);
            idList.clear();
            removeList.clear();
        }
    }

    //performed when user press Do FilterEmail button
    public List<EmailDTO> doFilterEmail() {
        flagFilterEmail = true;
        List<EmailDTO> emailListToFilter = emailDTOList;
        List<EmailDTO> listForReturn = new ArrayList<EmailDTO>();
        if((emailDTOList) != null){
            if (getSenderForFilteringEmail().equals("")){
                listForReturn = emailListToFilter;
            } else {
                for (EmailDTO filteredEmails : emailListToFilter) {
                    if (filteredEmails.getSender().contains(getSenderForFilteringEmail()))
                        listForReturn.add(filteredEmails);
                }
            }
        }
        return listForReturn;
    }

    //performed when user press Cancel FilterEmail button
    public List<EmailDTO> cancelFilterEmail() {
        flagFilterEmail = false;
        //setSenderForFilteringEmail("");
        return emailDTOList;
    }

    public void saveEmail(EmailDTO emailDTO) {
        emailDAO.save(emailDTO);
    }

    public void addEmail() {
        /*
        LOG.warn("Create attachment");
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

        */

        LOG.warn("Begin add email");
        EmailDTO emailDTO1 = new EmailDTO();
        emailDTO1.setSender("author 1");
        emailDTO1.setSubject("Hello 1!");
        emailDTO1.setBody("Body 1");
        emailDTO1.setSendDate(new Date());
        emailDTO1.setDeliveryDate(UtilGeneratorMessage.getRandomDate());
        //emailDTO1.setAttachmentCollection(attachmentCollection);
        //emailDTO1.setRecieverList(UtilGeneratorMessage.getRandomRecieverCollection());
        saveEmail(emailDTO1);


        //emailDTOList = emailDAO.getAll();
    }

}
