package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.ReadObject;
import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginLogoutTest {
    private static FirefoxDriver driver;
    private static String username;
    private static String password;
    private static ReadObject object;
    private static Properties allObjects;
    private static UIOperation operation;


    @BeforeAll
    public static void setup() throws IOException {
        Properties browserProps = new Properties();
        try {
            String browserConfigPath = "settings.properties";
            browserProps.load(new FileInputStream(browserConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = browserProps.getProperty("username");
        password = browserProps.getProperty("password");
        String webdriverPath = browserProps.getProperty("webdriver");
        System.setProperty("webdriver.gecko.driver", webdriverPath);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        object = new ReadObject();
        allObjects = object.getObjectRepository();
        operation = new UIOperation(driver);
    }

    @BeforeEach
    public void goToURL() {
        operation.goToUrl(allObjects, "url");
    }

    @Test
    public void emptyCredentials() throws Exception {
       operation.click(allObjects, "loginbutton", "id");
       Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText(allObjects, "wrongLoginMessage", "xpath"));
    }

    @Test
    public void invalidPassword() throws Exception {
        operation.setText(allObjects, "username", "id", username);
        operation.setText(allObjects, "password", "id", "invalid");
        operation.click(allObjects, "loginbutton", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText(allObjects, "wrongLoginMessage", "xpath"));
        operation.setText(allObjects, "username", "id", username);
        operation.setText(allObjects, "password", "id", password);
        operation.click(allObjects, "loginbutton", "id");
        operation.click(allObjects, "profilePicture", "xpath");
        operation.click(allObjects, "viewProfile", "id");
        Assertions.assertEquals(username, operation.getText(allObjects, "name", "id"));
    }

    @Test
    public void notRegisteredUser() throws Exception {
        operation.setText(allObjects, "username", "id", "invalid");
        operation.setText(allObjects, "password", "id", "invalid");
        operation.click(allObjects, "loginbutton", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText(allObjects, "wrongLoginMessage", "xpath"));
    }

    @Test
    public void successfulLogin() throws Exception{
        operation.setText(allObjects, "username", "id", username);
        operation.setText(allObjects, "password", "id", password);
        operation.click(allObjects, "loginbutton", "id");
        operation.click(allObjects, "profilePicture", "xpath");
        operation.click(allObjects, "viewProfile", "id");
        Assertions.assertEquals(username, operation.getText(allObjects, "name", "id"));
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}
