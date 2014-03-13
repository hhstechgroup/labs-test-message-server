package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SpringDataWebServiceDAO;
import com.engagepoint.university.messaging.dao.repository.WebServiceDAO;
import com.engagepoint.university.messaging.dto.WebServiceDTO;
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
    public void saveWebServiceDAO(WebServiceDTO webServiceDTO) {

    }

    @Override
    public List<WebServiceDTO> getWebServiceBySender(String sender) {
        return null;
    }

    @Override
    public WebServiceDTO getById(Long id) {
        return null;
    }

    @Override
    public List<WebServiceDTO> getAll() {
        return null;
    }

    @Override
    public void save(WebServiceDTO DTOType) {

    }

    @Override
    public void update(WebServiceDTO DTOType) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void delete(WebServiceDTO DTOType) {

    }
}
