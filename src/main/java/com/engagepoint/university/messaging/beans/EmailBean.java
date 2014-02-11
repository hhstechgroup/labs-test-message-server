package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.entities.Email;
import org.primefaces.component.api.UIColumn;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexey on 2/10/14.
 */

@Named
@ViewScoped
public class EmailBean implements Serializable {

    @Inject
    private EmailDAO emailDAO;

    private List<Email> emailList;

    public List<SortMeta> getPreSortOrder() {
        return preSortOrder;
    }

    private List<SortMeta> preSortOrder;

    public void setEmailList(List<Email> emails) {

        this.emailList = emails;
    }

    public List<Email> getEmailList(){
        if (emailList ==null)
                return emailDAO.getAll();

        return emailList;
    }

    private void buildSortOrder() {
        UIViewRoot viewRoot =  FacesContext.getCurrentInstance().getViewRoot();
        UIComponent column = viewRoot.findComponent("table:sender");

        SortMeta sm1 = new SortMeta();
        sm1.setSortBy((UIColumn)column);
        sm1.setSortField("sender");
        sm1.setSortOrder(SortOrder.DESCENDING);
        preSortOrder.add(sm1);
    }


    public void setPreSortOrder(List<SortMeta> preSortOrder) {
        this.preSortOrder = preSortOrder;
    }

    public void delete() {
        emailDAO.delete(3);
    }
}
