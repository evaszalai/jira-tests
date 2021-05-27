package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.CreateIssueScreen;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.NavBar;
import org.junit.jupiter.api.*;

public class CreateIssueTest extends TestBase {
    CreateIssueScreen screen;
    NavBar navBar;
    JiraLogin login;
    BrowseIssuePage issue;

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void login(){
        driver.get("https://jira-auto.codecool.metastage.net");
        login = new JiraLogin(driver);
        login.login(username, password);
    }

    @Test
    public void testCreateIssue(){
        String project = "Main Testing Project (MTP)";
        String issueType = "Bug";
        String summary = "Test create issue";
        navBar = new NavBar(driver);
        navBar.clickCreateButton();
        screen = new CreateIssueScreen(driver);
        screen.setFields(project, issueType, summary);
        screen.clickSubmit();
        screen.clickOnNotification();
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals(summary, issue.getSummary());
        Assertions.assertEquals(issueType, issue.getIssueType());
        Assertions.assertEquals("Main Testing Project", issue.getProjectName());
    }

    @Test
    public void createIssueWithoutSummary(){
        String errorMessage = "You must specify a summary of the issue.";
        navBar = new NavBar(driver);
        navBar.clickCreateButton();
        screen = new CreateIssueScreen(driver);
        screen.setFields("Main Testing Project (MTP)", "Bug", "");
        screen.clickSubmit();
        Assertions.assertEquals(errorMessage, screen.getErrorMessage());
    }

    @Test
    public void cancelButton(){
        navBar = new NavBar(driver);
        navBar.clickCreateButton();
        screen = new CreateIssueScreen(driver);
        screen.setFields("Main Testing Project (MTP)", "Story", "ID 12345");
        screen.clickCancel();
        driver.switchTo().alert().accept();
        driver.get("https://jira-auto.codecool.metastage.net/issues/?jql=summary%20~%20%27ID%2012345%27%20AND%20createdDate%20%3E%3D%20startOfDay()");
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals("No issues were found to match your search", issue.getErrorMessage());
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }

}
