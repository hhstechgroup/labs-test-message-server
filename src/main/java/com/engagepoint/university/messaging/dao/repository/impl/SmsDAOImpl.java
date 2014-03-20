package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SmsDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataSmsDAO;
import com.engagepoint.university.messaging.entity.Sms;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Service("smsDAO")
public class SmsDAOImpl implements SmsDAO {
    private static final String SENDER = "sender";

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataSmsDAO springDataSmsDAO;

    @Override
    @Transactional
    public Sms getById(Long id) {
        return springDataSmsDAO.findOne(id);
    }

    @Override
    @Transactional
    public List<Sms> getAll() {
        return springDataSmsDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Sms sms) {
        springDataSmsDAO.save(sms);
    }

    @Override
    @Transactional
    public void update(Sms sms) {
        springDataSmsDAO.save(sms);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        springDataSmsDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(Sms sms) {
        springDataSmsDAO.delete(sms);
    }

    @Override
    @Transactional
    public List<Sms> getSmsBySender(String sender) {
        List<Sms> smsList = entityManager
                .createNamedQuery(Sms.GET_ALL_BY_SENDER, Sms.class)
                .setParameter(SENDER, "%" + sender + "%").getResultList();
        return smsList;
    }

    @Override
    @Transactional
    public List<Sms> getSmsAllByQuery() {
        List<Sms> smsList = entityManager
                .createNamedQuery(Sms.GET_ALL_SMS, Sms.class)
                .getResultList();
        return smsList;
    }

    @Override
    @Transactional
    public List<Sms> quickSearch(String quickSearchPhrase) {
        return smsSearch(quickSearchPhrase);
    }

    @Override
    @Transactional
    public List<Sms> smsSearch(String searchPhrase) {
        List<Sms> smsList = entityManager
                .createNamedQuery(Sms.GET_SMS_QUICK_SEARCH, Sms.class)
                .setParameter("userName", "%" + searchPhrase + "%")
                .setParameter(SENDER, "%" + searchPhrase + "%")
                .setParameter("recipient", "%" + searchPhrase + "%" )
                .setParameter("body", "%" + searchPhrase + "%" )
                .setParameter("deliveryDate", "%" + searchPhrase + "%" )
                .getResultList();
        return smsList;
    }

    @Override
    @Transactional
    public void deleteIdList(List<Long> idList) {
        if (idList != null) {
            for (Long id : idList) {
                Sms sms = springDataSmsDAO.findOne(id);
                if (sms != null) {
                    springDataSmsDAO.delete(sms);
                }
            }
        }
    }

}
