package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrowseIssuePage {
    WebDriver driver;

    @FindBy(id="project-name-val")
    WebElement projectName;

    @FindBy(id="type-val")
    WebElement issueType;

    @FindBy(id="summary-val")
    WebElement summary;

    @FindBy(xpath="//*[@id=\"edit-issue\"]")
    WebElement editButton;

    @FindBy(id="opsbar-operations_more")
    WebElement moreOptions;

    @FindBy(id="create-subtask")
    WebElement createSubtask;

    public BrowseIssuePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProjectName(){
        return projectName.getText();
    }

    public String getIssueType(){
        return issueType.getText();
    }

    public String getSummary(){
        return summary.getText();
    }

    public String checkEditButton(){
        return editButton.getText();
    }

    public void clickEditButton(){
        editButton.click();
    }

    public void clickMoreOptions(){
        moreOptions.click();
    }

    public void clickCreateSubtask(){
        createSubtask.click();
    }

}
