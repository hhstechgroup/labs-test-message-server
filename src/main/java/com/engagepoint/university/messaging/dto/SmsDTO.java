package com.engagepoint.university.messaging.dto;

/*
 * #%L
 * labs-test-message-server
 * %%
 * Copyright (C) 2012 - 2014 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.engagepoint.university.messaging.dao.condao.impl.SmsDAOImpl;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.entities.base.Base;


import java.util.Date;

public class SmsDTO{

    private int id;
    private String sender;
    private String body;
    private Date sendDate;
    private Date deliveryDate;
    private String recipient;  //Viktor - add constructor and recipient(get/set)

    private SmsDAOImpl smsDAOImpl = new SmsDAOImpl();

    public SmsDTO(){

    }
    public SmsDTO(String sender, String body, Date sendDate, Date deliveryDate) {
        this.sender = sender;
        this.body = body;
        this.sendDate = sendDate;
        this.deliveryDate = deliveryDate;
    }

    public void saveSms(Sms sms){      //Viktor - add
        smsDAOImpl.save(sms);
    }
    public void saveSmsDTO(SmsDTO smsDTO){    //Viktor - add
             // from DTO to SMS
          Sms sms = new Sms();
        sms.setSender(smsDTO.getSender());
        sms.setBody(smsDTO.getBody());
        sms.setRecipient(smsDTO.getRecipient());
        sms.setDeliveryDate(smsDTO.getDeliveryDate());
        sms.setSendDate(smsDTO.getSendDate());

        saveSms(sms);

    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsDTO smsDTO = (SmsDTO) o;

        if (body != null ? !body.equals(smsDTO.body) : smsDTO.body != null) return false;
        if (!deliveryDate.equals(smsDTO.deliveryDate)) return false;
        if (!sendDate.equals(smsDTO.sendDate)) return false;
        if (sender != null ? !sender.equals(smsDTO.sender) : smsDTO.sender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + sendDate.hashCode();
        result = 31 * result + deliveryDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SmsDTO{" +
                "sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
