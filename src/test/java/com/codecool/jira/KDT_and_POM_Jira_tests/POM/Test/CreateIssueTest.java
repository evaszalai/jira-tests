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

    private void createIssueInProject(String project, String issueType){
        navBar = new NavBar(driver);
        navBar.clickCreateButton();
        screen = new CreateIssueScreen(driver);
        screen.setFields(project, issueType, project + issueType);
        Assertions.assertEquals(project, screen.getProject());
        Assertions.assertEquals(issueType, screen.getIssueType());
    }

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

    @Test
    public void createStoryInCOALA(){
        createIssueInProject("COALA project (COALA)", "Story");
    }

    @Test
    public void createBugInCOALA(){
        createIssueInProject("COALA project (COALA)", "Bug");
    }

    @Test
    public void createTaskInCOALA(){
        createIssueInProject("COALA project (COALA)", "Task");
    }

    @Test
    public void createStoryInJETI(){
        createIssueInProject("JETI project (JETI)", "Story");
    }

    @Test
    public void createBugInJETI(){
        createIssueInProject("JETI project (JETI)", "Bug");
    }

    @Test
    public void createTaskInJETI(){
        createIssueInProject("JETI project (JETI)", "Task");
    }

    @Test
    public void createStoryInTOUCAN() {
        createIssueInProject("TOUCAN project (TOUCAN)", "Story");
    }

    @Test
    public void createBugInTOUCAN() {
        createIssueInProject("TOUCAN project (TOUCAN)", "Bug");
    }

    @Test
    public void createTaskInTOUCAN() {
        createIssueInProject("TOUCAN project (TOUCAN)", "Task");
    }

    @Test
    public void createSubtaskInTOUCAN(){
        issue = new BrowseIssuePage(driver);
        String key = "TOUCAN-1";
        Assertions.assertEquals("Create Subtask : " + key, issue.createSubtaskInIssue(key));
    }

    @Test
    public void createSubtaskInJETI(){
        issue = new BrowseIssuePage(driver);
        String key = "JETI-1";
        Assertions.assertEquals("Create Subtask : " + key, issue.createSubtaskInIssue(key));
    }

    @Test
    public void createSubtaskInCOALA(){
        issue = new BrowseIssuePage(driver);
        String key = "COALA-13";
        Assertions.assertEquals("Create Subtask : " + key, issue.createSubtaskInIssue(key));
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }

}
