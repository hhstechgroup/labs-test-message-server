package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.entities.base.Base;

import java.util.Date;

public class EmailDTO{

    private Integer id;
    private String sender;
    private String subject;
    private String body;
    private Date sendDate;
    private Date deliveryDate;

    public EmailDTO() {
    }

    public EmailDTO(String sender, String subject, String body, Date sendDate, Date deliveryDate) {
        this.sender = sender;
        this.subject = subject;
        this.body = body;
        this.sendDate = sendDate;
        this.deliveryDate = deliveryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

        EmailDTO emailDTO = (EmailDTO) o;

        if (body != null ? !body.equals(emailDTO.body) : emailDTO.body != null) return false;
        if (!deliveryDate.equals(emailDTO.deliveryDate)) return false;
        if (id != null ? !id.equals(emailDTO.id) : emailDTO.id != null) return false;
        if (!sendDate.equals(emailDTO.sendDate)) return false;
        if (sender != null ? !sender.equals(emailDTO.sender) : emailDTO.sender != null) return false;
        if (subject != null ? !subject.equals(emailDTO.subject) : emailDTO.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + sendDate.hashCode();
        result = 31 * result + deliveryDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
