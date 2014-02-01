package com.engagepoint.university.messaging.dao.impl;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;

import java.util.List;

public class EmailDAOImpl implements GenericDAO {
    @Override
    public EmailMessageDTO getEmail(int id) {
        return null;
    }

    @Override
    public List<EmailMessageDTO> getEmailByTo(String to) {
        return null;
    }

    @Override
    public List<EmailMessageDTO> getEmailByFrom(String from) {
        return null;
    }

    @Override
    public List<EmailMessageDTO> getAllEmails() {
        return null;
    }

    @Override
    public void saveEmail(EmailMessageDTO email) {

    }

    @Override
    public void deleteEmail(int id) {

    }
}
