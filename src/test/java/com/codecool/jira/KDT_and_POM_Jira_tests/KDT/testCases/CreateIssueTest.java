package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateIssueTest extends TestBase{

    @BeforeAll
    public static void login() throws Exception {
        setup();
        launchBrowser();
        operation.goToUrl("url");
        operation.setText("username", "id", username);
        operation.setText("password", "id", password);
        operation.click("loginbutton", "id");
    }

    @Test
    public void createIssueGeneral() throws Exception {
        String project = "Main Testing Project (MTP)";
        String issueType = "Bug";
        String summary = "Test create issue";
        operation.openCreateIssueScreen(project, issueType, summary);
        operation.clickCreateIssueSubmit();
        operation.click("createdIssueNotification", "xpath");
        Assertions.assertEquals(summary, operation.getText("summaryValue", "id"));
        Assertions.assertEquals(issueType, operation.getText("issueTypeValue", "id"));
        Assertions.assertEquals("Main Testing Project", operation.getText("projectNameValue", "id"));
    }

    @Test
    public void createIssueWithoutSummary() throws Exception {
        String errorMessage = "You must specify a summary of the issue.";
        operation.openCreateIssueScreen("Main Testing Project (MTP)", "Story", "" );
        operation.clickCreateIssueSubmit();
        Assertions.assertEquals(errorMessage, operation.getText("errorMessage", "css"));
    }

    @Test
    public void cancelButton() throws Exception {
        operation.openCreateIssueScreen("Main Testing Project (MTP)", "Story", "ID 12345");
        operation.clickCancelIssue();
        operation.acceptAlert();
        operation.goToUrl("issueID12345");
        String error = "No issues were found to match your search";
        Assertions.assertEquals(error, operation.getText("issueNotFound", "css"));
    }

    @Test
    public void createStoryInCOALA() throws Exception {
        String project = "COALA project (COALA)";
        String issueType = "Story";
        operation.openCreateIssueScreen(project, issueType, "Coala story");
        operation.checkProjectField(project);
        operation.checkIssueType(issueType);
    }
}
