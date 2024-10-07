package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FetchAWSCreds;
import utils.SensitiveDataHandler;

import java.time.Duration;

import static constants.AppConstants.USERNAME;

public class ChatgptPage {

    private final WebDriver driver;
    private final FetchAWSCreds password;

    // Define locators for the login page elements
    @FindBy(xpath = "//button[contains(@data-testid, 'welcome-login-button')]")   // xpath for login button
    private WebElement logInButton;
    @FindBy(xpath = "//button[contains(@class, 'continue-btn')]")
    private WebElement continueButton;
    @FindBy(xpath = "//button[contains(@class, 'ccfd14389 c00a4c297 c50fd13c0 c04f4358f _button-login-password')]")
    private WebElement submitButton;
    @FindBy(xpath = "//p[contains(@class, 'placeholder')]")
    private  WebElement enterTextOnChatgpt;

    public ChatgptPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        SensitiveDataHandler sensitiveData = new SensitiveDataHandler();
        password = sensitiveData.fetechAwsCreds();
    }

    public void clickOnLoginButton() { logInButton.click(); }

    public void enterUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'email-input')]")));
        username.sendKeys(USERNAME);
    }

    public void clickOnContinueButton() { continueButton.click(); }

    public void enterPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement chatgptPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id, 'password')]")));
        chatgptPassword.sendKeys(password.firstString());
    }

    public void clickOnSubmitButton() { submitButton.click(); }

    public void setEnterTextOnChatgpt() {
        enterTextOnChatgpt.sendKeys("");
    }
}
