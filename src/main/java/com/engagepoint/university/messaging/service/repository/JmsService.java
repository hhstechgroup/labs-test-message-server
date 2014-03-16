package com.engagepoint.university.messaging.service.repository;

import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.GenericService;

import java.util.List;

public interface JmsService extends GenericService<JmsDTO> {
    public List<JmsDTO> getEmailsBySender(String sender);
    public List<JmsDTO> getEmailsBySubject(String subject);
    public List<JmsDTO> getEmailsSortByDeliverDate();
    public void deleteIdList(List<Long> idList);
    public List<JmsDTO> quickSearch(String quickSearchPhrase);
}