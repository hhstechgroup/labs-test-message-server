package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.condao.SmsDAO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.entities.Sms;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by artur.kandur on 11.02.14.
 */
@Named
@ViewScoped
public class SmsBean implements Serializable{
    private static final Logger LOG = LoggerFactory.getLogger(SmsBean.class);

    @Inject
    private SmsDAO smsDAO;

    private List<Sms> smsList;

    public List<SortMeta> getPreSortOrder() {
        return preSortOrder;
    }

    private List<SortMeta> preSortOrder;

    public void setSmsList(List<Sms> sms) {
        this.smsList = sms;
    }

    public List<Sms> getSmsList(){
        if (smsList ==null)
            return smsDAO.getAll();

        return smsList;
    }
}
