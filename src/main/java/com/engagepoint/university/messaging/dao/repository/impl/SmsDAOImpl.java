package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SmsDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataSmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service("smsDAO")
public class SmsDAOImpl implements SmsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataSmsDAO springDataSmsDAO;

    @Override
    @Transactional
    public SmsDTO getById(Long id) {
        Sms sms = springDataSmsDAO.findOne(id);
        SmsDTO smsDTO = Converter.convert(sms);
        return smsDTO;
    }

    @Override
    @Transactional
    public List<SmsDTO> getAll() {
        List<Sms> smses = springDataSmsDAO.findAll();
        List<SmsDTO> smsDTOs = new ArrayList<>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(Converter.convert(smsIterator.next()));
        }
        return smsDTOs;
    }

    @Override
    @Transactional
    public void save(SmsDTO smsDTO) {
        Sms sms = Converter.convert(smsDTO);
        springDataSmsDAO.save(sms);
    }

    @Override
    @Transactional
    public void update(SmsDTO smsDTO) {
        Sms sms = Converter.convert(smsDTO);
        springDataSmsDAO.save(sms);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        springDataSmsDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(SmsDTO smsDTO) {
        Sms sms = Converter.convert(smsDTO);
        springDataSmsDAO.delete(sms);
    }

    @Override
    @Transactional
    public List<SmsDTO> getSmsBySender(String sender) {
        List<Sms> smses = entityManager
                .createNamedQuery(Sms.GET_ALL_BY_SENDER, Sms.class)
                .setParameter("sender", "%" + sender + "%").getResultList();
        List<SmsDTO> smsDTOs = new ArrayList<>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(Converter.convert(smsIterator.next()));
        }
        return smsDTOs;
    }

    @Override
    @Transactional
    public List<SmsDTO> getSmsAllByQuery() {
        List<Sms> smses = entityManager
                .createNamedQuery(Sms.GET_ALL_SMS, Sms.class)
                .getResultList();
        List<SmsDTO> smsDTOs = new ArrayList<>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(Converter.convert(smsIterator.next()));
        }
        return smsDTOs;
    }

    @Override
    @Transactional
    public void saveSmsDAO(SmsDTO smsDTO) {

    }

    @Override
    @Transactional
    public void deleteIdList(List<Long> idList) {
        if (idList != null) {
            for (Long id : idList) {
                Sms sms = springDataSmsDAO.findOne(id);
                if (sms != null) {
                    springDataSmsDAO.delete(sms);
                }
            }
        }
    }

    @Override
    @Transactional
    public void smsQuickSearch() {

    }
}
