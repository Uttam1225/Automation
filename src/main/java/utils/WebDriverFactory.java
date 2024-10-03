package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {

    // Create WebDriver instance based on the browser name
    public static WebDriver createDriver(String browser) {
        // Setup Chrome driver
        // Setup Firefox driver
        // Setup Edge driver

        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup(); // Setup Chrome driver
                yield new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup(); // Setup Firefox driver
                yield new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup(); // Setup Edge driver
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
