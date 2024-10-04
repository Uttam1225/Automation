package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.FetchAWSCreds;
import utils.SensitiveDataHandler;
import static constants.AppConstants.DRIVER_TITLE;
import static constants.AppConstants.USERNAME;


public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        //creating object for Sensitive Data Handler class
        SensitiveDataHandler sensitiveData = new SensitiveDataHandler();
        FetchAWSCreds password = sensitiveData.fetechAwsCreds();
        FetchAWSCreds name = sensitiveData.fetechAwsCreds();
        loginPage.clickLoginButton();
        loginPage.enterUsername(USERNAME);
        loginPage.enterPassword(password.firstString());
        loginPage.clickSignInButton();
        // Assert condition
        Assert.assertTrue(driver.getTitle().contains(DRIVER_TITLE));
        // Assert condition for successful login to correct creds
        loginPage.isLoginSuccessful(name.secondString());
    }
}
