package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    // Create WebDriver instance based on the browser name
    public static WebDriver createDriver(String browser) {
        // Setup Chrome driver
        // Setup Firefox driver
        // Setup Edge driver

        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();// Setup Chrome driver
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                yield new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup(); // Setup Firefox driver
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--incognito");
                yield new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup(); // Setup Edge driver
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--incognito");
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
