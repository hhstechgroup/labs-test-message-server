package com.engagepoint.university.messaging.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQuery(name = "listALL", query = "select em from EmailMessageDTO em")
public class EmailMessageDTO implements Serializable {
    public static final String LIST_ALL = "listALL";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String from;

    private String to;

    private String subject;

    private String text;

    private String attachments;

    private Date date_insert;

    private Date date_delete;

    private boolean flag;

    public EmailMessageDTO(String from, String to, String subject, String text, String attachments, Date date_insert) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.attachments = attachments;
        this.date_insert = date_insert;
    }

    public EmailMessageDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        return "EmailMessageDTO{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", attachments='" + attachments + '\'' +
                ", date_insert=" + date_insert +
                ", date_delete=" + date_delete +
                ", flag=" + flag +
                '}';
    }
}
