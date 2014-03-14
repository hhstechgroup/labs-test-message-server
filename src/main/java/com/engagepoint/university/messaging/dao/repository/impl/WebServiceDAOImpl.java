package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SpringDataWebServiceDAO;
import com.engagepoint.university.messaging.dao.repository.WebServiceDAO;
import com.engagepoint.university.messaging.dto.WebServiceDTO;
import com.engagepoint.university.messaging.entity.WebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("WebServiceDAO")
public class WebServiceDAOImpl implements WebServiceDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SpringDataWebServiceDAO springDataWebServiceDAO;



    @Override
    public void deleteIdList(List<Long> idList) {

    }

    @Override
    public void saveWebServiceDAO(WebService webService) {

    }

    @Override
    public List<WebService> getWebServiceBySender(String sender) {
        return null;
    }

    @Override
    public WebService getById(Long id) {
        return null;
    }

    @Override
    public List<WebService> getAll() {
        return null;
    }

    @Override
    public void save(WebService EntityType) {

    }

    @Override
    public void update(WebService EntityType) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(WebService EntityType) {

    }
}
