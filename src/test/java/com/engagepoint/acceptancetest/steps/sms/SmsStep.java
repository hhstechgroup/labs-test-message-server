package com.engagepoint.acceptancetest.steps.sms;

import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import net.thucydides.core.pages.Pages;
import org.jbehave.core.annotations.When;

public class SmsStep extends JbehaveBaseSteps {

    public SmsStep(Pages pages) {
        super(pages);
    }

    @When("the sender '$sender' send sms to '$receiver' the body is '$body'")
    public void sendSms(String sender, String receiver, String body) {
        SmsController sendSMS = new SmsController();
        sendSMS.sendSMS(sender, receiver, body);
    }
}
