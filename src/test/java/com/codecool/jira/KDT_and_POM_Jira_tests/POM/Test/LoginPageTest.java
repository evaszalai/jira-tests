package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginPageTest extends TestBase {
    JiraLoginPage login;
    NavBar navBar;
    ProfilePage profilePage;
    LogoutPage logoutPage;

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void goToUrl(){
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
    }

    @Test
    public void invalidPassword() {
        login = new JiraLoginPage(driver);
        login.login("invalid", "invalid");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.",
                login.getErrorMessage());
        login.login(username, password);
        navBar = new NavBar(driver);
        navBar.clickProfilePicture();
        navBar.clickViewProfile();
        profilePage = new ProfilePage(driver);
        Assertions.assertEquals(username, profilePage.getUserName());
        navBar.clickProfilePicture();
        navBar.clickLogoutButton();
    }

    @Test
    public void successfulLogin() {
        login = new JiraLoginPage(driver);
        login.login(username, password);
        navBar = new NavBar(driver);
        navBar.clickProfilePicture();
        navBar.clickViewProfile();
        profilePage = new ProfilePage(driver);
        Assertions.assertEquals(username, profilePage.getUserName());
        navBar.clickProfilePicture();
        navBar.clickLogoutButton();
        logoutPage = new LogoutPage(driver);
        Assertions.assertEquals("You are now logged out. Any automatic login has also been stopped.",
                logoutPage.getMessage());
    }
}
