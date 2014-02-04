package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class EmailBean implements Serializable {

    @Inject
    private GenericDAO dao;

    @PostConstruct
    public void init() {
        dao.saveEmail(new EmailMessageDTO("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol1","rew1","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol1","rew1","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol1","rew1","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
    }



    public List<EmailMessageDTO> getAllEmails(){
        return dao.getAllEmails();
    }

}
