package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.Jms;

import java.util.List;

public interface JmsDAO extends GenericDAO<Jms> {
    public List<Jms> getJmsBySender(String sender);
    public void saveJms(Jms jms);
    public void deleteIdList(List<Long> idList);
    public List<Jms> quickSearch(String quickSearchPhrase);

}
