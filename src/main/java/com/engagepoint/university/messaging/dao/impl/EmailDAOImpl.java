package com.engagepoint.university.messaging.dao.impl;

import com.engagepoint.university.messaging.dao.EmailDAO;
import com.engagepoint.university.messaging.entity.EmailMessageEntity;

import java.util.List;

public class EmailDAOImpl extends AbstractGenericDAOImpl<EmailMessageEntity> implements EmailDAO {
    @Override
    public List<EmailMessageEntity> getEmailsBySender(String sender) {
        getEm().getTransaction().begin();
        List<EmailMessageEntity> emails = getEm().createNamedQuery(EmailMessageEntity.GET_EMAIL_BY_SENDER, EmailMessageEntity.class).setParameter("sender", sender).getResultList();
        getEm().getTransaction().commit();
        return emails;
    }

    @Override
    public List<EmailMessageEntity> getEmailByReceiver(String receiver) {
        getEm().getTransaction().begin();
        List<EmailMessageEntity> emails = getEm().createNamedQuery(EmailMessageEntity.GET_EMAIL_BY_RECEIVER, EmailMessageEntity.class).setParameter("receiver", receiver).getResultList();
        getEm().getTransaction().commit();
        return emails;
    }
}
