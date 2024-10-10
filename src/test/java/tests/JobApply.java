package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.JobApplyPage;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.FetchAWSCreds;
import utils.SensitiveDataHandler;

import static constants.AppConstants.DRIVER_TITLE;
import static constants.AppConstants.USERNAME;

public class JobApply extends BaseTest {
    ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void intialize() {
        setUp(config.returnNaukriUrl());
    }

    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        //creating object for Sensitive Data Handler class
        loginPage.clickLoginButton();
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickSignInButton();
        // Assert condition for successful login to correct creds
        loginPage.isLoginSuccessful();
    }
    @Test
    public void searchAndApplyJobsOnNaukri() throws InterruptedException {
        JobApplyPage jobApply = new JobApplyPage(driver);
        testValidLogin();
        jobApply.clickOnSearchButton();
        jobApply.enterSearchingDesignationAndExperience();
        Thread.sleep(10000);
        //jobApply.applyJobsOnNaukri();
    }
}
