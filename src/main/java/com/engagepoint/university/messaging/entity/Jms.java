package com.engagepoint.university.messaging.entity;

import com.engagepoint.university.messaging.entity.base.BaseEntity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "activemq_msgs")
@NamedQueries({
        @NamedQuery(name = "ActivemqMsgs.findAll", query = "SELECT a FROM Jms a"),
        @NamedQuery(name = "ActivemqMsgs.findById", query = "SELECT a FROM Jms a WHERE a.id = :id"),
        @NamedQuery(name = "ActivemqMsgs.findByContainer", query = "SELECT a FROM Jms a WHERE a.container = :container"),
        @NamedQuery(name = "ActivemqMsgs.findByMsgidProd", query = "SELECT a FROM Jms a WHERE a.msgidProd = :msgidProd"),
        @NamedQuery(name = Jms.GET_JMS_QUICK_SEARCH, query = "SELECT a FROM Jms a WHERE a.msgidProd LIKE :msgidProd"),
        @NamedQuery(name = "ActivemqMsgs.findByExpiration", query = "SELECT a FROM Jms a WHERE a.expiration = :expiration"),
        @NamedQuery(name = "ActivemqMsgs.findByPriority", query = "SELECT a FROM Jms a WHERE a.priority = :priority"),
        @NamedQuery(name = "ActivemqMsgs.findByXid", query = "SELECT a FROM Jms a WHERE a.xid = :xid")})
public class Jms implements Serializable, BaseEntity {
    private static final long serialVersionUID = 1L;

    public static final String GET_JMS_QUICK_SEARCH = "ActivemqMsgs.findByMsgidSeq";

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 250)
    @Column(name = "CONTAINER")
    private String container;
    @Size(max = 250)
    @Column(name = "MSGID_PROD")
    private String msgidProd;
    @Column(name = "MSGID_SEQ")
    private BigInteger msgidSeq;
    @Column(name = "EXPIRATION")
    private BigInteger expiration;
    @Lob
    @Column(name = "MSG")
    private byte[] msg;
    @Column(name = "PRIORITY")
    private BigInteger priority;
    @Size(max = 250)
    @Column(name = "XID")
    private String xid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getMsgidProd() {
        return msgidProd;
    }

    public void setMsgidProd(String msgidProd) {
        this.msgidProd = msgidProd;
    }

    public BigInteger getMsgidSeq() {
        return msgidSeq;
    }

    public void setMsgidSeq(BigInteger msgidSeq) {
        this.msgidSeq = msgidSeq;
    }

    public BigInteger getExpiration() {
        return expiration;
    }

    public void setExpiration(BigInteger expiration) {
        this.expiration = expiration;
    }

    public byte[] getMsg() {
        return msg;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }

    public BigInteger getPriority() {
        return priority;
    }

    public void setPriority(BigInteger priority) {
        this.priority = priority;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }
}
