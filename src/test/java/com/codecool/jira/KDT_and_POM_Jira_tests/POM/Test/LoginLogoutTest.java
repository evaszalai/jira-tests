package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginLogoutTest extends TestBase {
    JiraLogin login;

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void goToUrl(){
        driver.get("https://jira-auto.codecool.metastage.net");
    }

    @Test
    public void notRegisteredUser(){
        login = new JiraLogin(driver);
        login.login("invalid", "invalid");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.",
                login.getErrorMessage());
    }
}
