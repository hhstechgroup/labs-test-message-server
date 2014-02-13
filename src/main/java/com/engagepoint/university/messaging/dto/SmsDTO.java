package com.engagepoint.university.messaging.dto;

import java.util.Date;

public class SmsDTO{

    private int id;
    private String sender;
    private String body;
    private Date sendDate;
    private Date deliveryDate;

    public SmsDTO(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsDTO smsDTO = (SmsDTO) o;

        if (body != null ? !body.equals(smsDTO.body) : smsDTO.body != null) return false;
        if (!deliveryDate.equals(smsDTO.deliveryDate)) return false;
        if (!sendDate.equals(smsDTO.sendDate)) return false;
        if (sender != null ? !sender.equals(smsDTO.sender) : smsDTO.sender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + sendDate.hashCode();
        result = 31 * result + deliveryDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SmsDTO{" +
                "sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
