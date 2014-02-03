package com.engagepoint.university.messaging.dao;

import com.engagepoint.university.messaging.dto.EmailMessageDTO;

import java.util.List;

public interface GenericDAO {
    public EmailMessageDTO getEmail(int id);

    public List<EmailMessageDTO> getEmailBySender(String to);

    public List<EmailMessageDTO> getEmailByReceiver(String from);

    public List<EmailMessageDTO> getAllEmails();

    public void saveEmail(EmailMessageDTO email);

    public void deleteEmail(int id);
}
