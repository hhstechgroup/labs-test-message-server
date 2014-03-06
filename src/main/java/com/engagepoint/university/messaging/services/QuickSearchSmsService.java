package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dao.repository.SmsDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by toronaga on 06.03.14.
 */
@Named
public class QuickSearchSmsService {
    @Inject
    private SmsDAO smsDAO;

    private List<SmsDTO> smsDTOList;

    private String quicksearch;

    public List<SmsDTO> getSmsDTOList() {
        return smsDTOList;
    }

    public void setSmsDTOList(List<SmsDTO> smsDTOList) {
        this.smsDTOList = smsDTOList;
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
