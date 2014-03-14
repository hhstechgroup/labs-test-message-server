package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.WebServiceDTO;
import com.engagepoint.university.messaging.entity.WebService;

import java.util.List;

public interface WebServiceDAO extends GenericDAO<WebService> {
    public List<WebService> getWebServiceBySender(String sender);
    public void saveWebServiceDAO(WebService webService);
    public void deleteIdList(List<Long> idList);

}
