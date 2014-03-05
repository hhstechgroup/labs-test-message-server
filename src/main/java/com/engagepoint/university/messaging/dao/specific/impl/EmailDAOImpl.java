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
    public EmailDTO getById(Long id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = EntityManagerUtil.getEntityManager().find(Email.class, id);
        EmailDTO emailDTO = Converter.convert(email);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTO;
    }

    @Override
    public List<EmailDTO> getAll() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> attachments = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = attachments.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public void save(EmailDTO emailDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = Converter.convert(emailDTO);
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
        Email email = Converter.convert(emailDTO);
        EntityManagerUtil.getEntityManager().merge(email);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = EntityManagerUtil.getEntityManager().find(Email.class, id);
        if (email != null) {
            EntityManagerUtil.getEntityManager().remove(email);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(EmailDTO emailDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Email email = Converter.convert(emailDTO);
        EntityManagerUtil.getEntityManager().remove(email);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public List<EmailDTO> getEmailsBySender(String sender) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> emails = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_BY_SENDER, Email.class)
                .setParameter("sender", sender).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
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
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public List<EmailDTO> getEmailsSortByDeliverDate() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Email> emails = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return emailDTOs;
    }

    @Override
    public void deleteIdList(List<Long> idList) {
        if (idList != null) {
            EntityManagerUtil.getEntityManager().getTransaction().begin();
            for (Long id : idList) {
                Email email = EntityManagerUtil.getEntityManager().find(Email.class, id);
                if (email != null) {
                    EntityManagerUtil.getEntityManager().remove(email);
                }
            }
            EntityManagerUtil.getEntityManager().getTransaction().commit();
        }
    }

    public List<EmailDTO> search(String s){
        EntityManagerUtil.getEntityManager().getTransaction().begin();

        String[] SearchingWords =  s.trim().split("\\s+");

        List<Email> forReturn = EntityManagerUtil.getEntityManager().createNamedQuery(Email.GET_SEARCHING_EMAIL)
                .setParameter("attachName", "%"+s+"%")
                .setParameter("sender", "%" + s + "%")
                .setParameter("subject", "%"+s+"%" )
                .setParameter("body", "%"+s+"%" )
                .getResultList();
        List<EmailDTO> EmailDTOs = new ArrayList<>();
        Iterator<Email> EmailIterator = forReturn.iterator();
        while (EmailIterator.hasNext()) {
            EmailDTOs.add(Converter.convert(EmailIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return EmailDTOs;
    }
}
