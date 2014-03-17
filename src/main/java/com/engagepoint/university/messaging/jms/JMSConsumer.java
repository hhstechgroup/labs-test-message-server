package com.engagepoint.university.messaging.jms;

import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.repository.JmsService;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Date;

@MessageDriven(mappedName = "jms/myQueue")
public class JMSConsumer implements MessageListener {

    @Inject
    JmsService jmsService;

    @Override
    public void onMessage(Message message) {
        String msg;
        try {
            msg = ((TextMessage) message).getText();
            JmsDTO jmsDTO = new JmsDTO();
            jmsDTO.setBody(msg);
            jmsDTO.setSendDate(new Date());
            jmsService.save(jmsDTO);
            System.out.println("jms Message received: " + msg);
        } catch (JMSException e) {
            System.err.println("Error while fetching message: " + e.getMessage());
        }
    }
}
