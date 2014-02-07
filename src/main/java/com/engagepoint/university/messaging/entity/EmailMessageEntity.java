package com.engagepoint.university.messaging.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQueries({
    @NamedQuery(name = "getEmailById", query = "select em from EmailMessageEntity em where em.id = :id"),
    @NamedQuery(name = "getEmailBySender", query = "select em from EmailMessageEntity em where em.sender = :sender"),
    @NamedQuery(name = "getEmailByReceiver", query = "select em from EmailMessageEntity em where em.receiver like :receiver")
})
public class EmailMessageEntity implements Serializable {
    public static final String GET_EMAIL_BY_SENDER = "getEmailBySender";
    public static final String GET_EMAIL_BY_RECEIVER = "getEmailByReceiver";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String sender;
    private String receiver;
    private String subject;
    private String text;
    private String attachments;
    private Date date_insert;
    private Date date_delete;
    private boolean flag;

    public EmailMessageEntity(String sender, String receiver, String subject, String text, String attachments, Date date_insert) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.text = text;
        this.attachments = attachments;
        this.date_insert = date_insert;
    }

    public EmailMessageEntity() {
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

    public void setSender(String from) {
        this.sender = from;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String to) {
        this.receiver = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Date getDate_insert() {
        return date_insert;
    }

    public void setDate_insert(Date date_insert) {
        this.date_insert = date_insert;
    }

    public Date getDate_delete() {
        return date_delete;
    }

    public void setDate_delete(Date date_delete) {
        this.date_delete = date_delete;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "EmailMessageEntity{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", reciver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", attachments='" + attachments + '\'' +
                ", date_insert=" + date_insert +
                ", date_delete=" + date_delete +
                ", flag=" + flag +
                '}';
    }
}
