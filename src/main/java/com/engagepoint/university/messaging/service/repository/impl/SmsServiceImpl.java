package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entity.Sms;
import com.engagepoint.university.messaging.service.repository.SmsService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("smsService")
public class SmsServiceImpl implements SmsService {

    @Inject
    private SmsDAO smsDAO;


    @Override
    public List<SmsDTO> getSmsBySender(String sender) {
        List<Sms> smsList = smsDAO.getSmsBySender(sender);
        List<SmsDTO> smsDTOs = new ArrayList<>();
        for (Sms sms:smsList) {
            smsDTOs.add(Converter.convert(sms));
        }
        return smsDTOs;
    }

    @Override
    public void deleteIdList(List<Long> idList) {
        smsDAO.deleteIdList(idList);
    }

    @Override
    public List<SmsDTO> quickSearch(String quickSearchPhrase) {
        List<Sms> smsList = smsDAO.quickSearch(quickSearchPhrase);
        List<SmsDTO> smsDTOs = new ArrayList<>();
        for (Sms sms:smsList) {
            smsDTOs.add(Converter.convert(sms));
        }
        return smsDTOs;
    }

    @Override
    public SmsDTO getById(Long id) {
        Sms sms = smsDAO.getById(id);
        return Converter.convert(sms);
    }

    @Override
    public List<SmsDTO> getAll() {
        List<Sms> smsList = smsDAO.getAll();
        List<SmsDTO> smsDTOs = new ArrayList<>();
        for (Sms sms:smsList) {
            smsDTOs.add(Converter.convert(sms));
        }
        return smsDTOs;
    }

    @Override
    public void save(SmsDTO smsDTO) {
        smsDAO.save(Converter.convert(smsDTO));
    }

    @Override
    public void update(SmsDTO smsDTO) {
        smsDAO.save(Converter.convert(smsDTO));
    }

    @Override
    public void delete(Long id) {
        smsDAO.delete(id);
    }

    @Override
    public void delete(SmsDTO smsDTO) {
        smsDAO.delete(Converter.convert(smsDTO));
    }
}
