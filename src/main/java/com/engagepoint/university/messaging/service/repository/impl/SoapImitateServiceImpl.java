package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SoapImitationDAO;
import com.engagepoint.university.messaging.dto.ReqRespDTO;
import com.engagepoint.university.messaging.entity.ReqResp;
import com.engagepoint.university.messaging.service.repository.SoapImitateService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoapImitateServiceImpl implements SoapImitateService {

    @Inject
    private SoapImitationDAO soapImitationDAO;

    @Override
    public ReqRespDTO getById(Long id) {
        return Converter.convert(soapImitationDAO.getById(id));
    }

    @Override
    public List<ReqRespDTO> getAll() {
        List<ReqRespDTO> reqRespDTOs = new ArrayList<>();
        for (ReqResp reqResp : soapImitationDAO.getAll()) {
            reqRespDTOs.add(Converter.convert(reqResp));
        }
        return reqRespDTOs;
    }

    @Override
    public void save(ReqRespDTO reqRespDTO) {
        soapImitationDAO.save(Converter.convert(reqRespDTO));
    }

    @Override
    public void update(ReqRespDTO reqRespDTO) {
        soapImitationDAO.update(Converter.convert(reqRespDTO));
    }

    @Override
    public void delete(Long id) {
        soapImitationDAO.delete(id);
    }

    @Override
    public void delete(ReqRespDTO reqRespDTO) {
        soapImitationDAO.delete(Converter.convert(reqRespDTO));
    }
}
