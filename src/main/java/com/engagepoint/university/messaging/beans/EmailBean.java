package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.entities.Email;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
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

    private List<Email> myList;


    public void setMyList(List<Email> emails) {

        this.myList = emails;
    }

    public List<Email> getMyList(){
        if (myList==null)
                return emailDAO.getAll();

        return myList;
    }


}
