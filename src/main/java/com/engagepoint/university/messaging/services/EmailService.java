package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.services.paginator.impl.LazyEmailDTODataModel;
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
public class EmailService implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private String quickSearch;

    public String getQuickSearch() {
        return quickSearch;
    }

    public void setQuickSearch(String quickSearch) {
        this.quickSearch = quickSearch;
    }

    @Inject
    private EmailDAO emailDAO;

    private List<EmailDTO> emailDTOList;

    private LazyEmailDTODataModel lazyDataModel;

    private String senderForFilteringEmail;  //word which the list of email will be sorted by
    private int CustSearchFiltUse = 0;//checks if user use filter, quick search or custom output of list

    public List<EmailDTO> getEmailDTOList() {
        List<EmailDTO> forReturnOnUI;
        switch (CustSearchFiltUse) {
            case 1:
                forReturnOnUI = doFilterEmail();
                break;
            case 2:
                forReturnOnUI = doQuickSearch();
                break;
            case 0:
                forReturnOnUI = cancelFilterEmail();
                break;
            default:
                forReturnOnUI = cancelFilterEmail();
                break;
        }
        return forReturnOnUI;
    }

    @PostConstruct
    public void init() {
        emailDTOList = new ArrayList<EmailDTO>();
        emailDTOList = emailDAO.getAll();
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
        emailDTOList = emailDAO.getAll();
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

            emailDAO.deleteIdList(idList);
            idList.clear();
            removeList.clear();
        }
    }

    //performed when user press Do FilterEmail button
    public List<EmailDTO> doFilterEmail() {
        CustSearchFiltUse = 1;
        List<EmailDTO> emailListToFilter = emailDTOList;
        List<EmailDTO> listForReturn = new ArrayList<EmailDTO>();
        if ((emailDTOList) != null) {
            if (getSenderForFilteringEmail().equals("")) {
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
        CustSearchFiltUse = 0;
        return emailDTOList;
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


    }

    public List<EmailDTO> doQuickSearch() {
        CustSearchFiltUse = 2;
        return null; //emailDAO.search(getQuickSearch());
    }

    public LazyDataModel getLazyDataModel() {
        return lazyDataModel;
    }
}
