package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.SecretsManagerUtil;
import org.json.JSONObject;

public class LoginTest extends BaseTest {

    private String password;
    private String name;

    @BeforeClass
    public void setUp() {
        SecretsManagerUtil secretsManager = new SecretsManagerUtil("MyApp/Naukri.com/Credentials", "us-east-1");
        JSONObject secret = secretsManager.getSecret();
        password = secret.getString("password");
        name = secret.getString("name");
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.enterUsername("uuusss1225@gmail.com");
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        // Assert condition
        Assert.assertTrue(driver.getTitle().contains("Naukri.com"));
        // Assert condition for successful login to correct creds
        loginPage.isLoginSuccessful(name);
    }
}
