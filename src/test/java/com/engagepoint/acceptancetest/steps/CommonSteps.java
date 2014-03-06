package com.engagepoint.acceptancetest.steps;

import com.engagepoint.acceptancetest.base.pages.UIBootstrapBasePage;
import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import net.thucydides.core.pages.Pages;
import org.jbehave.core.annotations.Then;

public class CommonSteps extends JbehaveBaseSteps {

    private UIBootstrapBasePage uIBootstrapBasePage;

    public CommonSteps(Pages pages) {
        super(pages);
        uIBootstrapBasePage = pages().get(UIBootstrapBasePage.class);
    }

    @Then("wait for '$timeout' sec")
    public void waitForTimeout(int timeout) {
        uIBootstrapBasePage.waitFor(timeout).seconds();
    }

}
