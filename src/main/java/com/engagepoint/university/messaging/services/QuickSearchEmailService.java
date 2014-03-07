package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by toronaga on 06.03.14.
 */

@Named
@ViewScoped
public class QuickSearchEmailService implements Serializable {

    @Inject
    private EmailDAO emailDAO;

    private List<EmailDTO> emailDTOList;

    private String quickSearchPhrase;

    public List<EmailDTO> getEmailDTOList() {
        return emailDTOList;
    }

    public void setEmailDTOList(List<EmailDTO> emailDTOList) {
        this.emailDTOList = emailDTOList;
    }

    public String getQuickSearchPhrase() {
        return quickSearchPhrase;
    }

    public void setQuickSearchPhrase(String quicksearch) {
        this.quickSearchPhrase = quicksearch;
    }

    public void search(){
        if (this.getQuickSearchPhrase() != "") {
            this.setEmailDTOList(emailDAO.emailQuickSearch(this.getQuickSearchPhrase()));
        }
    }
}
