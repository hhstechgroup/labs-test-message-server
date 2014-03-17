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

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataJmsDAO springDataJmsDAO;

    @Override
    @Transactional
    public List<Jms> getJmsBySender(String sender) {
        return null;
    }

    @Override
    @Transactional
    public void saveJms(Jms jms) {

    }

    @Override
    @Transactional
    public void deleteIdList(List<Long> idList) {

    }

    @Override
    @Transactional
    public List<Jms> quickSearch(String quickSearchPhrase) {
        return null;
    }

    @Override
    @Transactional
    public Jms getById(Long id) {
        return null;
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
    public void update(Jms EntityType) {

    }

    @Override
    @Transactional
    public void delete(Long id) {

    }

    @Override
    @Transactional
    public void delete(Jms EntityType) {

    }
}
