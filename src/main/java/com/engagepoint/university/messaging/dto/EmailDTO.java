package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.Collection;
import java.util.Date;

public class EmailDTO extends BaseDTO {

    private String sender;
    private String subject;
    private String body;
    private Date sendDate;
    private Date deliveryDate;
    private Collection<AttachmentDTO> attachmentCollection;
    private Boolean flag = Boolean.FALSE;
    private String recipient;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public String getShortBody() {
        if(body.length()<10){
            return body;
        }
        return body.substring(1, 10);
    }

    public int getBodyLength(){
        return body.length();
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

    public Collection<AttachmentDTO> getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(Collection<AttachmentDTO> attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "id=" + this.getId() +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
