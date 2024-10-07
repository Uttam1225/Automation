package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverFactory;

public class BaseTest {

    protected WebDriver driver;

    public void setUp(String url) {
        String browser = System.getProperty("browser", "edge");  // Default to Edge if no browser is specified
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
