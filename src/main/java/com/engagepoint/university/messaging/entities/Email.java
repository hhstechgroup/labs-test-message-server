package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.baseentity.Base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Alexey on 2/9/14.
 */

@Entity
@Table(name = "email")
@NamedQueries({
        @NamedQuery(name = Email.GET_ALL, query = "SELECT em FROM Email em"),
        @NamedQuery(name = Email.GET_ALL_BY_EMAIL_ID, query = "SELECT em FROM Email em WHERE em.idEmail = :idEmail"),
        @NamedQuery(name = Email.GET_ALL_BY_SENDER, query = "SELECT em FROM Email em WHERE em.sender = :sender"),
        @NamedQuery(name = Email.GET_ALL_BY_SUBJECT, query = "SELECT em FROM Email em WHERE em.subject = :subject"),
        @NamedQuery(name = Email.GET_ALL_BY_SEND_DATE, query = "SELECT em FROM Email em WHERE em.sendDate = :sendDate"),
        @NamedQuery(name = Email.GET_ALL_BY_DELIVERY_DATE, query = "SELECT em FROM Email em WHERE em.deliveryDate = :deliveryDate")})

public class Email extends Base implements Serializable {

    private static final long serialVersionUID = 985345798781234739L;
    public static final String GET_ALL = "Email.findAll";
    public static final String GET_ALL_BY_EMAIL_ID = "Email.findByIdEmail";
    public static final String GET_ALL_BY_SENDER = "Email.findBySender";
    public static final String GET_ALL_BY_SUBJECT = "Email.findBySubject";
    public static final String GET_ALL_BY_SEND_DATE = "Email.findBySendDate";
    public static final String GET_ALL_BY_DELIVERY_DATE = "Email.findByDeliveryDate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_email")
    private Integer idEmail;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sender")
    private String sender;

    @Size(max = 255)
    @Column(name = "subject")
    private String subject;

    @Lob
    @Size(max = 65535)
    @Column(name = "body")
    private String body;

    @Basic(optional = false)
    @NotNull
    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @JoinTable(name = "mapped_user_email", joinColumns = {
            @JoinColumn(name = "Email_id_email", referencedColumnName = "id_email")}, inverseJoinColumns = {
            @JoinColumn(name = "User_id_user", referencedColumnName = "id_user")})
    @ManyToMany
    private Collection<User> userCollection;

    @JoinTable(name = "mapped_email_attachment", joinColumns = {
            @JoinColumn(name = "Email_id_email", referencedColumnName = "id_email")}, inverseJoinColumns = {
            @JoinColumn(name = "Attachment_id_attachment", referencedColumnName = "id_attachment")})
    @ManyToMany
    private Collection<Attachment> attachmentCollection;

    public Email() {
    }

    public Email(EmailDTO emailDTO) {
        this.sender = emailDTO.getSender();
        this.subject = emailDTO.getSubject();
        this.body = emailDTO.getBody();
        this.sendDate = emailDTO.getSendDate();
        this.deliveryDate = emailDTO.getDeliveryDate();
    }

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Integer idEmail) {
        this.idEmail = idEmail;
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

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<Attachment> getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(Collection<Attachment> attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmail != null ? idEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.idEmail == null && other.idEmail != null) || (this.idEmail != null && !this.idEmail.equals(other.idEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Email[ idEmail=" + idEmail + " ]";
    }

}