package com.engagepoint.acceptancetest.sms;

import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import net.thucydides.core.pages.Pages;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

public class SimpleSendSms extends JbehaveBaseSteps {

    private SendSMSAcceptanceTest sendSMS;

    public SimpleSendSms(Pages pages) {
        super(pages);
    }

    @Given("create sender sms")
    public void createSender() {
        sendSMS = new SendSMSAcceptanceTest();

    }

    @When("the sender '$sender' send sms to '$receiver' the body is '$body'")
    public void sendSms(String sender, String receiver, String body) {
        SendSMSAcceptanceTest sendSMS = new SendSMSAcceptanceTest();
        sendSMS.sendSMS(sender, receiver, body);

    }
}
