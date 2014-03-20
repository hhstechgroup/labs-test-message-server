package com.engagepoint.university.messaging.jms;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class JMSProducer {

    @Resource(lookup = "jms/myQueue")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    public void sendMessage() {
        String msg = "My jms 2.0 Message";
        jmsContext.createProducer().send(queue, msg);
    }

}
