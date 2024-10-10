package pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FetchAWSCreds;
import utils.SensitiveDataHandler;

import java.time.Duration;
import java.util.List;

import static constants.AppConstants.*;
import static utils.GenericUtils.*;

public class LinkedInPage {

    private final WebDriver driver;
    private final FetchAWSCreds password;

    // Define locators for the login page elements
    @FindBy(xpath = "//input[contains(@id,'password')]")
    private WebElement enterPassword;
    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement submitButton;

    //// Method to handle pop-up if it appears
    private static void handlePopupWithNoteAndButton(WebDriver driver) {
        try {
            // Wait for the pop-up to appear (customize the wait duration as needed)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Locate the text area/input field to send the note
            WebElement noteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'artdeco-button__text') and contains(., 'Add a note')]")));
            noteButton.click();
            WebElement noteField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name, 'message')]")));
            noteField.sendKeys(NOTE_FOR_PEOPLE_LINKED_IN);
            // Locate the submit button on the pop-up
            WebElement sendButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Send invitation')]")); // Adjust the XPath as per the button in the pop-up
            sendButton.click();
            System.out.println("Note submitted and button clicked!");
        } catch (Exception e) {
            System.out.println("An error occurred while handling the pop-up for NoteButton: " + e.getMessage());
            DismissPopUp(driver);
        }
    }

    private static void DismissPopUp(WebDriver driver) {
        try {
            // Wait for a pop-up or alert to appear (modify wait time as necessary)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Locate the text area/input field to send the note
            WebElement noteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'artdeco-button__text') and contains(., 'Add a note')]")));
            noteButton.click();

        } catch (NoAlertPresentException e) {
            System.out.println("No pop-up appeared.");
        } catch (Exception e) {
            System.out.println("An error occurred while handling the pop-up: " + e.getMessage());
        }
    }

    public LinkedInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        SensitiveDataHandler sensitiveData = new SensitiveDataHandler();
        password = sensitiveData.fetechAwsCreds();
    }

    public void enterUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'username')]")));
        username.sendKeys(USERNAME);
    }

    public void enterPassword() {
        enterPassword.sendKeys(password.firstString());
    }

    public void clickOnSubmitButton() { submitButton.click(); }

    public void searchPeopleOnLinkedInHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchPeople = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'search-global-typeahead__input')]")));
        searchPeople.sendKeys(SEARCH_PEOPLE);
        searchPeople.sendKeys(Keys.ENTER);
    }

    public void clickOnPeopleButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement selectPeopleButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'artdeco-pill') and contains(., 'People')]")));
        selectPeopleButton.isSelected();
    }

    public void clickOnConnectButtons() throws InterruptedException {
        int i = 0;
        for(int j=0;j<3;++j) {
            List<WebElement> connButtons = driver.findElements(By.xpath("//span[contains(., 'Connect')]"));
            for (WebElement button : connButtons) {
                button.click();
                handlePopupWithNoteAndButton(driver);
                // Add a small wait to avoid overlapping clicks (if necessary)
                Thread.sleep(2000);
                i += 1;
            }

            scrolldownTillTheEndOfThePage(driver);

            // Check for the presence of the next button
            try {
                WebElement nextButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Next')]")); // Adjust the xpath as needed
                nextButton.click();
                Thread.sleep(2000); // Wait for the next page to load
            } catch (NoSuchElementException e) {
                System.out.println("No Next Button is present on the page"); // Exit loop if next button is not found
            }
            scrollupTillTheBeginingOfThePage(driver);

            System.out.println("Connect Button Click Count = " + i);
        }
    }

}
