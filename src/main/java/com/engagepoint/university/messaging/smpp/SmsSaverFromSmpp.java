package com.engagepoint.university.messaging.smpp;

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


import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.pdu.SubmitSm;

import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dao.specific.impl.SmsDAOImpl;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entities.Sms;

import javax.inject.Inject;
import java.util.Date;

public class SmsSaverFromSmpp {

    @Inject
    private SmsDAOImpl smsDAO;

    public void saveSms(SubmitSm submitSm){

        System.out.println(" *** Try to save SMS *** " + submitSm.getResponseClass());
        System.out.println("!!! Try to save SMS.getDestAddress();"+ submitSm.getDestAddress().getAddress());  //номер телефона
        System.out.println("!!! Try to save SMS.getSourceAddress();"+ submitSm.getSourceAddress().getAddress());// отправитель - так как это ответ все наоборот
        System.out.println("!!! Try to save SMS.getShortMessage() in byte" + submitSm.getShortMessage());//byte
        System.out.println("!!! Try to save SMS.getName()"+submitSm.getName());
        System.out.println("!!! Try to save SMS.getName()"+submitSm.getReferenceObject());

        String str = CharsetUtil.decode(submitSm.getShortMessage(), CharsetUtil.CHARSET_MODIFIED_UTF8);
        System.out.println("!!! Try to save SMS - str " + str);

        //Создаем SMSMessage new
        Sms sms = new Sms();
        sms.setBody(str);
        sms.setSender(submitSm.getSourceAddress().getAddress());
        sms.setSendDate(new Date());
        sms.setDeliveryDate(new Date());
        //sms.setRecipient(submitSm.getDestAddress().getAddress());
        //smsDTO сохранить получателя
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setBody(str);
        smsDTO.setSender(submitSm.getSourceAddress().getAddress());
        smsDTO.setDeliveryDate(new Date());
        smsDTO.setSendDate(new Date());
        //smsDTO.setRecipient(submitSm.getDestAddress().getAddress());
        //smsDTO.saveSmsDTO(smsDTO);
        smsDAO.save(smsDTO);
        //smsDTO.saveSms(sms);

       /* SmsDAOImpl smsDAOImpl = new SmsDAOImpl();
        smsDAOImpl.                     //generik only sms or email
        */
    }
}
