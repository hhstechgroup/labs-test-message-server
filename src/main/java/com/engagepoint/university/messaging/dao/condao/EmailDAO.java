package com.engagepoint.university.messaging.dao.condao;
import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.Email;

import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public interface EmailDAO extends GenericDAO<Email> {
    public List<Email> getEmailsBySender(String sender);
    public List<Email> getEmailsBySubject(String subject);
}
