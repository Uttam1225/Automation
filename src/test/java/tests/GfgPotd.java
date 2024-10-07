package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.GfgPotdPage;

public class GfgPotd extends BaseTest {

    public void gfgLogin(GfgPotdPage gfg) {
        gfg.clickSignInButton();
        gfg.enterUserName();
        gfg.enterPassword();
        gfg.clickOnSubmitButton();
        gfg.validationForSuccessfulLogin();
    }

    @Test
    public void getProblem() {
        GfgPotdPage gfg = new GfgPotdPage(driver);
        gfgLogin(gfg);
        gfg.clickOnSolveProblemButton();
    }
}
