package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.repository.JmsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jmsService")
public class JmsServiceImpl implements JmsService {
    @Override
    public JmsDTO getById(Long id) {
        return null;
    }

    @Override
    public List<JmsDTO> getAll() {
        return null;
    }

    @Override
    public void save(JmsDTO DTOType) {

    }

    @Override
    public void update(JmsDTO DTOType) {

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
