package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entity.Email;
import com.engagepoint.university.messaging.service.repository.EmailService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("emailService")
public class EmailServiceImpl implements EmailService{

    @Inject
    private EmailDAO emailDAO;


    @Override
    public List<EmailDTO> getEmailsBySender(String sender) {
        List<Email> emailList = emailDAO.getEmailsBySender(sender);
        List<EmailDTO> emailDTOs = new ArrayList<>();
        for (Email email :emailList) {
            emailDTOs.add(Converter.convert(email));
        }
        return emailDTOs;
    }

    @Override
    public List<EmailDTO> getEmailsBySubject(String subject) {
        List<Email> emailList = emailDAO.getEmailsBySubject(subject);
        List<EmailDTO> emailDTOs = new ArrayList<>();
        for (Email email :emailList) {
            emailDTOs.add(Converter.convert(email));
        }
        return emailDTOs;
    }

    @Override
    public List<EmailDTO> getEmailsSortByDeliverDate() {
        List<Email> emailList = emailDAO.getEmailsSortByDeliverDate();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        for (Email email :emailList) {
            emailDTOs.add(Converter.convert(email));
        }
        return emailDTOs;
    }

    @Override
    public void deleteIdList(List<Long> idList) {
        emailDAO.deleteIdList(idList);
    }

    @Override
    public List<EmailDTO> quickSearch(String quickSearchPhrase) {
        List<Email> emailList = emailDAO.quickSearch(quickSearchPhrase);
        List<EmailDTO> emailDTOs = new ArrayList<>();
        for (Email email :emailList) {
            emailDTOs.add(Converter.convert(email));
        }
        return emailDTOs;
    }

    @Override
    public EmailDTO getById(Long id) {
        Email email = emailDAO.getById(id);
        return Converter.convert(email);
    }

    @Override
    public List<EmailDTO> getAll() {
        List<Email> emailList = emailDAO.getAll();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        for (Email email :emailList) {
            emailDTOs.add(Converter.convert(email));
        }
        return emailDTOs;
    }

    @Override
    public void save(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        emailDAO.save(email);

    }

    @Override
    public void update(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        emailDAO.update(email);
    }

    @Override
    public void delete(Long id) {
        emailDAO.delete(id);
    }

    @Override
    public void delete(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        emailDAO.delete(email);
    }
}
