package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.util.UtilGeneratorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    @Inject
    private SmsDAO smsDAO;

    private List<SmsDTO> smsDTOList;

    private String senderForFilteringSms;  //word which the list of sms will be sorted by

    private boolean flagFilterSms = false;  //checks if user use FilterSms

    public List<SmsDTO> getSmsDTOList() {

        if (flagFilterSms) return doFilterSms();
        else {

            return cancelFilterSms();
        }
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
        flagFilterSms = true;
        List<SmsDTO> l = smsDTOList;
        List<SmsDTO> listForReturn = new ArrayList<SmsDTO>();
        if (senderForFilteringSms.equals("")) listForReturn = l;
        else {
            for (SmsDTO i : l) {
                if (i.getSender().equals(senderForFilteringSms))
                    listForReturn.add(i);
            }
        }
        return listForReturn;
    }

    //performed when user press Cancel FilterSms button
    public List<SmsDTO> cancelFilterSms() {
        flagFilterSms = false;
        //setSenderForFilteringSms("");
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
}
