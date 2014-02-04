package com.engagepoint.university.messaging.dao.impl;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;


import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@RequestScoped
public class EmailDAOImpl implements GenericDAO {

//    @PersistenceContext(unitName = "messaging", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

//    @Inject
//    private UserTransaction utx;
    public EmailDAOImpl() {
        em = Persistence.createEntityManagerFactory("messaging").createEntityManager();
    }

    @Override
//    @Transactional
    public EmailMessageDTO getEmail(int id) {
        em.getTransaction().begin();
        EmailMessageDTO email = null;
        try {
            email = em.createNamedQuery(EmailMessageDTO.GET_EMAIL_BY_ID,EmailMessageDTO.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            email = null;
        }
        em.getTransaction().commit();
        return email;
    }

    @Override
//    @Transactional
    public List<EmailMessageDTO> getEmailBySender(String sender) {
        em.getTransaction().begin();
        List<EmailMessageDTO> emails = em.createNamedQuery(EmailMessageDTO.GET_EMAIL_BY_SENDER, EmailMessageDTO.class).setParameter("sender", sender).getResultList();
        em.getTransaction().commit();
        return emails;

    }

    @Override
//    @Transactional
    public List<EmailMessageDTO> getEmailByReceiver(String receiver) {
        em.getTransaction().begin();
        List<EmailMessageDTO> emails = em.createNamedQuery(EmailMessageDTO.GET_EMAIL_BY_RECEIVER, EmailMessageDTO.class).setParameter("receiver", receiver).getResultList();
        em.getTransaction().commit();
        return emails;

    }

    @Override
//    @Transactional
    public List<EmailMessageDTO> getAllEmails() {
        em.getTransaction().begin();
        List<EmailMessageDTO> emails = em.createNamedQuery(EmailMessageDTO.GET_ALL, EmailMessageDTO.class).getResultList();
        em.getTransaction().commit();
        System.out.println(emails);
        return emails;
    }

    @Override
//    @Transactional
    public void saveEmail(EmailMessageDTO email) {
        em.getTransaction().begin();
        em.persist(email);
        em.getTransaction().commit();
    }

    @Override
//    @Transactional
    public void deleteEmail(int id) {
        em.getTransaction().begin();
        em.remove(em.find(EmailMessageDTO.class, id));
        em.getTransaction().commit();
    }
}
