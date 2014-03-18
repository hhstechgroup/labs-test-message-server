package com.engagepoint.university.messaging.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
public class SoapImitationController implements Serializable {

    private Map<String, String> matchingReqResp = new HashMap<>();
    private String request;
    private String response;

    public Map<String, String> getMatchingReqResp() {
        return matchingReqResp;
    }

    public void setMatchingReqResp(Map<String, String> matchingReqResp) {
        this.matchingReqResp = matchingReqResp;
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
        matchingReqResp.put(request, response);
    }
}
