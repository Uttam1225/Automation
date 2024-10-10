package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LinkedInPage;
import utils.ConfigReader;

public class LinkedIn extends BaseTest {

    ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void initialize() {
        setUp(config.returnLinkedINUrl());
    }

    public void login(LinkedInPage page) {
        page.enterUsername();
        page.enterPassword();
        page.clickOnSubmitButton();
    }

    @Test
    public void searchTalentAcquisitionPeople() throws InterruptedException {
        LinkedInPage page = new LinkedInPage(driver);
        login(page);
        page.searchPeopleOnLinkedInHomePage();
        page.clickOnPeopleButton();
        Thread.sleep(5000);
        page.clickOnConnectButtons();
    }

}
