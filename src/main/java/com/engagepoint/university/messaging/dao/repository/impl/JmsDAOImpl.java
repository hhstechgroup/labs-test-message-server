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
        return jmsesSearch(quickSearchPhrase);
    }

    @Override
    @Transactional
    public List<Jms> jmsesSearch(String searchPhrase) {
        List<Jms> jmses = entityManager
                .createNamedQuery(Jms.GET_JMS_QUICK_SEARCH, Jms.class)
                .setParameter("sendDate", "%" +searchPhrase+ "%")
                .setParameter("body", "%" +searchPhrase+ "%")
                .getResultList();
        return jmses;
    }

    @Override
    @Transactional
    public Jms getById(Long id) {
        return springDataJmsDAO.findOne(id);
    }

    @Override
    @Transactional
    public List<Jms> getAll() {
        return springDataJmsDAO.findAll();
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
