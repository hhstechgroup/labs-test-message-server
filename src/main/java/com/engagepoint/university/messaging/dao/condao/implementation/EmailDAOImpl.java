package com.engagepoint.university.messaging.dao.condao.implementation;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.generic.implementation.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
@RequestScoped
public class EmailDAOImpl extends GenericDAOImpl<Email> implements EmailDAO {

    public EmailDAOImpl() {
        super(Email.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager();
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
