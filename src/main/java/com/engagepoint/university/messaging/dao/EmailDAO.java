package com.engagepoint.university.messaging.dao;

import com.engagepoint.university.messaging.entity.EmailMessageEntity;

import java.util.List;

public interface EmailDAO extends GenericDAO<EmailMessageEntity> {
    public List<EmailMessageEntity> getEmailsBySender(String sender);
    public List<EmailMessageEntity> getEmailByReceiver(String receiver);
}
