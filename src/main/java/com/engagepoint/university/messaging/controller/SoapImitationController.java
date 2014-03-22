package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.dto.ReqRespDTO;
import com.engagepoint.university.messaging.service.repository.SoapImitateService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class SoapImitationController implements Serializable {

    private static Map<String, Map<String, String>> matchingReqResp = new HashMap<>();
    @Inject
    private SoapImitateService soapImitateService;
    private String url;
    private String request;
    private String response;

    public static Map<String, Map<String, String>> getStaticMatchingReqResp() {
        return matchingReqResp;
    }

    public Map<String, Map<String, String>> getMatchingReqResp() {
        return matchingReqResp;
    }

    public void setMatchingReqResp(Map<String, Map<String, String>> matchingReqResp) {
        this.matchingReqResp = matchingReqResp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public void addMatching() {
        Map<String, String> reqResps = matchingReqResp.get(url);
        if (reqResps == null) {
            reqResps = new HashMap<>();
        }
        reqResps.put(request, response);
        matchingReqResp.put(url, reqResps);
    }

    public List<ReqRespDTO> getAllMatching() {
        List<ReqRespDTO> reqRespDTOs = new ArrayList<>();
        for (final Map.Entry<String, Map<String, String>> entry : matchingReqResp.entrySet()) {
            String url = entry.getKey();
            for (final Map.Entry<String, String> entryReqResp : entry.getValue().entrySet()) {
                ReqRespDTO reqRespDTO = new ReqRespDTO(url, entryReqResp.getKey(), entryReqResp.getValue());
                reqRespDTOs.add(reqRespDTO);
            }
        }
        return reqRespDTOs;
    }

    public List<ReqRespDTO> getAllReqResps() {
        return soapImitateService.getAll();
    }
}
