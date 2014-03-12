package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataEmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service("emailDAO")
public class EmailDAOImpl implements EmailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataEmailDAO springDataEmailDAO;

    @Override
    @Transactional
    public EmailDTO getById(Long id) {
        Email email = springDataEmailDAO.findOne(id);
        EmailDTO emailDTO = Converter.convert(email);
        return emailDTO;
    }

    @Override
    @Transactional
    public List<EmailDTO> getAll() {
        List<Email> attachments = springDataEmailDAO.findAll();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = attachments.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public void save(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        springDataEmailDAO.saveAndFlush(email);

    }

    @Override
    @Transactional
    public void update(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        springDataEmailDAO.save(email);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        springDataEmailDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        springDataEmailDAO.delete(email);
    }

    @Override
    @Transactional
    public List<EmailDTO> getEmailsBySender(String sender) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SENDER, Email.class)
                .setParameter("sender", "%" + sender + "%").getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> getEmailsBySubject(String subject) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SUBJECT, Email.class)
                .setParameter("subject", subject).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> getEmailsSortByDeliverDate() {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
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
    public List<EmailDTO> fullEmailQuickSearchByAttachment(String criteria) {
        String[] criterias = criteria.trim().split("\\s");
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_FULL_EMAIL_QUICK_SEARCH_BY_ATTACHMENTS, Email.class)
                .setParameter("searchCriteria", criterias)
                .setParameter("attachmentCount", criterias.length)
                .getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> partialEmailQuickSearchByAttachment(String criteria) {
        String[] criterias = criteria.trim().split("\\s");
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_PARTIAL_EMAIL_QUICK_SEARCH_BY_ATTACHMENTS, Email.class)
                .setParameter("searchCriteria", criterias)
                .setParameter("attachmentCount", criterias.length)
                .getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> fullEmailQuickSearchByEmails(String criteria) {
        String[] criterias = criteria.trim().split("\\s");
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_FULL_EMAIL_QUICK_SEARCH_BY_EMAILS, Email.class)
                .setParameter("searchCriteria", criterias)
                .setParameter("attachmentCount", criterias.length)
                .getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> partialEmailQuickSearchByEmails(String criteria) {
        String[] criterias = criteria.trim().split("\\s");
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_PARTIAL_EMAIL_QUICK_SEARCH_BY_EMAILS, Email.class)
                .setParameter("searchCriteria", criterias)
                .setParameter("attachmentCount", criterias.length)
                .getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> emailQuickSearch(String criteria) {
        String[] criterias = criteria.trim().split("\\s");
        List<EmailDTO> emailDTOs = new ArrayList<>();
        emailDTOs.addAll(this.fullEmailQuickSearchByAttachment(criteria));
//        emailDTOs.addAll(this.partialEmailQuickSearchByAttachment(criteria));
//        emailDTOs.addAll(this.fullEmailQuickSearchByEmails(criteria));
//        emailDTOs.addAll(this.partialEmailQuickSearchByEmails(criteria));
        return emailDTOs;
    }
}
