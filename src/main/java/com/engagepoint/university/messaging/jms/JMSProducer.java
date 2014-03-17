package com.engagepoint.university.messaging.jms;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class JMSProducer {
    //@Resource(lookup = "jms/testQueue")
    //private Queue queue;
    @Inject
    private JMSContext jmsContext;

    public void sendMessage() {
        String msg = "My jms 2.0 Message";
        jmsContext.createProducer().send(queue, msg);
    }


    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/testQueue")
    private Queue queue;

    public String produce() {
        String status = "OK";

        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, 0);
            MessageProducer producer = session.createProducer(queue);

            String msg = "My jms 2.0 Message";

            ObjectMessage message = session.createObjectMessage();
            message.setObject(msg);

            producer.send(message);

        } catch (JMSException e) {
            status = e.getMessage();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    status = e.getMessage();
                }
            }
        }
        return status;
    }
}
