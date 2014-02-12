package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.entities.Email;
import org.primefaces.component.api.UIColumn;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EmailBean implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(EmailBean.class);

    @Inject
    private EmailDAO emailDAO;

    private List<Email> emailList;

    public void setEmailList(List<Email> emails) {

        this.emailList = emails;
    }

    public List<Email> getEmailList(){
        LOG.info("Get email list");
        if (emailList ==null)
                return emailDAO.getAll();

        return emailList;
    }


}
