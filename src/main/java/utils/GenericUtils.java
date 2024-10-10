package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class GenericUtils {

    static ConfigReader config = new ConfigReader();
    static String filePath = config.getMySkillSet();

    public static List<String> getSkillsFromTextFile() {    // To read mySkillSet file
        List<String> mySkills = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");  // Split line by commas
                for (String value : data) {
                    mySkills.add(value.trim()); // Trim removes any leading/trailing spaces
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mySkills;
    }

    public static double matchSets(Set<String> smallSet, Set<String> largeSet) {   //match whole string and getting % of matched data
        int matches = 0;
        // Compare elements from the smallSet with the largeSet
        for (String item : smallSet) {
            if (largeSet.contains(item)) {
                matches++;
            }
        }
        // Calculate the matching percentage
        return (double) matches / smallSet.size() * 100;
    }

    /*public static double matchString(List<String> smallStr , List<String> largeStr) {
        JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

        String largeText = "Automation testing with Selenium and Java is popular";
        String smallText = "Selenium testing and Java";

        // Calculate similarity between two strings
        double matchScore = similarity.apply(smallText, largeText);
        return matchScore;
    }*/

    public static void scrolldownTillTheEndOfThePage(WebDriver driver) throws InterruptedException {
        // Scroll down the page to load more elements
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(5000); //wait for content to load
    }

    public static void scrollupTillTheBeginingOfThePage(WebDriver driver) {
        // Scroll up to a specific position or element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
        /* or scroll to a specific element if needed
         WebElement elementToScrollTo = driver.findElement(By.id("elementId"));
         js.executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);*/
    }

}
