package com.engagepoint.university.messaging.dao.impl;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class EmailDAOImpl implements GenericDAO {

    @Inject
    private EntityManager em;

    @Override
    public EmailMessageDTO getEmail(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<EmailMessageDTO> getEmailByTo(String to) {
        Query query = em.createQuery(EmailMessageDTO.LIST_ALL);
        return query.getResultList();
    }

    @Override
    public List<EmailMessageDTO> getEmailByFrom(String from) {
        return null;
    }

    @Override
    public List<EmailMessageDTO> getAllEmails() {
        return null;
    }

    @Override
    @Transactional
    public void saveEmail(EmailMessageDTO email) {
        em.persist(email);
    }

    @Override
    public void deleteEmail(int id) {

    }
}
