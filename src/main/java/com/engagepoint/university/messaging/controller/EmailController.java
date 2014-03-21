package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.controller.lazydatamodel.impl.LazyEmailDTODataModel;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.service.repository.EmailService;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    public LazyDataModel getLazyDataModel() {
        return lazyDataModel;
    }

    public String getQuickSearchPhrase() {
        return quickSearchPhrase;
    }

    public void setQuickSearchPhrase(String quickSearchPhrase) {
        this.quickSearchPhrase = quickSearchPhrase;
    }

    public void quickSearch() {
        if (this.getQuickSearchPhrase() == null || "".equals(getQuickSearchPhrase())) {
            this.refreshEmail();
        } else {
            emailDTOList = emailService.quickSearch(this.getQuickSearchPhrase());
            lazyDataModel = new LazyEmailDTODataModel(emailDTOList);
        }
    }
}
