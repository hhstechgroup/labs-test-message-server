package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;

import java.util.List;

public interface EmailDAO extends GenericDAO<EmailDTO> {
    public List<EmailDTO> getEmailsBySender(String sender);
    public List<EmailDTO> getEmailsBySubject(String subject);
    public List<EmailDTO> getEmailsSortByDeliverDate();
    public void deleteIdList(List<Long> idList);
    public List<EmailDTO> fullEmailQuickSearchByAttachment(String criteria);
    public List<EmailDTO> partialEmailQuickSearchByAttachment(String criteria);
    public List<EmailDTO> fullEmailQuickSearchByEmails(String criteria);
    public List<EmailDTO> partialEmailQuickSearchByEmails(String criteria);
    public List<EmailDTO> emailQuickSearch(String criteria);
}
