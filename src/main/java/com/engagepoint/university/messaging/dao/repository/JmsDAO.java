package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.entity.Jms;

import java.util.List;

public interface JmsDAO extends GenericDAO<Jms> {
    public void deleteIdList(List<Long> idList);
    public List<Jms> quickSearch(String quickSearchPhrase);
    public List<Jms> jmsesSearch(String searchPhrase);
}
