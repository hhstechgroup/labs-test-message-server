package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "webService")
@NamedQueries({
        @NamedQuery(name = WebService.GET_SEARCHING_WEB_SERVICE, query = "SELECT sm FROM WebService sm WHERE" +
                " sm.sender LIKE :sender OR" +
                " sm.body LIKE :body"),

        @NamedQuery(name = WebService.GET_ALL_WEB_SERVICE, query = "SELECT sm FROM WebService sm"),
        @NamedQuery(name = WebService.GET_ALL_BY_WEB_SERVICE_ID, query = "SELECT sm FROM WebService sm WHERE sm.id = :idWebService"),
        @NamedQuery(name = WebService.GET_ALL_BY_SENDER, query = "SELECT sm FROM WebService sm WHERE sm.sender = :sender"),
        @NamedQuery(name = WebService.GET_ALL_BY_SEND_DATE, query = "SELECT sm FROM WebService sm WHERE sm.sendDate = :sendDate"),
        @NamedQuery(name = WebService.DELETE_WEB_SERVICE_LIST, query = "DELETE FROM WebService sm WHERE sm.id IN :idList")})
public class WebService implements Serializable, BaseEntity {


    private static final long serialVersionUID = 6745638798781234739L;
    public static final String GET_SEARCHING_WEB_SERVICE = "WebService.findSearchingSms";

    public static final String GET_ALL_WEB_SERVICE = "WebService.findAll";
    public static final String GET_ALL_BY_WEB_SERVICE_ID = "WebService.findByIdSms";
    public static final String GET_ALL_BY_SENDER = "WebService.findBySender";
    public static final String GET_ALL_BY_SEND_DATE = "WebService.findBySendDate";
    public static final String DELETE_WEB_SERVICE_LIST = "WebService.deleteSmsList";
    public static final String PARAM_IDS_LIST = "idList";

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
