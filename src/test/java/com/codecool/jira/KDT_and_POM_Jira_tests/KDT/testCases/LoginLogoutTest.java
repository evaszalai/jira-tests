package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import   com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.ReadObject;
import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        username = browserProps.getProperty("username");
        password = browserProps.getProperty("password");
        operation = new UIOperation();
    }

    @Test
    public void emptyCredentials() throws Exception {
        operation.goToUrl("url");
        operation.setText("username", "id", "invalid");
        operation.setText("password", "id", "invalid");
        operation.click("loginbutton", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText("wrongLoginMessage", "xpath"));
    }
}
