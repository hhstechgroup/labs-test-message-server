package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataEmailDAO;
import com.engagepoint.university.messaging.entity.Email;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service("emailDAO")
public class EmailDAOImpl implements EmailDAO {
    private static final String SENDER = "sender";
    private static final String SUBJECT = "subject";

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataEmailDAO springDataEmailDAO;

    @Override
    @Transactional
    public Email getById(Long id) {
        return springDataEmailDAO.findOne(id);
    }

    @Override
    @Transactional
    public List<Email> getAll() {
        return springDataEmailDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Email email) {
        springDataEmailDAO.saveAndFlush(email);

    }

    @Override
    @Transactional
    public void update(Email email) {
        springDataEmailDAO.save(email);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        springDataEmailDAO.delete(id);
    }

    @Override
    @Transactional
    public void delete(Email email) {
        springDataEmailDAO.delete(email);
    }

    @Override
    @Transactional
    public List<Email> getEmailsBySender(String sender) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SENDER, Email.class)
                .setParameter(SENDER, "%" + sender + "%").getResultList();
        return emails;
    }

    @Override
    @Transactional
    public List<Email> getEmailsBySubject(String subject) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SUBJECT, Email.class)
                .setParameter(SUBJECT, subject).getResultList();
        return emails;
    }

    @Override
    @Transactional
    public List<Email> getEmailsSortByDeliverDate() {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        return emails;
    }

    @Override
    @Transactional
    public void deleteIdList(List<Long> idList) {
        if (idList != null) {
            for (Long id : idList) {
                Email email = springDataEmailDAO.findOne(id);
                if (email != null) {
                    springDataEmailDAO.delete(email);
                }
            }
        }
    }

    @Override
    @Transactional
    public List<Email> quickSearch(String quickSearchPhrase) {
        return emailsSearch(quickSearchPhrase);
    }

    @Override
    @Transactional
    public List<Email> emailsSearch(String searchPhrase) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_EMAIL_QUICK_SEARCH, Email.class)
                .setParameter("attachName", "%" +searchPhrase+ "%")
                .setParameter(SENDER, "%" +searchPhrase+ "%")
                .setParameter(SUBJECT, "%" +searchPhrase+ "%")
                .setParameter("body", "%" +searchPhrase+ "%")
                .setParameter("deliveryDate", "%" +searchPhrase+ "%")
                .getResultList();
        return emails;
    }
}
