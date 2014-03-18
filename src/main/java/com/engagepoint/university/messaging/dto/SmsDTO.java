package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.Date;

public class SmsDTO extends BaseDTO {

    private String sender;
    private String body;
    private Date sendDate;
    private Date deliveryDate;
    private Boolean flag = Boolean.FALSE;
    private String recipient;

    public SmsDTO(){

    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    public int getBodyLength(){
        return body.length();
    }

    public String getShortBody() {
        if(body.length()<10)
            return body;
        return body.substring(1, 10);
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return  "\n\nSMS id: " + this.getId() +
                "\nSender: " + sender + '\'' +
                "\nMessage: " + body + '\'' +
                "\nSend date: " + sendDate +
                "\nDelivery date: " + deliveryDate;
    }
}
