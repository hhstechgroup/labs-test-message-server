package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.condao.SmsDAO;
import com.engagepoint.university.messaging.entities.Sms;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SmsService implements Serializable{
    private static final Logger LOG = LoggerFactory.getLogger(SmsService.class);

    @Inject
    private SmsDAO smsDAO;

    @Inject
    private InitService initService;

    private List<Sms> smsList;

    public void setSmsList(List<Sms> sms) {
        this.smsList = sms;
    }

    public List<Sms> getSmsList(){
        return initService.getSmsDTOList();
    }
}
