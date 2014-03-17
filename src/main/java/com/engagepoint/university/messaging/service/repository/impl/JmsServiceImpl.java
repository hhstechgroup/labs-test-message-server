package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.JmsDAO;
import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entity.Jms;
import com.engagepoint.university.messaging.entity.Sms;
import com.engagepoint.university.messaging.service.repository.JmsService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("jmsService")
public class JmsServiceImpl implements JmsService {

    @Inject
    private JmsDAO jmsDAO;

    @Override
    public JmsDTO getById(Long id) {
        return null;
    }

    @Override
    public List<JmsDTO> getAll() {
        List<Jms> jmsList = jmsDAO.getAll();
        List<JmsDTO> jmsDTOs = new ArrayList<>();
        Iterator<Jms> jmsIterator = jmsList.iterator();
        while (jmsIterator.hasNext()) {
            jmsDTOs.add(Converter.convert(jmsIterator.next()));
        }
        return jmsDTOs;
    }

    @Override
    public void save(JmsDTO jmsDTO) {
        Jms jms = Converter.convert(jmsDTO);
        jmsDAO.save(jms);
    }

    @Override
    public void update(JmsDTO jmsDTO) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(JmsDTO DTOType) {

    }

    @Override
    public List<JmsDTO> getEmailsBySender(String sender) {
        return null;
    }

    @Override
    public List<JmsDTO> getEmailsBySubject(String subject) {
        return null;
    }

    @Override
    public List<JmsDTO> getEmailsSortByDeliverDate() {
        return null;
    }

    @Override
    public void deleteIdList(List<Long> idList) {

    }

    @Override
    public List<JmsDTO> quickSearch(String quickSearchPhrase) {
        return null;
    }
}
