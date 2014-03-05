package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.services.lazy.impl.LazySmsDTODataModel;
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
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class SmsService implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(SmsService.class);

    private String quickSearch;
    public String getQuickSearch() {
        return quickSearch;
    }
    public void setQuickSearch(String quickSearch) {
        this.quickSearch = quickSearch;
    }

    @Inject
    private SmsDAO smsDAO;

    private List<SmsDTO> smsDTOList;

    private LazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        smsDTOList = new ArrayList<SmsDTO>();
        smsDTOList = smsDAO.getAll();
        lazyDataModel = new LazySmsDTODataModel(smsDTOList);
    }

    private String senderForFilteringSms;  //word which the list of sms will be sorted by

    private int CustSearchFiltUse = 0;//checks if user use filter, quick search or custom output of list

    public List<SmsDTO> getSmsDTOList() {
        List<SmsDTO> forReturnOnUI;
        switch (CustSearchFiltUse){
            case 1:
                forReturnOnUI = doFilterSms();
                break;
            case 2:
                forReturnOnUI = doQuickSearch();
                break;
            case 0:
                forReturnOnUI = cancelFilterSms();
                break;
            default:
                forReturnOnUI = cancelFilterSms();
                break;
        }
        return forReturnOnUI;
    }

    public void setSmsDTOList(List<SmsDTO> smsDTOList) {
        this.smsDTOList = smsDTOList;
    }

    public String getSenderForFilteringSms() {
        return senderForFilteringSms;
    }

    public void setSenderForFilteringSms(String senderForFilteringSms) {
        this.senderForFilteringSms = senderForFilteringSms;
    }

    public void refreshSms() {
        smsDTOList = smsDAO.getAll();
    }

    public void deleteCheckedSMS() {
        List<Long> idList = new ArrayList<Long>();
        List<SmsDTO> removeList = new ArrayList<SmsDTO>();
        if (smsDTOList != null) {
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

    //performed when user press Do FilterSms button
    public List<SmsDTO> doFilterSms() {
        CustSearchFiltUse = 1;
        List<SmsDTO> l = smsDTOList;
        List<SmsDTO> listForReturn = new ArrayList<SmsDTO>();
        if((smsDTOList) != null){
        if (senderForFilteringSms.equals("")) listForReturn = l;
        else {
            for (SmsDTO i : l) {
                if (i.getSender().indexOf(getSenderForFilteringSms()) >= 0)
                    listForReturn.add(i);
            }
        }
        }
        return listForReturn;
    }

    //performed when user press Cancel FilterSms button
    public List<SmsDTO> cancelFilterSms() {
        CustSearchFiltUse = 0;
        return smsDTOList;
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

    public List<SmsDTO> doQuickSearch(){
        CustSearchFiltUse = 2;
        return smsDAO.search(getQuickSearch());
    }

    public LazyDataModel getLazyDataModel() {
        return lazyDataModel;
    }
}
