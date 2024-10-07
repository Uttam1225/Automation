package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.FetchAWSCreds;
import utils.SensitiveDataHandler;

import java.time.Duration;
import java.util.Set;

import static constants.AppConstants.GFG_TITLE;
import static constants.AppConstants.GFG_USERNAME;

public class GfgPotdPage {

    private final WebDriver driver;
    private final FetchAWSCreds password;

    // Define locators for the login page elements
    @FindBy(xpath = "//button[contains(@class, 'signinButton')]")   // xpath for login button
    private WebElement signInButton;
    @FindBy(xpath = "//input[contains(@class, 'mb15 next_input')]")  // xpath for sign-in username
    private  WebElement userName;
    @FindBy(xpath = "//input[contains(@placeholder, 'Password')]")   // xpath for password
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(@title, 'Sign In')]")
    private  WebElement clickOnSubmitButton;
    @FindBy(xpath = "//h1[@class='problemOfTheDay_potd_banner_heading__H0fpf']")
    private WebElement potd;

    //after login xpaths
    /*@FindBy(xpath = "//span[contains(@class, 'potdTour_skip_btn__Ro7RO')]")
    private WebElement skipButton;*/
    @FindBy(xpath = "//button[contains(@class, 'ui button problemOfTheDay_POTDCntBtn__SSQfX')]")
    private WebElement clickOnSolveProblemButton;



    // Constructor to initialize WebDriver
    public GfgPotdPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        SensitiveDataHandler sensitiveData = new SensitiveDataHandler();
        password = sensitiveData.fetechAwsCreds();
    }

    // Method to click on SignIn button
    public void clickSignInButton(){ signInButton.click(); }

    public void enterUserName() {
        userName.sendKeys(GFG_USERNAME);
    }

    public void enterPassword() {
        passwordField.sendKeys(password.firstString());
    }

    public void clickOnSubmitButton() {
        clickOnSubmitButton.click();
    }

    public void validationForSuccessfulLogin() {
        String potdText = potd.getText();
        Assert.assertEquals(potdText, GFG_TITLE);
    }

    public void clickOnSolveProblemButton() {
        //Explicit wait for button to appear on UI
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement skipProblemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'potdTour_skip_btn__Ro7RO')]")));
            skipProblemButton.click();
        }catch (Exception e) {
            System.out.println("Button not visible, skipping click.");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement solveProblemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'ui button problemOfTheDay_POTDCntBtn__SSQfX')]")));
        solveProblemButton.click();
        // Store the current window handle
        String originalWindow = driver.getWindowHandle();
        // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();
        // Loop through the handles and switch to the new tab
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        System.out.println("New tab title: " + driver.getTitle());
    }
}
