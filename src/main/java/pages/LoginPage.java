package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // Define locators for the login page elements
    @FindBy(xpath = "//a[contains(@id, 'login_Layer')]")   // xpath for login button
    private WebElement loginButton;
    @FindBy(xpath = "//input[contains(@placeholder, 'Enter your password')]")   // xpath for password
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(@class, 'btn-primary loginButton')]")   // xpath for sign-in button
    private WebElement signInButton;

    // Constructor to initialize WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click on Login button
    public void clickLoginButton(){ loginButton.click(); }

    // Method to enter the username
    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Enter your active Email ID')]")));
        usernameFieldElement.sendKeys(username);
    }

    // Method to enter the password
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    // Method to click the signIn button
    public void clickSignInButton() { signInButton.click(); }

    // Optionally, a method to check if login is successful (e.g., by checking account holder name)
    public void isLoginSuccessful(String accountHolderName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accHolderName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'info__heading')]")));
        String name = accHolderName.getText();
        Assert.assertEquals(accountHolderName, name);
    }
}
