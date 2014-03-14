package com.engagepoint.university.messaging.dao.repository;
import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.Sms;

import java.util.List;

public interface SmsDAO extends GenericDAO<Sms> {
    public List<Sms> getSmsBySender(String sender);
    public void saveSmsDAO(Sms sms);
    public void deleteIdList(List<Long> idList);
    public void smsQuickSearch();
    public List<Sms> getSmsAllByQuery();
}
