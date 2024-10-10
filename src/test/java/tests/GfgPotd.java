package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GfgPotdPage;
import utils.ConfigReader;

public class GfgPotd extends BaseTest {

    ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void initialize() {
        setUp(config.returnGfgUrl());
    }

    public void gfgLogin(GfgPotdPage gfg) throws InterruptedException {
        gfg.clickSignInButton();
        gfg.enterUserName();
        gfg.enterPassword();
        Thread.sleep(5000);
        gfg.clickOnSubmitButton();
        gfg.validationForSuccessfulLogin();
    }

    @Test
    public void getProblem() throws InterruptedException {
        GfgPotdPage gfg = new GfgPotdPage(driver);
        gfgLogin(gfg);
        gfg.clickOnSolveProblemButton();
    }
}
