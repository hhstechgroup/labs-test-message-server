package com.engagepoint.university.messaging.service.repository;

import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.GenericService;

import java.util.List;

public interface JmsService extends GenericService<JmsDTO> {
    public void deleteIdList(List<Long> idList);
    public List<JmsDTO> quickSearch(String quickSearchPhrase);
}