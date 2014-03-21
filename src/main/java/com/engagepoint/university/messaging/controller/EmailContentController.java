package com.engagepoint.university.messaging.controller;


import com.engagepoint.university.messaging.dto.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EmailContentController implements Serializable {

    private EmailDTO currentEmail;

    public EmailDTO getCurrentEmail()
    {
        return currentEmail;
    }

    public void setCurrentEmail(EmailDTO currentEmail) {
        this.currentEmail = currentEmail;
    }
}
