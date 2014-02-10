package com.engagepoint.university.messaging.dao.condao;

import com.engagepoint.university.messaging.dao.generic.GenericDAO;
import com.engagepoint.university.messaging.entities.Sms;

import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
public interface SmsDAO extends GenericDAO<Sms>{
    public List<Sms> getSmsBySender(String sender);
}
