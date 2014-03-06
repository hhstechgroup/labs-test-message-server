package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by toronaga on 06.03.14.
 */

@Named
public class QuickSearchEmailService {

    @Inject
    private EmailDAO emailDAO;

    private List<EmailDTO> emailDTOList;

    private String quicksearch;

    public List<EmailDTO> getEmailDTOList() {
        return emailDTOList;
    }

    public void setEmailDTOList(List<EmailDTO> emailDTOList) {
        this.emailDTOList = emailDTOList;
    }

    public String getQuicksearch() {
        return quicksearch;
    }

    public void setQuicksearch(String quicksearch) {
        this.quicksearch = quicksearch;
    }

    public void search(){

    }
}
