package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static constants.AppConstants.DESIGNATION;
import static constants.AppConstants.EXPERIENCE;
import static utils.GenericUtils.getSkillsFromTextFile;

public class JobApplyPage {
    private final WebDriver driver;

    @FindBy(xpath = "//span[contains(@class, 'nI-gNb-sb__placeholder')]")
    private WebElement clickSearchButton;
    @FindBy(xpath = "//input[contains(@placeholder, 'Enter keyword / designation / companies')]")
    private WebElement enterSearchDesignation;
    @FindBy(xpath = "//input[contains(@id, 'experienceDD')]")
    private WebElement enterSearchExperience;
    @FindBy(xpath = "//span[contains(@class, 'ni-gnb-icn ni-gnb-icn-search')]")
    private WebElement searchButton;
    @FindBy(css = "ul.tags-gt")
    private  WebElement listOfKeySkills;

    public JobApplyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickSearchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'nI-gNb-sb__placeholder')]")));
        clickSearchButton.click();
    }

    public void enterSearchingDesignationAndExperience() {
        enterSearchDesignation.sendKeys(DESIGNATION);
        enterSearchExperience.sendKeys(EXPERIENCE);
        searchButton.click();
    }

    public void applyJobsOnNaukri() {
        List<WebElement> requiredSkills = listOfKeySkills.findElements(By.cssSelector("li"));
        List<String> mySkills = getSkillsFromTextFile();
    }

}
