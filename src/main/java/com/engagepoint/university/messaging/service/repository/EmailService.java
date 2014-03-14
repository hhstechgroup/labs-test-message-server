package com.engagepoint.university.messaging.service.repository;


import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.service.GenericService;

import java.util.List;

public interface EmailService extends GenericService<EmailDTO> {
    public List<EmailDTO> getEmailsBySender(String sender);
    public List<EmailDTO> getEmailsBySubject(String subject);
    public List<EmailDTO> getEmailsSortByDeliverDate();
    public void deleteIdList(List<Long> idList);
    public List<EmailDTO> quickSearch(String quickSearchPhrase);
}
