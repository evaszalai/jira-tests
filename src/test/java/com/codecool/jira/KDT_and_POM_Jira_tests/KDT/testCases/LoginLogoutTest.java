package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import   com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.ReadObject;
import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginLogoutTest {
    private static String username;
    private static String password;
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
    }

    @BeforeEach
    public void goToURL() throws IOException{
        operation = new UIOperation();
        operation.goToUrl("url");
    }

    @Test
    public void emptyCredentials() throws Exception {
       operation.click("loginbutton", "id");
       Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText("wrongLoginMessage", "xpath"));
    }

    @Test
    public void invalidPassword() throws Exception {
        operation.setText("username", "id", username);
        operation.setText("password", "id", "invalid");
        operation.click("loginbutton", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText("wrongLoginMessage", "xpath"));
        operation.setText("username", "id", username);
        operation.setText("password", "id", password);
        operation.click("loginbutton", "id");
        operation.click("profilePicture", "xpath");
        operation.click("viewProfile", "id");
        Assertions.assertEquals(username, operation.getText("name", "id"));
        operation.click("profile", "xpath");
        operation.click("logoutButton", "id");
    }

    @Test
    public void notRegisteredUser() throws Exception {
        operation.setText("username", "id", "invalid");
        operation.setText("password", "id", "invalid");
        operation.click("loginbutton", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText("wrongLoginMessage", "xpath"));
    }

    @Test
    public void successfulLoginAndLogout() throws Exception{
        operation.setText("username", "id", username);
        operation.setText("password", "id", password);
        operation.click("loginbutton", "id");
        operation.click("profilePicture", "xpath");
        operation.click("viewProfile", "id");
        Assertions.assertEquals(username, operation.getText("name", "id"));
        operation.click("profile", "xpath");
        operation.click("logoutButton", "id");
    }

    @AfterEach
    public void quit() {
        UIOperation.quitDriver();
    }
}
