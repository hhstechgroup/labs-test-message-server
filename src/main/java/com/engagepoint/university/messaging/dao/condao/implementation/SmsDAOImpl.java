package com.engagepoint.university.messaging.dao.condao.implementation;

import com.engagepoint.university.messaging.dao.condao.SmsDAO;
import com.engagepoint.university.messaging.dao.generic.implementation.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexey on 2/9/14.
 */
@RequestScoped
public class SmsDAOImpl extends GenericDAOImpl<Sms> implements SmsDAO {

    public SmsDAOImpl() {
        super(Sms.class);
    }

    @Override
    public List<Sms> getSmsBySender(String sender) {
        getEntityManager().getTransaction().begin();
        List<Sms> smses = getEntityManager()
                .createNamedQuery(Sms.GET_ALL_BY_SENDER, Sms.class)
                .setParameter("sender", sender).getResultList();
        getEntityManager().getTransaction().commit();
        return smses;
    }

    @Override
    protected EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager();
    }
}
