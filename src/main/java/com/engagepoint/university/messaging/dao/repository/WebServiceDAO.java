package com.engagepoint.university.messaging.dao.repository;

import com.engagepoint.university.messaging.dao.GenericDAO;
import com.engagepoint.university.messaging.dto.WebServiceDTO;

import java.util.List;

/**
 * Created by User on 13.03.14.
 */
public interface WebServiceDAO extends GenericDAO<WebServiceDTO> {
    public List<WebServiceDTO> getWebServiceBySender(String sender);
    public void saveWebServiceDAO(WebServiceDTO webServiceDTO);
    public void deleteIdList(List<Long> idList);

}
