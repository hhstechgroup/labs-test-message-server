package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.dao.EmailDAO;
import com.engagepoint.university.messaging.entity.EmailMessageEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class EmailController implements Serializable {

    @Inject
    private EmailDAO dao;

    @PostConstruct
    public void init() {
        dao.save(new EmailMessageEntity("Vitalia","Matwiichuk","test","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("lol2", "rew2", "qwerty", "sdfghjkllkjh5+64", "url", new Date(12345678L)));
    }



    public List<EmailMessageEntity> getAllEmails(){
        return dao.getAll();
    }

}
