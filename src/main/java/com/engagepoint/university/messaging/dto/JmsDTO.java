package com.engagepoint.university.messaging.dto;

import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.math.BigInteger;
import java.util.Date;

public class JmsDTO extends BaseDTO {

    private Long id;
    private String container;
    private String msgidProd;
    private BigInteger msgidSeq;
    private BigInteger expiration;
    private byte[] msg;
    private BigInteger priority;
    private String xid;

    private Boolean flag = Boolean.FALSE;

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
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

}
