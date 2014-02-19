package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "email")
@NamedQueries({
        @NamedQuery(name = Email.GET_ALL, query = "SELECT e FROM Email e"),
        @NamedQuery(name = Email.GET_ALL_BY_EMAIL_ID, query = "SELECT em FROM Email em WHERE em.id = :idEmail"),
        @NamedQuery(name = Email.GET_ALL_BY_SENDER, query = "SELECT em FROM Email em WHERE em.sender = :sender"),
        @NamedQuery(name = Email.GET_ALL_BY_SUBJECT, query = "SELECT em FROM Email em WHERE em.subject = :subject"),
        @NamedQuery(name = Email.GET_ALL_BY_SEND_DATE, query = "SELECT em FROM Email em WHERE em.sendDate = :sendDate"),
        @NamedQuery(name = Email.GET_ALL_BY_DELIVERY_DATE, query = "SELECT em FROM Email em WHERE em.deliveryDate = :deliveryDate"),
        @NamedQuery(name = Email.GET_ALL_SORT_BY_DELIVERY_DATE, query = "SELECT em FROM Email em ORDER BY em.sender DESC"),
        @NamedQuery(name = Email.DELETE_EMAILS_LIST, query = "DELETE FROM Email em WHERE em.id IN :idList")})

public class Email implements Serializable, BaseEntity {

    private static final long serialVersionUID = 985345798781234739L;
    public static final String GET_ALL = "Email.findAll";
    public static final String GET_ALL_BY_EMAIL_ID = "Email.findByIdEmail";
    public static final String GET_ALL_BY_SENDER = "Email.findBySender";
    public static final String GET_ALL_BY_SUBJECT = "Email.findBySubject";
    public static final String GET_ALL_BY_SEND_DATE = "Email.findBySendDate";
    public static final String GET_ALL_BY_DELIVERY_DATE = "Email.findByDeliveryDate";
    public static final String GET_ALL_SORT_BY_DELIVERY_DATE = "Email.sortByDeliveryDate";
    public static final String DELETE_EMAILS_LIST = "Email.deleteEmailsList";
    public static final String PARAM_IDS_LIST = "idList";

    private Long id;
    private String sender;
    private String subject;
    @Lob
    @Size(max = 65535)
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    private Collection<User> userCollection;
    private Collection<Attachment> attachmentCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "send_date")
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Column(name = "delivery_date")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "attachment_has_email", joinColumns = {
            @JoinColumn(name = "attachment_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "email_id", referencedColumnName = "id")})
    public Collection<Attachment> getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(Collection<Attachment> attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    @ManyToMany(mappedBy = "emailCollection", cascade = CascadeType.ALL)
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }
}