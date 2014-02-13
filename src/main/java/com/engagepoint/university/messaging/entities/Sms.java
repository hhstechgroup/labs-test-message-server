package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entities.base.Base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = Sms.GET_ALL_BY_SMS_ID, query = "SELECT sm FROM Sms sm WHERE sm.idSms = :idSms"),
        @NamedQuery(name = Sms.GET_ALL_BY_SENDER, query = "SELECT sm FROM Sms sm WHERE sm.sender = :sender"),
        @NamedQuery(name = Sms.GET_ALL_BY_SEND_DATE, query = "SELECT sm FROM Sms sm WHERE sm.sendDate = :sendDate"),
        @NamedQuery(name = Sms.GET_ALL_BY_DELIVERY_DATE, query = "SELECT sm FROM Sms sm WHERE sm.deliveryDate = :deliveryDate")})

public class Sms extends Base implements Serializable {

    private static final long serialVersionUID = 6745638798781234739L;
    public static final String GET_ALL_BY_SMS_ID = "Sms.findByIdSms";
    public static final String GET_ALL_BY_SENDER = "Sms.findBySender";
    public static final String GET_ALL_BY_SEND_DATE = "Sms.findBySendDate";
    public static final String GET_ALL_BY_DELIVERY_DATE = "Sms.findByDeliveryDate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSms;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String sender;

    @Lob
    @Size(max = 65535)
    private String body;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

    //delivery Date can be initially later
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    //@JoinTable(name = "mapped_user_sms", joinColumns = {
    //      @JoinColumn(name = "Sms_id_sms", referencedColumnName = "idsms")}, inverseJoinColumns = {
    //        @JoinColumn(name = "User_id_user", referencedColumnName = "iduser")})
    @ManyToMany
    private Collection<User> userCollection;

    public Sms() {
    }

    public Sms(SmsDTO smsDTO) {
        this.sender = smsDTO.getSender();
        this.sendDate = smsDTO.getSendDate();
        this.deliveryDate = smsDTO.getDeliveryDate();
        this.body = smsDTO.getBody();
    }

      public Integer getIdSms() {
        return idSms;
    }

    public void setIdSms(Integer idSms) {
        this.idSms = idSms;
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

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSms != null ? idSms.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sms)) {
            return false;
        }
        Sms other = (Sms) object;
        if ((this.idSms == null && other.idSms != null) || (this.idSms != null && !this.idSms.equals(other.idSms))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sms[ idSms=" + idSms + " ]";
    }

}
