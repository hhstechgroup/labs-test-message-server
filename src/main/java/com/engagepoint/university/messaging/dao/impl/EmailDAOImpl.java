package com.engagepoint.university.messaging.dao.impl;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;


import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.*;
import java.util.List;

@RequestScoped
public class EmailDAOImpl implements GenericDAO {

    // For server-side JPA realization.
    // @Resource(name="java:comp/env/UserTransaction")
    // UserTransaction userTransaction;

    // @PersistenceContext(unitName = "messaging", type = PersistenceContextType.EXTENDED)
    @PersistenceUnit(unitName = "messaging")
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public EmailDAOImpl() {
        emf = Persistence.createEntityManagerFactory("messaging");
        entityManager = emf.createEntityManager();
    }

    @Override
    @Transactional
    public EmailMessageDTO getEmail(int id) {
        //EntityManager entityManager = emf.createEntityManager();
        EmailMessageDTO email = null;
        entityManager.getTransaction().begin();
        try {
            email = entityManager.createNamedQuery(EmailMessageDTO.GET_EMAIL_BY_ID,EmailMessageDTO.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            email = null;
        }
        entityManager.getTransaction().commit();
        return email;
    }

    @Override
    @Transactional
    public List<EmailMessageDTO> getEmailBySender(String sender) {
        //EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        List<EmailMessageDTO> emails = entityManager.createNamedQuery(EmailMessageDTO.GET_EMAIL_BY_SENDER, EmailMessageDTO.class).setParameter("sender", sender).getResultList();
        entityManager.getTransaction().commit();
        return emails;
    }

    @Override
    @Transactional
    public List<EmailMessageDTO> getEmailByReceiver(String receiver) {
        //EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        List<EmailMessageDTO> emails = entityManager.createNamedQuery(EmailMessageDTO.GET_EMAIL_BY_RECEIVER, EmailMessageDTO.class).setParameter("receiver", receiver).getResultList();
        entityManager.getTransaction().commit();
        return emails;

    }

    @Override
    @Transactional
    public List<EmailMessageDTO> getAllEmails() {
        //EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        List<EmailMessageDTO> emails = entityManager.createNamedQuery(EmailMessageDTO.GET_ALL, EmailMessageDTO.class).getResultList();
        entityManager.getTransaction().commit();
        System.out.println(emails);
        return emails;
    }

    @Override
    @Transactional
    public void saveEmail(EmailMessageDTO email) {
        //EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(email);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void deleteEmail(int id) {
        //EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(EmailMessageDTO.class, id));
        entityManager.getTransaction().commit();
    }
}
