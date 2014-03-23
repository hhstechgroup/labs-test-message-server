package com.engagepoint.acceptancetest.steps.jms;

import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import net.thucydides.core.pages.Pages;
import org.jbehave.core.annotations.When;

import javax.jms.JMSException;


public class JmsStep extends JbehaveBaseSteps {

    public JmsStep(Pages pages) {
        super(pages);
    }

    @When("send jms to without body")
    public void sendJms() {
        JmsProducer jmsProducer = new JmsProducer();
        try {
            jmsProducer.sendMessage();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
