package com.engagepoint.acceptancetest;

import com.engagepoint.acceptancetest.base.pages.UIBootstrapBasePage;
import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import com.engagepoint.university.messaging.smpp.SendSMS;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.seleniumhq.jetty7.server.Authentication;

public class SimpleSendSms  extends JbehaveBaseSteps {


    private UIBootstrapBasePage uIBootstrapBasePage;

    public SimpleSendSms(Pages pages) {
        super(pages);
        uIBootstrapBasePage = pages().get(UIBootstrapBasePage.class);
    }

    @When("the sender '$sender' send sms to '$receiver' the body is '$body'")
    public void sendSms(String sender, String receiver, String body){
        SendSMS sendSMS = new SendSMS();
        sendSMS.sendSMS(sender,receiver,body);

    }
    @Then("wait for '$timeout' sec")
    public void waitForTimeout(int timeout) {
        uIBootstrapBasePage.waitFor(timeout).seconds();
    }

}
