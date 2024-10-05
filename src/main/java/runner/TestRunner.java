package runner;

import org.testng.TestNG;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(List.of("testng.xml")); // Point to your testng.xml
        testng.run();
    }

}
