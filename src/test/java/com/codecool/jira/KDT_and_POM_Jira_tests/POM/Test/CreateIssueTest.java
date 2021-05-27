package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.CreateIssueScreen;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.NavBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        screen.clickProjectField();
        screen.setProject("");
        screen.setProject(project);
        screen.clickOutSide();
        screen.clickIssueTypeField();
        screen.setIssueType("");
        screen.setIssueType(issueType);
        screen.clickOutSide();
        screen.clickSummaryField();
        screen.setSummary(summary);
        screen.clickSubmit();
        screen.clickOnNotification();
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals(summary, issue.getSummary());
        Assertions.assertEquals(issueType, issue.getIssueType());
        Assertions.assertEquals("Main Testing Project", issue.getProjectName());
    }

}
