package com.engagepoint.university.messaging.dao.specific.impl;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.util.Converter;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmailDAOImpl implements EmailDAO {

    @Override
    public EmailDTO getById(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = EntityManagerUtil.getEntityManager().find(Email.class, id);
        EmailDTO emailDTO = new Converter().convert(email);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTO;
    }

    @Override
    public List<EmailDTO> getAll() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> attachments = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<EmailDTO>();
        Iterator<Email> emailIterator = attachments.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(new Converter().convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public void save(EmailDTO emailDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = new Converter().convert(emailDTO);
        if (!EntityManagerUtil.getEntityManager().contains(email)) {
            EntityManagerUtil.getEntityManager().merge(email);
        } else {
            EntityManagerUtil.getEntityManager().persist(email);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(EmailDTO emailDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = new Converter().convert(emailDTO);
        EntityManagerUtil.getEntityManager().merge(email);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = EntityManagerUtil.getEntityManager().find(Email.class, id);
        if (email != null) {
            EntityManagerUtil.getEntityManager().detach(email);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(EmailDTO emailDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = new Converter().convert(emailDTO);
        EntityManagerUtil.getEntityManager().detach(email);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public List<EmailDTO> getEmailsBySender(String sender) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> emails = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_BY_SENDER, Email.class)
                .setParameter("sender", sender).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<EmailDTO>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(new Converter().convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public List<EmailDTO> getEmailsBySubject(String subject) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> emails = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_BY_SUBJECT, Email.class)
                .setParameter("subject", subject).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<EmailDTO>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(new Converter().convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public List<EmailDTO> getEmailsSortByDeliverDate() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> emails = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<EmailDTO>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(new Converter().convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public void deleteIdList(List<Long> idList) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        EntityManagerUtil.getEntityManager().createNamedQuery(Email.DELETE_EMAILS_LIST).setParameter(Email.PARAM_IDS_LIST,idList).executeUpdate();
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }
}
