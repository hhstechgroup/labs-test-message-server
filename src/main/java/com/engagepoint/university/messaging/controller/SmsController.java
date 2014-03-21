package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.controller.lazydatamodel.impl.LazySmsDTODataModel;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.service.repository.SmsService;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SmsController implements Serializable {

    @Inject
    private SmsService smsService;

    private List<SmsDTO> smsDTOList;

    private LazyDataModel lazyDataModel;

    private String quickSearchPhrase;

    @PostConstruct
    public void init() {
        quickSearchPhrase = "";
        smsDTOList = new ArrayList<SmsDTO>();
        smsDTOList = smsService.getAll();
        lazyDataModel = new LazySmsDTODataModel(smsDTOList);
    }

    public void refreshSms() {
        smsDTOList = new ArrayList<SmsDTO>();
        smsDTOList = smsService.getAll();
        lazyDataModel = new LazySmsDTODataModel(smsDTOList);
        if (this.getQuickSearchPhrase() != null) {
            this.setQuickSearchPhrase("");
        }
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
            smsService.deleteIdList(idList);
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
            this.refreshSms();
        } else {
            smsDTOList = smsService.quickSearch(this.getQuickSearchPhrase());
            lazyDataModel = new LazySmsDTODataModel(smsDTOList);
        }
    }
}
