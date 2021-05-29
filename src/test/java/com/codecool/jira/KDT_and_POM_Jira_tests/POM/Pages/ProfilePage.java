package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage {
    WebDriver driver;

    @FindBy(id = "up-d-username")
    WebElement userName;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName.getText();
    }
}