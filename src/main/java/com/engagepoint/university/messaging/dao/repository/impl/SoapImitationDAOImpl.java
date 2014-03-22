package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SoapImitationDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataSoapImitationDAO;
import com.engagepoint.university.messaging.entity.ReqResp;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SoapImitationDAOImpl implements SoapImitationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataSoapImitationDAO springDataSoapImitationDAO;

    @Override
    public ReqResp getById(Long id) {
        return springDataSoapImitationDAO.findOne(id);
    }

    @Override
    public List<ReqResp> getAll() {
        return springDataSoapImitationDAO.findAll();
    }

    @Override
    public void save(ReqResp reqResp) {
        springDataSoapImitationDAO.saveAndFlush(reqResp);
    }

    @Override
    public void update(ReqResp reqResp) {
        springDataSoapImitationDAO.save(reqResp);
    }

    @Override
    public void delete(Long id) {
        springDataSoapImitationDAO.delete(id);
    }

    @Override
    public void delete(ReqResp reqResp) {
        springDataSoapImitationDAO.delete(reqResp);
    }
}
