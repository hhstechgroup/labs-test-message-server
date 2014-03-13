package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.Date;

/**
 * Created by User on 13.03.14.
 */
public class WebServiceDTO extends BaseDTO {

    private Long id;
    private String sender;
    private String body;
    private Date sendDate;
    private Boolean flag = Boolean.FALSE;

    public WebServiceDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}