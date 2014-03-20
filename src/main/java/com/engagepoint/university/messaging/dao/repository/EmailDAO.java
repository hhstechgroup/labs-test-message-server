package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.Email;

import java.util.List;

public interface EmailDAO extends GenericDAO<Email> {
    public List<Email> getEmailsBySender(String sender);
    public List<Email> getEmailsBySubject(String subject);
    public List<Email> getEmailsSortByDeliverDate();
    public void deleteIdList(List<Long> idList);
    public List<Email> quickSearch(String quickSearchPhrase);
    public List<Email> emailsSearch(String searchPhrase);
}
