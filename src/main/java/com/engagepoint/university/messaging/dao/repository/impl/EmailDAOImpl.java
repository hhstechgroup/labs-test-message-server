package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataEmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entity.Email;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
@Service("emailDAO")
public class EmailDAOImpl implements EmailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataEmailDAO springDataEmailDAO;

    @Override
    @Transactional
    public Email getById(Long id) {
        Email email = springDataEmailDAO.findOne(id);

        return email;
    }

    @Override
    @Transactional
    public List<Email> getAll() {
        List<Email> emails = springDataEmailDAO.findAll();
        return emails;
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
                .setParameter("sender", "%" + sender + "%").getResultList();
        return emails;
    }

    @Override
    @Transactional
    public List<Email> getEmailsBySubject(String subject) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SUBJECT, Email.class)
                .setParameter("subject", subject).getResultList();
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
    public List<Email> quickSearch(String quickSearchPhrase) {
        List<Email> emails = searchWithAttachments(quickSearchPhrase);
        List<Email> emailsWithoutAttachments = searchWithoutAttachments(quickSearchPhrase);
        if (emails.isEmpty()) {
            emails.addAll(emailsWithoutAttachments);
        } else {
            for (Email email : emails) {
                for (Email emailWithoutAttachment : emailsWithoutAttachments) {
                    if (emailWithoutAttachment.getId() != email.getId()) {
                        emails.add(emailWithoutAttachment);
                    }
                }
            }
        }
        return emails;
    }

    @Override
    public List<Email> searchWithoutAttachments(String searchPhrase) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_EMAIL_QUICK_SEARCH_WITHOUT_ATTACHMENTS, Email.class)
                .setParameter("sender", "%" + searchPhrase + "%")
                .setParameter("subject", "%" + searchPhrase + "%")
                .setParameter("body", "%" + searchPhrase + "%")
                .getResultList();
        return emails;
    }

    @Override
    public List<Email> searchWithAttachments(String searchPhrase) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_EMAIL_QUICK_SEARCH, Email.class)
                .setParameter("attachName", "%" + searchPhrase + "%")
                .setParameter("sender", "%" + searchPhrase + "%")
                .setParameter("subject", "%" + searchPhrase + "%")
                .setParameter("body", "%" + searchPhrase + "%")
                .getResultList();
        return emails;
    }
}
