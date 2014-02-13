package com.engagepoint.university.messaging.dao.condao.impl;

/*
 * #%L
 * labs-test-message-server
 * %%
 * Copyright (C) 2012 - 2014 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.engagepoint.university.messaging.dao.condao.SmsDAO;
import com.engagepoint.university.messaging.dao.generic.impl.GenericDAOImpl;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class SmsDAOImpl extends GenericDAOImpl<Sms> implements SmsDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SmsDAOImpl.class);

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

    public void saveSmsDAO(Sms sms){    //Viktor - add
       save(sms);     //use generic
    }

}
