package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.JmsDAO;
import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.entity.Jms;
import com.engagepoint.university.messaging.service.repository.JmsService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("jmsService")
public class JmsServiceImpl implements JmsService {

    @Inject
    private JmsDAO jmsDAO;

    @Override
    public JmsDTO getById(Long id) {
        Jms jms = jmsDAO.getById(id);
        return Converter.convert(jms);
    }

    @Override
    public List<JmsDTO> getAll() {
        List<Jms> jmsList = jmsDAO.getAll();
        List<JmsDTO> jmsDTOs = new ArrayList<>();
        for (Jms jms :jmsList) {
            jmsDTOs.add(Converter.convert(jms));
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
        Jms jms = Converter.convert(jmsDTO);
        jmsDAO.update(jms);
    }

    @Override
    public void delete(Long id) {
        jmsDAO.delete(id);
    }

    @Override
    public void delete(JmsDTO jmsDTO) {
        jmsDAO.delete(Converter.convert(jmsDTO));
    }

    @Override
    public void deleteIdList(List<Long> idList) {
        jmsDAO.deleteIdList(idList);
    }

    @Override
    public List<JmsDTO> quickSearch(String quickSearchPhrase) {
        List<Jms> jmsList = jmsDAO.quickSearch(quickSearchPhrase);
        List<JmsDTO> jmsDTOs = new ArrayList<>();
        for (Jms jms :jmsList) {
            jmsDTOs.add(Converter.convert(jms));
        }
        return jmsDTOs;
    }
}
