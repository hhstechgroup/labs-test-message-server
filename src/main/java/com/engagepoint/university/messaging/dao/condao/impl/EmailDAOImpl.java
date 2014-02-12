package com.engagepoint.university.messaging.dao.condao.impl;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.generic.impl.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class EmailDAOImpl extends GenericDAOImpl<Email> implements EmailDAO {
    private static final Logger LOG = LoggerFactory.getLogger(EmailDAOImpl.class);

    public EmailDAOImpl() {
        super(Email.class);
    }

    @Override
    public List<Email> getEmailsBySender(String sender) {
        getEntityManager().getTransaction().begin();
        List<Email> emails = getEntityManager()
                .createNamedQuery(Email.GET_ALL_BY_SENDER, Email.class)
                .setParameter("sender", sender).getResultList();
        getEntityManager().getTransaction().commit();
        return emails;
    }

    @Override
    public List<Email> getEmailsBySubject(String subject) {
        getEntityManager().getTransaction().begin();
        List<Email> emails = getEntityManager()
                .createNamedQuery(Email.GET_ALL_BY_SUBJECT, Email.class)
                .setParameter("subject", subject).getResultList();
        getEntityManager().getTransaction().commit();
        return emails;
    }

}
