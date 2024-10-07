package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class Chatgpt extends BaseTest {

    ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void initialize() {
        setUp(config.returnChatgptUrl());
    }
    @Test
    public void login() {

    }
}
