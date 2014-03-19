package com.engagepoint.university.messaging.dao.repository;
import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.Sms;

import java.util.List;

public interface SmsDAO extends GenericDAO<Sms> {
    public List<Sms> getSmsBySender(String sender);
    public void deleteIdList(List<Long> idList);
    public List<Sms> getSmsAllByQuery();
    public List<Sms> quickSearch(String quickSearchPhrase);
    public List<Sms> smsSearch(String searchPhrase);
}
