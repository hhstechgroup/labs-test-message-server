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

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataSmsDAO springDataSmsDAO;

    @Override
    @Transactional
    public Sms getById(Long id) {
        Sms sms = springDataSmsDAO.findOne(id);

        return sms;
    }

    @Override
    @Transactional
    public List<Sms> getAll() {
        List<Sms> smses = springDataSmsDAO.findAll();
        return smses;
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
        List<Sms> smses = entityManager
                .createNamedQuery(Sms.GET_ALL_BY_SENDER, Sms.class)
                .setParameter("sender", "%" + sender + "%").getResultList();
        return smses;
    }

    @Override
    @Transactional
    public List<Sms> getSmsAllByQuery() {
        List<Sms> smses = entityManager
                .createNamedQuery(Sms.GET_ALL_SMS, Sms.class)
                .getResultList();
        return smses;
    }

    @Override
    public List<Sms> quickSearch(String quickSearchPhrase) {
        List<Sms> smses = entityManager
                .createNamedQuery(Sms.GET_SMS_QUICK_SEARCH, Sms.class)
                .setParameter("userName", "%" + quickSearchPhrase + "%")
                .setParameter("sender", "%" + quickSearchPhrase + "%")
                .setParameter("body", "%" + quickSearchPhrase + "%" )
                .getResultList();
        smses.addAll(entityManager
                .createNamedQuery(Sms.GET_SMS_QUICK_SEARCH_WITHOUT_USERS, Sms.class)
                .setParameter("sender", "%" + quickSearchPhrase + "%")
                .setParameter("body", "%" + quickSearchPhrase + "%")
                .getResultList());
        return smses;
    }

    @Override
    @Transactional
    public void saveSmsDAO(Sms sms) {

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

    @Override
    @Transactional
    public void smsQuickSearch() {

    }
}
