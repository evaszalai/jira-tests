package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import org.junit.jupiter.api.*;

public class BrowseIssueTest extends TestBase{
    JiraLogin login;
    BrowseIssuePage issue;

    private void browseIssue(String key){
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals(key, issue.browseIssue(key));
    }

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
        browseIssue("MTP-146");
    }

    @Test
    public void browseNonExistentIssue(){
        driver.get("https://jira-auto.codecool.metastage.net/browse/COALA-5");
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals("You can't view this issue", issue.issueNotAvailable());
    }

    @Test
    public void browseCoalaIssue1(){
        browseIssue("COALA-1");
    }

    @Test
    public void browseCoalaIssue2(){
        browseIssue("COALA-2");
    }

    @Test
    public void browseCoalaIssue3(){
        browseIssue("COALA-3");
    }

    @Test
    public void browseJetiIssue1(){
        browseIssue("JETI-1");
    }

    @Test
    public void browseJetiIssue2(){
        browseIssue("JETI-2");
    }

    @Test
    public void browseJetiIssue3(){
        browseIssue("JETI-3");
    }

    @Test
    public void browseToucanIssue1(){
        browseIssue("TOUCAN-1");
    }

    @Test
    public void browseToucanIssue2(){
        browseIssue("TOUCAN-2");
    }

    @Test
    public void browseToucanIssue3(){
        browseIssue("TOUCAN-3");
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }
}
