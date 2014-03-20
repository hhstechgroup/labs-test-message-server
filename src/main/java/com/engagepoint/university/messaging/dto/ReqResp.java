package com.engagepoint.university.messaging.dto;

public class ReqResp {
    private String url;
    private String request;
    private String response;

    public ReqResp(String url, String request, String response) {
        this.url = url;
        this.request = request;
        this.response = response;
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
}
