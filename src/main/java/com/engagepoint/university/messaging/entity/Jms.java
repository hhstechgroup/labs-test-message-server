package com.engagepoint.university.messaging.entity;

import com.engagepoint.university.messaging.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Jms")
@NamedQueries({
        @NamedQuery(name = Jms.GET_ALL_JMS_MESSAGES, query = "SELECT jms FROM Jms jms"),
        @NamedQuery(name = Jms.GET_ALL_BY_JMS_MESSAGES_ID, query = "SELECT jms FROM Jms jms WHERE jms.id = :idJms"),
        @NamedQuery(name = Jms.GET_ALL_BY_SENDER, query = "SELECT jms FROM Jms jms WHERE jms.sender = :sender"),
        @NamedQuery(name = Jms.GET_ALL_BY_SEND_DATE, query = "SELECT jms FROM Jms jms WHERE jms.sendDate = :sendDate"),
        @NamedQuery(name = Jms.DELETE_JMS_MESSAGES_LIST, query = "DELETE FROM Jms jms WHERE jms.id IN :idList")})

public class Jms implements Serializable, BaseEntity {
    private static final long serialVersionUID = 8756335744756867929L;


    public static final String GET_ALL_JMS_MESSAGES = "Jms.findAll";
    public static final String GET_ALL_BY_JMS_MESSAGES_ID = "Jms.findByIdSms";
    public static final String GET_ALL_BY_SENDER = "Jms.findBySender";
    public static final String GET_ALL_BY_SEND_DATE = "Jms.findBySendDate";
    public static final String DELETE_JMS_MESSAGES_LIST = "Jms.deleteSmsList";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sender")
    private String sender;

    @Lob
    @Size(max = 65535)
    @Column(name = "body")
    private String body;

    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

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
}