package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import org.junit.jupiter.api.*;

public class BrowseIssueTest extends TestBase{
    JiraLogin login;
    BrowseIssuePage issue;

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void login(){
        this.launchBrowser();
        driver.get("https://jira-auto.codecool.metastage.net");
        login = new JiraLogin(driver);
        login.login(username, password);
    }

    @Test
    public void browseIssue(){
        String key = "MTP-146";
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals(key, issue.browseIssue(key));
    }

    @Test
    public void browseNonExistentIssue(){
        driver.get("https://jira-auto.codecool.metastage.net/browse/COALA-5");
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals("You can't view this issue", issue.issueNotAvailable());
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }
}
