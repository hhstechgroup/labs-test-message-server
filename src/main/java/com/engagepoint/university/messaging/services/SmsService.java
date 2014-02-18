package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.smpp.ServerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SmsService implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(SmsService.class);

    @Inject
    private SmsDAO smsDAO;

    @Inject
    private InitService initService;

    @Inject
    private ServerMain serverMain;

    private List<SmsDTO> smsList;

    public void setSmsList(List<SmsDTO> smsList) {
        this.smsList = smsList;
    }

    public List<SmsDTO> getSmsList(){
        return initService.getSmsDTOList();
    }


}
