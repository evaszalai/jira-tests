package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
    WebDriver driver;

    @FindBy(id = "//main[@id='main']/div/div/p/strong")
    WebElement logoutMessage;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMessage() {
        return logoutMessage.getText();
    }
}
