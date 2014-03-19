package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.JmsDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataJmsDAO;
import com.engagepoint.university.messaging.entity.Jms;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service("jmsDAO")
public class JmsDAOImpl implements JmsDAO {

    @Inject
    private SpringDataJmsDAO springDataJmsDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteIdList(List<Long> idList) {
        if (idList != null) {
            for (Long id : idList) {
                Jms jms = springDataJmsDAO.findOne(id);
                if (jms != null) {
                    springDataJmsDAO.delete(jms);
                }
            }
        }
    }

    @Override
    @Transactional
    public List<Jms> quickSearch(String quickSearchPhrase) {
        List<Jms> emails = jmsesSearch(quickSearchPhrase);
        return emails;
    }

    @Override
    @Transactional
    public List<Jms> jmsesSearch(String searchPhrase) {
        List<Jms> emails = entityManager
                .createNamedQuery(Jms.GET_JMS_QUICK_SEARCH, Jms.class)
                .setParameter("sendDate", "%" +searchPhrase+ "%")
                .setParameter("body", "%" +searchPhrase+ "%")
                .getResultList();
        return emails;
    }

    @Override
    @Transactional
    public Jms getById(Long id) {
        Jms jms = springDataJmsDAO.findOne(id);
        return jms;
    }

    @Override
    @Transactional
    public List<Jms> getAll() {
        List<Jms> jmsList = springDataJmsDAO.findAll();
        return jmsList;
    }

    @Override
    @Transactional
    public void save(Jms jms) {
        springDataJmsDAO.saveAndFlush(jms);
    }

    @Override
    @Transactional
    public void update(Jms jms) {
        springDataJmsDAO.save(jms);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        springDataJmsDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(Jms jms) {
        springDataJmsDAO.delete(jms);
    }
}
