package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.ReadObject;
import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginPageTest {
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
        object = new ReadObject();
        allObjects = object.getObjectRepository();

    }

    @BeforeEach
    public void goToURL() {
        operation = new UIOperation();
        operation.goToUrl(allObjects, "loginPageURL");
    }

    @Test
    public void invalidPassword() throws Exception {
        operation.setText(allObjects, "username", "id", username);
        operation.setText(allObjects, "password", "id", "invalid");
        operation.click(allObjects, "loginFormSubmit", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText(allObjects, "loginPageMessage", "xpath"));
        operation.setText(allObjects, "username", "id", username);
        operation.setText(allObjects, "password", "id", password);
        operation.click(allObjects, "loginFormSubmit", "id");
        operation.click(allObjects, "profilePicture", "xpath");
        operation.click(allObjects, "viewProfile", "id");
        Assertions.assertEquals(username, operation.getText(allObjects, "name", "id"));
        operation.click(allObjects, "profile", "xpath");
        operation.click(allObjects, "logoutButton", "id");
    }

    @Test
    public void successfulLoginAndLogout() throws Exception{
        operation.setText(allObjects, "username", "id", username);
        operation.setText(allObjects, "password", "id", password);
        operation.click(allObjects, "loginFormSubmit", "id");
        operation.click(allObjects, "profilePicture", "xpath");
        operation.click(allObjects, "viewProfile", "id");
        Assertions.assertEquals(username, operation.getText(allObjects, "name", "id"));
        operation.click(allObjects, "profile", "xpath");
        operation.click(allObjects, "logoutButton", "id");
    }

    @AfterEach
    public void quit() {
        UIOperation.quitDriver();
    }
}
