package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateIssueScreen {
    WebDriver driver;

    @FindBy(id="project-field")
    WebElement projectField;

    @FindBy(id="issuetype-field")
    WebElement issueTypeField;

    @FindBy(id="summary")
    WebElement summaryField;

    @FindBy(xpath="//div[contains(@class, 'jira-dialog-core-content')]")
    WebElement dialogScreen;

    @FindBy(xpath="//button[contains(.,'Cancel')]")
    WebElement cancelButton;

    @FindBy(id="create-issue-submit")
    WebElement submit;

    @FindBy(xpath="//div[@id='aui-flag-container']/div/div/a")
    WebElement notification;

    public CreateIssueScreen(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setProject(String project){
        projectField.sendKeys(project);
    }

    public void setIssueType(String issueType){
        issueTypeField.sendKeys(issueType);
    }

    public void setSummary(String summary){
        summaryField.sendKeys(summary);
    }

    public void clickOutSide(){
        dialogScreen.click();
    }

    public void clickCancel(){
        cancelButton.click();
    }

    public void clickSubmit(){
        submit.click();
    }

    public void clickOnNotification(){
        notification.click();
    }
}
