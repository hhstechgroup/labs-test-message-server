package com.engagepoint.university.messaging.dao.specific;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;

import java.util.List;

public interface EmailDAO extends GenericDAO<EmailDTO> {
    public List<EmailDTO> getEmailsBySender(String sender);
    public List<EmailDTO> getEmailsBySubject(String subject);
    public List<EmailDTO> getEmailsSortByDeliverDate();
}
